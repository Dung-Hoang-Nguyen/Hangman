package simpleGames;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;

public class Hangman extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static void main(String args[]) {
	SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new Hangman("ultilitarianism").setVisible(true);
        }
    });
}
boolean shouldDrawLine;
private JTextField input;
private JLabel display, wrong;
private Font big;
private String string, out;
private boolean newgame;
private ArrayList<String> guessed = new ArrayList<String>();
private ArrayList<String> unguessed = new ArrayList<String>();
private int p=0;
public Hangman(String str) {
	string = str;
	newgame=false;
	out = "";
	for (int i = 0; i < string.length(); i++) {
		if (string.charAt(i) == ' ') {
			out += " ";
		} 
		else {
			out += "_";
		}
		out += " ";
	}
	big = new Font(Font.MONOSPACED, Font.PLAIN, 20);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(1000, 0, 1000, 1000);
	setLayout(null);
    getContentPane().setBackground(Color.BLACK);
	
	display = new JLabel(out, SwingConstants.CENTER);
	display.setBounds(0, 700, 1000, 300);
	display.setForeground(Color.white);
	display.setFont(big);
	add(display);
	
	wrong = new JLabel();
	wrong.setBounds(10, 0, 1000, 45);
	wrong.setForeground(Color.white);
	add(wrong);
	
	input = new JTextField();
	input.setBounds(400, 75, 200, 30);
	input.setBackground(Color.darkGray);
	input.setForeground(Color.white);
	input.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String guess = input.getText();
			if (newgame) {
				string = guess;
				newgame=false;
				out = "";
				for (int i = 0; i < string.length(); i++) {
					if (string.charAt(i) == ' ') {
						out += " ";
					} else {
						out += "_";
					}
					out += " ";
				}
				wrong.setText("");
				guessed = new ArrayList<String>();
				unguessed = new ArrayList<String>();
				p=0;
				display.setText(out);
			}
			else {
			if (!guess.equals("")) {
				if (string.indexOf(guess) > -1 && guessed.indexOf(guess) < 0) {
					String str = string;
					int index = str.indexOf(guess);
					do {
						index = str.indexOf(guess);
						String outstring = out.substring(0, index * 2);
						for (int i = 0; i < guess.length(); i++) {
							outstring += guess.substring(i, i + 1) + " ";
						}
						outstring += out.substring(index * 2 + guess.length() * 2);
						out = outstring;
						str = str.substring(0, index);
						for (int i = 0; i < guess.length(); i++) {
							str += " ";
						}
						str += string.substring(index + guess.length());
					} while (str.indexOf(guess) > -1);
					guessed.add(guess);
					display.setText(out);
					String a=out.replaceAll("\\s+", "");
					if(a.equals(string)) {
						display.setText("Congratuations! You didn't leave her hanging! Choose new word!");newgame=true;
					}
				}
				else if (string.indexOf(guess) < 0 && unguessed.indexOf(guess) < 0) {
					unguessed.add(guess);
					wrong.setText(wrong.getText() + guess + " ");
					p++;
				}
				
				
			        
			}}repaint();input.setText("");
		}
	});
	
	add(input);
	setVisible(true);
}
@Override
public void paint(Graphics g) {
	super.paint(g);
	Graphics2D g2 = (Graphics2D) g;
	g2.setColor(Color.white);
	g2.setStroke(new BasicStroke(2));
	switch(p) {
	case 10:g2.drawLine(650, 525, 700, 600); display.setText("OOPS! She died. Try again with new word!"); newgame=true;
	case 9:g2.drawLine(650, 525, 600, 600);
	case 8:g2.drawLine(650,450,700,500);
	case 7:g2.drawLine(650,450,600,500);
	case 6:g2.drawLine(650,450,650,525);
	case 5:g2.drawOval(625, 400, 50, 50);
	case 4:g2.drawLine(650, 350, 650, 400);
	case 3:g2.drawLine(500, 350, 650, 350);
	case 2:g2.drawLine(500, 700, 500, 350);
	case 1:g2.drawLine(350, 700, 650, 700);
	}
}
}

