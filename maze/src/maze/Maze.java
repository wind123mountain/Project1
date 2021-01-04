package maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Maze extends JPanel implements Runnable {
	
	private static final long serialVersionUID = -3717503480014665676L;
	private cell[][] maze;
	private int x, y;
	private int es = 25;
	private int offset = 37;
	public static cell search[];
	private Thread thread = null;
	private cell temp = null;
	private int sizeSearch = 0;
	private int count = 0;
	private boolean isSearch = false;
	private int speed = 2;
	private boolean isCompleted = false;
	private Graphics2D g2d;
	private ImageIcon player = new ImageIcon("player.png");
	private JLabel cd = new JLabel("So buoc co lai: ");
	
	
	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Maze(int x, int y) {
		this.x = x;
		this.y = y;
		maze = new cell[x][y];
		for(int i=0; i<x; i++)
			for(int j=0; j<y;j++)
				maze[i][j] = new cell(i,j);
		add(cd);
		
		generate();

	}
	
	public void generate() {
		Stack<cell> stack = new Stack<cell>();
		int x=0;
		int y=0;
		cell current = maze[x][y];
		current.setVisited(true);
		stack.add(current);
		cell next;
		
		while (!stack.isEmpty()) {
			cell c = rndcell(current);
			if(c != null) {
				int cx = c.getX();
				int cy = c.getY();
				next = maze[cx][cy];
				
				if(x < cx) {
					next.removeWall(cell.walls.left);
					current.removeWall(cell.walls.right);
				} else if(y < cy) {
					next.removeWall(cell.walls.top);
					current.removeWall(cell.walls.bottom);
				}else if (x > cx) {
					next.removeWall(cell.walls.right);
					current.removeWall(cell.walls.left);
				} else if (y > cy) {
					next.removeWall(cell.walls.bottom);
					current.removeWall(cell.walls.top);
				}
				
				stack.add(next);
				next.setVisited(true);
				x=cx;
				y=cy;
				current = maze[x][y];
			}
			else {
				next = stack.pop();
				x=next.getX();
				y=next.getY();
				current = maze[x][y];
			}
		}
		
		maze[0][0].removeWall(cell.walls.left);
		maze[this.x-1][this.y-1].removeWall(cell.walls.right);
		
	}
	
	public cell rndcell(cell c) {
		int x=c.getX();
		int y=c.getY();
		ArrayList<cell> nextcell = new ArrayList<cell>();
		if(x+1 < this.x && !maze[x+1][y].isVisited())
			nextcell.add(maze[x+1][y]);
		if(x-1 >= 0 && !maze[x-1][y].isVisited())
			nextcell.add(maze[x-1][y]);
		if(y+1 < this.y && !maze[x][y+1].isVisited())
			nextcell.add(maze[x][y+1]);
		if(y-1 >= 0 && !maze[x][y-1].isVisited())
			nextcell.add(maze[x][y-1]);
		cell ncell = null;
		if(!nextcell.isEmpty()) {
			Random rnd = new Random();
			ncell = nextcell.get(rnd.nextInt(nextcell.size()));
		}
		return ncell;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		draw(g2d);
		if(!isSearch && !isCompleted)
			player.paintIcon(this, g2d,offset+1, offset+1);
		if(isSearch) {
			for(int i= (sizeSearch-1);i>count;i--) {
				//count = i;
				temp = search[i];
				g2d.setPaint(Color.LIGHT_GRAY);
				g2d.drawRect(temp.getX()*es+offset+12, temp.getY()*es+offset+12, 1, 1);
			}
			player.paintIcon(this, g2d, search[count].getX()*es+offset+1, search[count].getY()*es+offset+1);
		}
		else if(isCompleted) {
			for(int i= (sizeSearch-1);i>0;i--) {
				temp = search[i];
				g2d.setPaint(Color.red);
				g2d.drawRect(temp.getX()*es+offset+12, temp.getY()*es+offset+12, 1, 1);
			}
			player.paintIcon(this, g2d, search[0].getX()*es+offset+1, search[0].getY()*es+offset+1);
		}
			
	}
	
	private void draw(Graphics2D g2d) {
		
		g2d.setStroke(new BasicStroke(5));
		for(int i= 0;i<this.x;i++) {
			for (int j = 0; j < this.y; j++) {
				if (maze[i][j].isL())
					g2d.drawLine(i * es + offset, j * es + offset, i * es + offset, (j + 1) * es + offset);
				if (maze[i][j].isR())
					g2d.drawLine((i + 1) * es + offset, j * es + offset, (i + 1) * es + offset, (j + 1) * es + offset);
				if (maze[i][j].isB())
					g2d.drawLine(i * es + offset, (j + 1) * es + offset, (i + 1) * es + offset, (j + 1) * es + offset);	
				if (maze[i][j].isT())
					g2d.drawLine(i * es + offset, j * es + offset, (i + 1) * es + offset, j * es + offset);
			}
		}
		
	}
	
	
	
	public void search() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Stack<cell> Sstack =  new Stack<cell>();
		for (int i = 0; i < this.x; i++) 
			for (int j = 0; j < this.y; j++)
				maze[i][j].setVisited(false);
		
		cell current = maze[0][0];
		current.setVisited(true);
		Sstack.add(current);
		cell next = null;
		
		while (current != maze[this.x-1][this.y-1]) {
			if(!current.isB()&& current.getY()+1 < this.y && !maze[current.getX()][current.getY()+1].isVisited())
				next = maze[current.getX()][current.getY()+1];
			else if(!current.isL()&& current.getX() > 0 && !maze[current.getX()-1][current.getY()].isVisited())
				next = maze[current.getX()-1][current.getY()];
			else if(!current.isR()&& current.getX()+1 < this.x && !maze[current.getX()+1][current.getY()].isVisited())
				next = maze[current.getX()+1][current.getY()];
			else if(!current.isT()&& current.getY()>0 && !maze[current.getX()][current.getY()-1].isVisited())
				next = maze[current.getX()][current.getY()-1];
			else 
				next = null;
			
			if(next == null) {
				current = Sstack.pop();
			}
			else {
				if(Sstack.peek() != current)
					Sstack.add(current);
				Sstack.add(next);
				next.setVisited(true);
				current = next;
			}
		}
		search = new cell[Sstack.size()];
		int i = 0;
		while(!Sstack.isEmpty()) {
			search[i++] = Sstack.pop();
		}
		
		 sizeSearch = search.length;
		for(i= (sizeSearch-1);i>=0;i--) {
			count = i;
			try {
                Thread.sleep(speed*100);
            } catch (InterruptedException ex) {
                System.out.println("error here: replayPanel in run() method");
            }
			cd.setText("So buoc co lai: "+Integer.toString(i));
			repaint();
		}
		isSearch = false;
		isCompleted = true;
		System.gc();
		thread = null;
	}

}
