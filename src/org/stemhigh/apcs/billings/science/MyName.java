package org.stemhigh.apcs.billings.science;

public class MyName {
	private static GUI gui;
	public static void main(String[] args) {
		gui = new GUI();
		gui.go();

		gui.log("\"Jake");
		gui.log(" ");
		gui.logln("Billings\"");
		for (int i=0;i<9999;i++) {
			gui.drawDiamond();
		}
	}
}
