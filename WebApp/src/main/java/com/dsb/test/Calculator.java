package com.dsb.test;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
	private JPanel pa1, pa2, pa3, pa4;
	private JTextField tfResult;// 显示结果的文本框
	private final String[] KEYS = { "7", "8", "9", "1/x", "+", "4", "5", "6", "-", "=", "1", "2", "3", "*", "/", "0",
			".", "x2" };// 运算键
	private final String[] COMMANDS = { "←", "CE", "C" };// 功能键
	private JButton[] commandButtons, keyButtons;// 功能键和运算键
	private boolean flagFirstDigit = true;
	private double temp;// 存储中间结果
	private String operator = "=";// 当前操作符
	private JMenuBar mb;
	private JMenu menuEdit, menuAbout, menuColor;
	private JMenuItem miCopy, miPaste, miAbout, miRed, miBlue;
	private String clipBoard;
	private boolean flagValidOperation = true;

	public static void main(String[] args) {
		Calculator c = new Calculator();
		c.setSize(800,800);
		c.setVisible(true);
	}
	
	public Calculator() {
		// 添加菜单
		mb = new JMenuBar();
		setJMenuBar(mb);
		menuEdit = new JMenu("编辑（E）");
		menuEdit.setMnemonic('E');
		menuColor = new JMenu("改变颜色(S)");
		menuColor.setMnemonic('S');
		menuAbout = new JMenu("帮助(H)");
		menuAbout.setMnemonic('H');
		miCopy = new JMenuItem("复制(C)", 'C');
		miCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		miPaste = new JMenuItem("粘贴(P)", 'P');
		miPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		miCopy.addActionListener(this);
		miPaste.addActionListener(this);
		menuEdit.add(miCopy);
		menuEdit.add(miPaste);
		miAbout = new JMenuItem("关于计算器(A）", 'A');
		miAbout.addActionListener(this);
		menuAbout.add(miAbout);
		mb.add(menuEdit);
		mb.add(menuColor);
		mb.add(menuAbout);

		setLayout(new BorderLayout());
		tfResult = new JTextField("0", 20);
		tfResult.setHorizontalAlignment(JTextField.RIGHT);
		pa1 = new JPanel();
		pa1.add(tfResult);

		pa4 = new JPanel();// 是pa2和pa3的容器
		pa2 = new JPanel();// 是功能键的容器
		pa3 = new JPanel();// 是运算键的容器
		pa2.setLayout(new GridLayout(1, 3, 3, 3));
		pa3.setLayout(new GridLayout(0, 5, 3, 3));
		pa4.setLayout(new BorderLayout(3, 3));

		commandButtons = new JButton[COMMANDS.length];
		for (int i = 0; i < COMMANDS.length; i++) {
			commandButtons[i] = new JButton(COMMANDS[i]);
			commandButtons[i].addActionListener(this);
			pa2.add(commandButtons[i]);
		}
		keyButtons = new JButton[KEYS.length];
		for (int i = 0; i < KEYS.length; i++) {
			keyButtons[i] = new JButton(KEYS[i]);
			keyButtons[i].addActionListener(this);
			pa3.add(keyButtons[i]);
		}

		pa4.add(pa2, BorderLayout.NORTH);
		pa4.add(pa3, BorderLayout.CENTER);

		add(pa1, BorderLayout.NORTH);
		add(pa4, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String key = ae.getActionCommand();
		if (ae.getSource() == miCopy) {
			System.out.println("copy");
			clipBoard = tfResult.getText();

		} else if (ae.getSource() == miPaste) {
			if (clipBoard != null)
				tfResult.setText(clipBoard);

		} else if (ae.getSource() == miAbout) {
			JOptionPane.showMessageDialog(null, "我的计算器\n软件1171 张三", "我的计算器", JOptionPane.OK_OPTION);
		} else if (key.equals(COMMANDS[0])) {
			
			handleBackSpace();
		} else if (key.equals(COMMANDS[0])) {
			
		} else if (key.equals(COMMANDS[2])) {
			tfResult.setText("0");
			flagFirstDigit = false;
		} else if (key.equals("x2")) {
			double num = Double.parseDouble(tfResult.getText());
			num = num*num;
			
			String str;
//			
//			// 正常写法
//			if (num%1==0) {
//				str = String.valueOf((int)num);
//			} else {
//				str = String.valueOf(num);
//			}
//			
//			// 三目运算符 简写
//			str = num%1==0 ? String.valueOf((int)num) : String.valueOf(num);
//			
			
			
			tfResult.setText(num%1==0?String.valueOf((int)num):String.valueOf(num));
		}
		// 处理数字键
		else if ("0123456789.".indexOf(key) >= 0)
			handleNumber(key);
		else
			handleOperator(key);

	}

	private void handleNumber(String key) {
		if (flagFirstDigit)
			tfResult.setText(key);
		else if (key.equals(".") && tfResult.getText().indexOf(".") >= 0) {
		} else
			tfResult.setText(tfResult.getText().equals("0")?String.valueOf(key):tfResult.getText()+key);

		flagFirstDigit = false;

	}

	private void handleBackSpace() {

	}

	private void handleOperator(String key) {
		System.out.println("key=" + key);
		System.out.println("operator=" + operator);
		switch (operator) {
		case "+":
			temp += Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;
		case "=":
			temp = Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;
		case "*":
			temp = Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;
		case "1/x":
			if (temp == 0) {
				flagFirstDigit = false;
				tfResult.setText("零没有倒数");
			} else
				temp = 1 / temp;
			Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;

		case "%":
			temp %= Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;
		case "x2":
			temp = temp * temp;
			Double.parseDouble(tfResult.getText());
			System.out.println("temp=" + temp);
			break;
		case "/":
			if (Double.parseDouble(tfResult.getText()) == 0.0) {
				tfResult.setText("除数不能为0");
				flagValidOperation = false;

			} else
				temp /= Double.parseDouble(tfResult.getText());
		}

		if (flagValidOperation) {
			if ((int) temp == temp)
				tfResult.setText(Integer.toString((int) temp));
			else
				tfResult.setText(Double.toString(temp));
		}
		operator = key;
		flagFirstDigit = true;
	}
}
