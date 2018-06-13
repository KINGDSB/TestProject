package com.dsb.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * 扫雷 v0.0.1
 * 
 * @author admin
 * 
 */
public class Minesweeper {
	
	/** 场地 */
	private String[][] ground;
	/** 地雷数 */
	private int landmineNum;
	/** 地雷位置 */
	private Set<String> landminePositionSet;
	/** 点击次数 */
	private int clickCount;
	
	/**
	 * 初始化游戏
	 * @param groundSize
	 * @param landmineNum
	 */
	public void init(int groundSize,int landmineNum){
		
		ground = new String[groundSize][groundSize];
		for (int i = 0; i < ground.length; i++) {
			for (int j = 0; j < ground[i].length; j++) {
				ground[i][j]="□";
			}
		}
		
		this.landmineNum = landmineNum;
		landminePositionSet = new HashSet<String>();		
		Random random = new Random();
		
		while (landmineNum > 0) {
			
			int x = random.nextInt(groundSize);
			int y = random.nextInt(groundSize);
			
			if (landminePositionSet.add(x+","+y)) {
				landmineNum--;
			}
			
		}
		
		clickCount = 0;
	}
	
	/**
	 * 展示场地
	 */
	public void showGround(){
		for (int i = 0; i < ground.length; i++) {
			for (int j = 0; j < ground[i].length; j++) {
				System.out.print(ground[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("地雷数:"+landmineNum);
	}
	
	/**
	 * 检查当前位置
	 * @param position
	 * @return
	 */
	public int checkPosition(String position){
		
		int num = 0;
		
		if (landminePositionSet.contains(position)) {
			System.err.println("boom!!!!");
			System.exit(1);
		}
		
		String[] positions = position.split(",");
		int x = Integer.parseInt(positions[0]);
		int y = Integer.parseInt(positions[1]);
		
		// 从左上顺时针
		if (x - 1 > 0 && y - 1 > 0) {
			num += landminePositionSet.contains(x-1+","+(y - 1))?1:0;
		}
		if (y - 1 > 0) {
			num += landminePositionSet.contains(x+","+(y - 1))?1:0;
		}
		if (x + 1 < ground.length && y - 1 > 0) {
			num += landminePositionSet.contains(x+1+","+(y - 1))?1:0;
		}
		
		if (x - 1 > 0) {
			num += landminePositionSet.contains(x-1+","+y)?1:0;
		}
		if (x + 1 < ground.length) {
			num += landminePositionSet.contains(x+1+","+y)?1:0;
		}
		
		if (x - 1 > 0 && y + 1 > 0) {
			num += landminePositionSet.contains(x-1+","+(y + 1))?1:0;
		}
		if (y + 1 > 0) {
			num += landminePositionSet.contains(x+","+(y + 1))?1:0;
		}
		if (x + 1 < ground.length && y + 1 > 0) {
			num += landminePositionSet.contains(x+1+","+(y + 1))?1:0;
		}
		
		return num;
	}
	
	/**
	 * 单击
	 * @param position
	 * @return
	 */
	public int click(String position){
		
		if (position == null || "".equals(position) || position.indexOf(",") == -1) {
			return 0;
		}
		
		String[] positions = position.split(",");
		int x = Integer.parseInt(positions[0]);
		x -= 1;
		int y = Integer.parseInt(positions[1]);
		y -= 1;
		
		if (x < 0 || x > ground.length || y < 0 || y > ground.length) {
			System.err.println("请输入正确的位置!");
			return 0;
		}
		
		int rtn = checkPosition(position);
		
//		while (0 == rtn) {
//			
//		}
		
		// 原位置变成数字
		ground[x][y] = String.valueOf(rtn);
		// 点击次数+1
		clickCount++;
		
		return rtn;
	}
	
	public String[][] getGround() {
		return ground;
	}

	public void setGround(String[][] ground) {
		this.ground = ground;
	}

	public int getLandmineNum() {
		return landmineNum;
	}

	public void setLandmineNum(int landmineNum) {
		this.landmineNum = landmineNum;
	}

	public Set<String> getLandminePositionSet() {
		return landminePositionSet;
	}

	public void setLandminePositionSet(Set<String> landminePositionSet) {
		this.landminePositionSet = landminePositionSet;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Minesweeper m = new Minesweeper();
		
		// 场地大小
		int groundSize = 10;
		// 地雷数
		int landmineNum = 50;
		
		m.init(groundSize, landmineNum);
		
		// 作弊 输出地雷位置
		for (String landminePosition : m.getLandminePositionSet()) {
			System.out.print(landminePosition+" ");
		}
		System.out.println();
		
		int i = groundSize*groundSize-landmineNum;
		
		while (m.getLandmineNum() > 0 || m.getClickCount() >= i) {
			
			m.showGround();
			
			System.out.println("请输入位置(x,y):");
			
			m.click(scan.next());
			
		}
		
		scan.close();
	}
}