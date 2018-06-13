package com.dsb.observer;

import java.util.Random;

/**
 * 玩家-观测者
 * @author admin
 *
 */
public class Player implements Observer {
	
	private String playerName;
	
	/**
	 * 玩家观测到的地图
	 */
	private Map map;
	
	/**
	 * 玩家位置
	 */
	private String position;
	
	/**
	 * 随机运动
	 */
	private Random random = new Random();
	
	/**
	 * 探索
	 * @param info
	 */
	public void searching(String info){
		
		// 行动
		action();
		
		if (!map.isFindArea(this.position)) {
			map.notifyObservers(this.position);
		}
		
	}

	@Override
	public void update(String str) {
		map.findNewMap(str);
	}
	
	/**
	 * 行动 
	 * 	往上下左右四个方向运动 超出地图的时不行动
	 * @return
	 */
	public void action(){
		String index[] = position.split(Map.POSITION_SYMBOL);
		int x = Integer.valueOf(index[0]);
		int y = Integer.valueOf(index[1]);
		
		/**
		 * 方向 0:↑ 1:→ 2:↓ 3:←
		 */
		int direction = random.nextInt(4);
		switch (direction) {
			case 0:
				y += 1;
				break;
			case 1:
				x += 1;
				break;
			case 2:
				y -= 1;
				break;
			case 3:
				x -= 1;
				break;
		}

		String newPosition = x+","+y;
		
		if (Map.isOutOfMap(newPosition)) {
			this.position = newPosition;
		}
	}

	public Player(String playerName, Map map, String position) {
		this.playerName = playerName;
		this.map = map;
		this.position = position;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
