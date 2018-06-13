package com.dsb.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title RedisUtil.java 
 * @Package com.dsb.redis 
 * @Description redis工具类
 * @author dsb
 * @date 2018年3月2日 下午1:19:47
 */
public final class RedisUtil {

	// Redis 服务器 ip
	private static String HOST = "localhost";

	// Redis 端口号
	private static int PORT = 6379;

	// 密码
	 private static String PASSWORD = "lexinyyt!1234";

	// 可用连接实例的最大数目，默认值为8； 
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。 
	private static int MAX_ACTIVE = 100;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 10;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	private static JedisPool jedisPool = initJedisPool();

	/**
	 * @Title initJedisPool 
	 * @Description 初始化jedis连接池
	 * @return
	 */
	private static JedisPool initJedisPool(){
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			return new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title getJedis 
	 * @Description 获取jedis连接
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title returnResource 
	 * @Description 关闭jedis连接 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	public static String getHOST() {
		return HOST;
	}

	public static void setHOST(String hOST) {
		HOST = hOST;
	}

	public static int getPORT() {
		return PORT;
	}

	public static void setPORT(int pORT) {
		PORT = pORT;
	}

	public static String getPASSWORD() {
		return PASSWORD;
	}

	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public static int getMAX_ACTIVE() {
		return MAX_ACTIVE;
	}

	public static void setMAX_ACTIVE(int mAX_ACTIVE) {
		MAX_ACTIVE = mAX_ACTIVE;
	}

	public static int getMAX_IDLE() {
		return MAX_IDLE;
	}

	public static void setMAX_IDLE(int mAX_IDLE) {
		MAX_IDLE = mAX_IDLE;
	}

	public static int getMAX_WAIT() {
		return MAX_WAIT;
	}

	public static void setMAX_WAIT(int mAX_WAIT) {
		MAX_WAIT = mAX_WAIT;
	}

	public static int getTIMEOUT() {
		return TIMEOUT;
	}

	public static void setTIMEOUT(int tIMEOUT) {
		TIMEOUT = tIMEOUT;
	}
	
}