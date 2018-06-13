package com.dsb.yumo.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{

	private int fps = 6;
	private BufferedImage[] charImgs;
	int count = 0;
	private BufferedImage background = null;
	public GamePanel() {
		charImgs = ImgToCharacter.getCharImgs();
		initBackgroudImg();
		Thread t = new MyThread();
		t.start();
	}
	public void initBackgroudImg() {
		background = new BufferedImage(MainFrame.WIDTH, MainFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics gb = background.getGraphics();
		gb.setColor(Color.white);
		gb.fillRect(0, 0, background.getWidth(), background.getHeight());
		gb.dispose();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int size = charImgs.length;
		int index = count % size;
		BufferedImage img = charImgs[index];
		int w = img.getWidth();
		int h = img.getHeight();
		int x = (MainFrame.WIDTH - w)/2;
		int y = (MainFrame.HEIGHT - h)/2;
		//����Ϳ��
		g.setColor(Color.white);
		g.fillRect(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		g.setColor(Color.black);
		// 3/4Ϊ��ʾ���������ǵ��е�gifͼƬ̫��������С��һ�������ڴ�������ʾ
		g.drawImage(img, 0, MainFrame.HEIGHT - h,w*3/4, h*3/4, null);
		count++;
	}
	
	class MyThread extends Thread {

		long startTime;
		long endTime;
		long sleepTime;
		long spendTime;
		long period = 1000/fps;
		@Override
		public void run() {
			while(true) {
				startTime = System.currentTimeMillis();
				repaint();
				endTime = System.currentTimeMillis();
				spendTime = endTime - startTime;
				sleepTime = period - spendTime;
				if(sleepTime < 0)
					sleepTime = 2;
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
}
