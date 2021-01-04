package Caro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.*;

import AI.*;

public class Main extends JPanel implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    public static int SIZEX = GameState.size;
    public static int SIZEY = GameState.size;
    public static int OFFSET = 18;
    private static final int WEIGHT = 725, HEIGHT = 780;
    private static final int SQUAD = 32;
    private static GameState gameState;
    public static JFrame frame = new JFrame();

    public Main(){
        gameState = new GameState(Player.X);
        gameState.addSquare(new Square(SIZEX / 2, SIZEY / 2));
        addMouseMotionListener(this);
        addMouseListener(this);

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= SIZEX; i++) {
            g.drawLine(SQUAD * i + OFFSET, OFFSET, SQUAD * i + OFFSET, SQUAD * SIZEY + OFFSET);
        }
        for (int i = 0; i <= SIZEY; i++) {
            g.drawLine(OFFSET, SQUAD * i + OFFSET, SQUAD * SIZEX + OFFSET, SQUAD * i + OFFSET);
        }
        
        ArrayList<Piece> move = gameState.getMove();
        for (int i = 0; i < move.size(); i++) {
            Piece piece = move.get(i);
            piece.getPlayer().getIcon().paintIcon(this, g, SQUAD * piece.getSquare().getCoordX() + OFFSET+4, SQUAD*piece.getSquare().getCoordY() + OFFSET+4);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        int coordX = (e.getX() - OFFSET) / SQUAD;
        int coordY = (e.getY() - OFFSET) / SQUAD;
        //System.out.println(coordX + " " + coordY);
        Square square = new Square(coordX, coordY);
        if (e.getButton() == 1 && gameState.getCurrentPlayer() == Player.O) {
            int[][] board = gameState.getBoard();
            if (board[coordX][coordY] == 0) {
                gameState.addSquare(square);
                EvalueCell nextCell = AlphaBetaPrunning.AlphaBetaSearch(gameState);
                if(nextCell.getSquare() != null)
                	gameState.addSquare(nextCell.getSquare());
                repaint();
               // System.out.println("kk");
            }
            
            if(HeuristicFunction.checkVictory(gameState)) {
            	Square curr = gameState.lastMove();
            	int currX = curr.getCoordX();
            	int currY = curr.getCoordY();
            	if(board[currX][currY]==1)
            		JOptionPane.showMessageDialog(null,"\t \t \t X WIN","Caro",JOptionPane.PLAIN_MESSAGE);
            	if(board[currX][currY]==2)
            		JOptionPane.showMessageDialog(null,"\t \t \t O WIN","Caro",JOptionPane.PLAIN_MESSAGE);
            	reset();
            }
        }
        
    }
    
    

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    
    public static void main(String[] ags) {
        System.out.println("play");
    
        JPanel menu = new JPanel();
        
        JButton restart = new JButton("Restart");
        restart.setPreferredSize(new Dimension(81,26));
        restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset();
			}
		});
        JButton undo = new JButton("Undo");
        undo.setPreferredSize(new Dimension(81,26));
        undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameState.undo();
				frame.repaint();
			}
		});
        
        JButton level1 = new JButton("Easy");
        level1.setPreferredSize(new Dimension(81,26));
        level1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlphaBetaPrunning.DEPTH = 1;
				reset();
			}
		});
        JButton level2 = new JButton("Medium");
        level2.setPreferredSize(new Dimension(81,26));
        level2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlphaBetaPrunning.DEPTH = 2;
				reset();
			}
		});
        JButton level3 = new JButton("Hard");
        level3.setPreferredSize(new Dimension(81,26));
        level3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AlphaBetaPrunning.DEPTH = 3;
				reset();
			}
		});
        
//        menu.add(level1);
//        menu.add(level2);
//        menu.add(level3);
        menu.add(undo);
        menu.add(restart);
        
        frame.add(menu,BorderLayout.NORTH);
        frame.add(new Main());
       
        frame.setTitle("Caro");
        frame.setSize(WEIGHT, HEIGHT);
        frame.setBackground(Color.GRAY);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void reset() {
    	int choose = JOptionPane.showConfirmDialog(null, "Restart game?", "Caro", JOptionPane.YES_NO_OPTION);
        if(choose == 0) {
        	gameState = new GameState(Player.X);
    		gameState.addSquare(new Square(SIZEX / 2, SIZEY / 2));
    		frame.repaint();
        }
    }

}
