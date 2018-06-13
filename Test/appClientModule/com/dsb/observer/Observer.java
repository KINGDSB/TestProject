package com.dsb.observer;

/**
 * 观测者接口
 * @author admin
 *
 */
public interface Observer {
	
	/**
	 * 观测对象变更时观测者相应变化
	 * @param str
	 */
	public void update(String str);
	
}
