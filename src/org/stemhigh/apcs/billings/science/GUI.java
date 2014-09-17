package org.stemhigh.apcs.billings.science;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private long allDiamonds = 0;
	private double diamondsPerSecond = 0;
	private double diamondsPerSecondOverflow = 0;
	private JPanel sidebar;
	private JButton btnBuyScribe;

	private Thread updateDiamondThread;

	public GUI() {
		frame = new JFrame("Diamond Drawer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,500,500);

		diamondNumberLabel=new JLabel();
		frame.getContentPane().add(diamondNumberLabel,BorderLayout.NORTH);

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

		sidebar = new JPanel(new GridLayout(5,1));
		frame.getContentPane().add(sidebar, BorderLayout.WEST);

		btnBuyScribe = new JButton("Buy Servent (50)");
		btnBuyScribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (diamonds>=50) {
					diamondsPerSecond+=5;
					diamonds-=50;
				}
			}
		});
		sidebar.add(btnBuyScribe);

		btnBuyScribe = new JButton("Buy Scribe (500)");
		btnBuyScribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (diamonds>=500) {
					diamondsPerSecond+=50;
					diamonds-=500;
				}
			}
		});
		sidebar.add(btnBuyScribe);

		btnBuyScribe = new JButton("Buy Author (5000)");
		btnBuyScribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (diamonds>=5000) {
					diamondsPerSecond+=500;
					diamonds-=5000;
				}
			}
		});
		sidebar.add(btnBuyScribe);

		frame.getContentPane().add(consoleScroller,BorderLayout.CENTER);
		frame.getContentPane().add(button,BorderLayout.SOUTH);

		updateDiamondThread = new Thread() {
			public void run() {
				try {
					while (true) {
						diamondsPerSecondOverflow+=diamondsPerSecond;
						while(diamondsPerSecondOverflow>=1) {
							drawDiamond();
							diamondsPerSecondOverflow--;
						}
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}

	public void drawDiamond() {
		diamonds++;
		allDiamonds++;
		logln("=Diamond "+ (allDiamonds) + "=");
		logln("   /\\   ");
		logln("  /  \\  ");
		logln(" /    \\ ");
		logln(" \\    /  ");
		logln("  \\  /   ");
		logln("   \\/   ");
		diamondNumberLabel.setText("Diamonds: "+diamonds);
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
		updateDiamondThread.run();
	}
}
