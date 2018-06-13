package com.dsb.observer;

/**
 * 被观测者-地图类
 * @author admin
 *
 */
public class Map extends Subject {
	
	/**
	 * 地图信息
	 */
	private String[][] info = new String[100][100];

	/**
	 * 被发现的地区
	 */
	public static String IS_FIND_AREA = "□";
	
	/**
	 * 未被发现的地区
	 */
	public static String NOT_FIND_AREA = "■";
	
	/**
	 * 地区坐标分隔的标志
	 */
	public static String POSITION_SYMBOL = ",";
	
	/**
	 * 发现新地图
	 * @param position
	 */
	public void findNewMap(String position){
		
		String index[] = position.split(POSITION_SYMBOL);
		int x = Integer.valueOf(index[0]);
		int y = Integer.valueOf(index[1]);
		info[x][y] = IS_FIND_AREA;
		
	}
	
	/**
	 * 是否在地图之外
	 * @param position
	 * @return
	 */
	public static boolean isOutOfMap(String position){
		String index[] = position.split(POSITION_SYMBOL);
		int x = Integer.valueOf(index[0]);
		int y = Integer.valueOf(index[1]);
		if (x < 0 || y < 0 || x > 99 || y > 99) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否是已被发现的地图
	 * @param position
	 * @return
	 */
	public boolean isFindArea(String position){
		String index[] = position.split(POSITION_SYMBOL);
		int x = Integer.valueOf(index[0]);
		int y = Integer.valueOf(index[1]);
		return IS_FIND_AREA.equals(info[x][y]);
	}

	/**
	 * 显示地图信息
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < info.length; i++) {
			for (int j = 0; j < info[i].length; j++) {
				System.out.print(info[i][j]);
			}
			System.out.println();
		}
		return sb.toString();
	}
	
	
	
}
