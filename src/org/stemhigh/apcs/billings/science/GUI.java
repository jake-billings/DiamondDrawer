package org.stemhigh.apcs.billings.science;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.go();
	}
	
	private JFrame frame;
	private JTextArea console;
	private JScrollPane consoleScroller;
	private JButton button;
	private JLabel diamondNumberLabel;
	private long diamonds = 0;
	
	public GUI() {
		frame = new JFrame("Diamond Drawer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,500,500);
		
		diamondNumberLabel=new JLabel();
		frame.add(diamondNumberLabel,BorderLayout.NORTH);
		
		console = new JTextArea();
		console.setEditable(false);
		
		consoleScroller = new JScrollPane(console);
		
		button = new JButton("Go!");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawDiamond();			
			}
			
		});
		
		frame.add(consoleScroller,BorderLayout.CENTER);
		frame.add(button,BorderLayout.SOUTH);
	}
	
	public void drawDiamond() {
		diamonds++;
		logln("=Diamond "+ (diamonds) + "=");
		logln("   /\\   ");
		logln("  /  \\  ");
		logln(" /    \\ ");
		logln(" \\    /  ");
		logln("  \\  /   ");
		logln("   \\/   ");
		diamondNumberLabel.setText("Diamonds: 0"+diamonds);
	}
	
	public void scrollToBottom() {
		consoleScroller.getVerticalScrollBar().setValue(consoleScroller.getVerticalScrollBar().getMaximum());
	}
	
	public void log(String str) {
		console.setText(console.getText()+str);
		scrollToBottom();
	}
	
	public void logln(String str) {
		log(str+"\n");
	}

	public void go() {
		frame.setVisible(true);
	}
}
