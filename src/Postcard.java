package mkpostkardclout.autaticlyrepaint;

import javax.swing.*;
import java.awt.*;

public class Postcard{
	JFrame frame;
	
	public static void main(String[] args) {
		Postcard card  = new Postcard();
		card.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel panel = new MyDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(450, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

