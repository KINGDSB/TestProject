package com.dsb.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleEvent extends JFrame {

	private static final long serialVersionUID = 1L;
	private static int a;
	private static int b;
	private static char c;
	private static int d;

	private JButton jb = new JButton("1");
	private JButton jb1 = new JButton("2");
	private JButton jb9 = new JButton("+");
	private JButton jb10 = new JButton("=");
	final JTextField jt = new JTextField("", 20);
	
	private boolean firstDigit = true;

	public SimpleEvent() {
		setLayout(null);
		setSize(350, 250);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container cp = getContentPane();
		cp.add(jb);
		cp.add(jb1);
		cp.add(jb9);
		cp.add(jb10);
		cp.add(jt);
		jb.setBounds(10, 100, 80, 30);
		jb1.setBounds(120, 100, 80, 30);
		jb9.setBounds(10, 220, 80, 30);
		jb10.setBounds(120, 220, 80, 30);
		getContentPane().setLayout(new FlowLayout(80, 50, 10));

		if (firstDigit = true) {
			firstDigit = false;
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jt.setText("1");
					a = 1;
				}
			});
			jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jt.setText("2");
					a = 2;
				}
			});
		}

		if (firstDigit = false) {
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jt.setText("1");
					b = 1;
				}
			});
			jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jt.setText("2");
					b = 2;
				}
			});
		}

		jb9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jt.setText("+");
				char c = '+';
			}
		});
		jb10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jt.setText("=");
				switch (c) {
				case '+':
					d = a + b;
					break;
				}
				System.out.println(a);
				System.out.println(b);
				System.out.println(d);
			}
		});
		setVisible(true);

	}

	public static void main(String[] args) {
		new SimpleEvent();
	}
}