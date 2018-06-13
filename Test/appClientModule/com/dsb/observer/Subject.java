package com.dsb.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题类
 * @author admin
 *
 */
public abstract class Subject {

	/**
	 * 观察者列表
	 */
	private List<Observer> observerList = new ArrayList<Observer>();
	
	/**
	 * 添加观察者
	 * @param observer
	 */
	public void addObserver(Observer observer){
		observerList.add(observer);
	}
	
	/**
	 * 移除观测者
	 * @param observer
	 */
	public void removeObserver(Observer observer){
		observerList.remove(observer);
	}
	
	/**
	 * 通知所有观测者
	 * @param newState
	 */
	public void notifyObservers(String newState){
		for (Observer observer : observerList) {
			observer.update(newState);
		}
	}
}
