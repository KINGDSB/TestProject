package com.dsb.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wm on 2017/1/15.
 * 缓存操作策略类
 */
public abstract class CacheStrategy {
	private static final Logger logger = LoggerFactory.getLogger(CacheStrategy.class);
	
    public static class NoCacheException extends Exception {
		private static final long serialVersionUID = -2111078329021202611L;
    }

    public interface ReadWriteCacheInterface <KeyType,ValueType> {
        ValueType read(KeyType key) throws NoCacheException,Exception;
        boolean write(KeyType key, ValueType value) throws Exception;
    }

    public interface ReadSourceDataInterface <KeyType,ValueType> {
        ValueType readSourceData(KeyType key) throws Exception;
    }

    public static <KeyType,ValueType> ValueType getAndUpdateCache(
            KeyType key,
            ReadWriteCacheInterface<KeyType,ValueType> rwci,
            ReadSourceDataInterface<KeyType,ValueType> rsdi,
            Object syncLock) throws Exception {
        // 先从缓存读取数据
        try {
            return rwci.read(key);
        } catch (NoCacheException e) {
        } catch (Exception e) {
            throw e;
        }

        try {
            synchronized (syncLock) {
                // 从缓存读取数据, !!! 尝试将这段代码注释, 看看运行效果
                try {
                    return rwci.read(key);
                } catch (NoCacheException e) {
                }

                // 直接从数据源获取原始数据
                ValueType value = rsdi.readSourceData(key);

                // 写缓存
                if (!rwci.write(key, value)) {
                	logger.warn("write cache error, key={}, value={}", key, value);
                }

                return value;
            }
        } catch (Exception e) {
            throw e;
        }
    }


    public static void main(String[] argv) {
        final Map<String, String> cacheMap = new HashMap<String, String>();
        final String keyA = "key_a";
//        cacheMap.put(keyA, "av");

        final ReadWriteCacheInterface<String, String> rwci = new ReadWriteCacheInterface<String, String>() {
            @Override
            public String read(String key) throws Exception {
                if (!cacheMap.containsKey(key))
                    throw new NoCacheException();
                System.out.println("read data of key["+key+"] from cache");
                return cacheMap.get(key);
            }

            @Override
            public boolean write(String key, String value) throws Exception {
                System.out.println("write cache key="+key + ", value="+value);

                //int timeout = 100;
                //if (null == value)  // 空值需要过期更快
                //    timeout >>>= 1;

                cacheMap.put(key, value);
                // TODO 设置过期时间
                return true;
            }
        };

        final ReadSourceDataInterface<String, String> rsdi = new ReadSourceDataInterface<String, String>() {
            private Random random = new Random(System.currentTimeMillis());

            @Override
            public String readSourceData(String key) throws Exception {
                System.out.println("read data of key["+key+"] from source");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return String.valueOf(random.nextDouble());
            }
        };

        Thread[] threads = new Thread[100];
        for (int i=0; i<threads.length; ++i) {
            Thread testThread = new Thread("testThread" + i) {
                @Override
                public void run() {
                    try {
                        String value = getAndUpdateCache(keyA, rwci, rsdi, keyA);
                        System.out.println(Thread.currentThread().getName() + ", value=" + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            threads[i] = testThread;
        }

        for (int i=0; i<threads.length; ++i) {
            threads[i].start();
        }

        for (int i=0; i<threads.length; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
