package com.dsb.yumo.game;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {

	public static String input = "D://1.gif"; //gifͼƬ·�����Ƽ�ʹ��������400*400���ڵ�
	public static String output = "D://charGif//"; //gifͼƬ����·��������û������AnimatedGifEncoder��
												   //�������ɵ���һЩjpgͼƬ����Ҫ�Լ�������������������gif
												   //������������Լ����Ľ���ֱ������һ��gif�ļ�
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});
	}
}

@SuppressWarnings("serial")
class MainFrame extends JFrame {
	private int x;
	private int y;
	public static int WIDTH = 0;
	public static int HEIGHT = 0;
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		int screen_width = kit.getScreenSize().width;
		int screen_height = kit.getScreenSize().height;
		x = (screen_width-WIDTH)/2;
		y = (screen_height-HEIGHT)/2;
	}
	public MainFrame() {
		setTitle("CharacterGIF_1.0  by YuMo");
		initBounds();
		createGIF();
		setBounds(x, y, WIDTH, HEIGHT);
		setResizable(false);
		setIconImage(null);
		
		JPanel panel = new GamePanel();
		Container container = getContentPane();
		container.add(panel);
	}
	//���ַ�ͼƬ�����ָ��Ŀ¼
	public void createGIF() {
		BufferedImage[] charImgs = ImgToCharacter.getCharImgs();
			try {
				for (int i=0; i<charImgs.length; i++) {
					File file = new File(Client.output+i+".jpg");
					if (!file.exists()) {
						file.createNewFile();
					}
					ImageIO.write(charImgs[i], "jpg", file);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println("ͼƬ�����ɣ�");
	}
	public void initBounds() {
		ImgToCharacter.readGiF();
		ImgToCharacter.draw();
		BufferedImage[] charImgs = ImgToCharacter.getCharImgs();
		int max_width = 0;
		int max_height = 0;
		for (BufferedImage img : charImgs) {
			if(img.getWidth() > max_width)
				max_width = img.getWidth();
			if(img.getHeight() > max_height)
				max_height = img.getHeight();
		}
		WIDTH = max_width;
		HEIGHT = max_height;
	}
}
