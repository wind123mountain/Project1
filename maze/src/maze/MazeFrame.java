package maze;

import java.awt.*;
import javax.swing.*;

public class MazeFrame extends JFrame {
	
	private static final long serialVersionUID = -3717503480014665676L;
	public static int hight = 25;
	public static int weight = 37;
	
	public Maze maze = new Maze(weight, hight);
	
	public MazeFrame() {
		
		//int i=9;
		JButton b1 = new JButton("SEARCH");
		JButton b2 = new JButton("RESET");
		JButton b3 = new JButton("Speed-1");
		JButton b4 = new JButton("Speed+1");
		//JLabel cd = new JLabel(Integer.toString(i));
		b1.setPreferredSize(new Dimension(200,35));
		b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearch(evt);
            }
        });
		b2.setPreferredSize(new Dimension(200,35));
		b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReset(evt);
            }
        });
		b3.setPreferredSize(new Dimension(90,35));
		b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSpeed1(evt);
            }
        });
		b4.setPreferredSize(new Dimension(90,35));
		b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSpeed2(evt);
            }
        });
	
		add(maze);
		JPanel panel = new JPanel();
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		//panel.add(cd);
		
		add(panel,BorderLayout.SOUTH);
		
		setTitle("MAZE");
		setSize((weight + 3)*25+5 , (hight + 6)*25);
		setLocationRelativeTo(null);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void bReset(java.awt.event.ActionEvent evt) {
		dispose();
		new MazeFrame();
	}
	
	public void bSearch(java.awt.event.ActionEvent evt) {
		maze.setSearch(true);
		maze.search();
	}
	
	public void bSpeed1(java.awt.event.ActionEvent evt) {
		maze.setSpeed(maze.getSpeed()+1);
	}
	
	public void bSpeed2(java.awt.event.ActionEvent evt) {
		if(maze.getSpeed()-1>0)
			maze.setSpeed(maze.getSpeed()-1);
	}

	public static void main(String[] args) {
		new MazeFrame();
	}

}
