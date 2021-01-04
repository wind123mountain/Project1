package maze;

public class cell {
	
	public enum walls {left, right, top, bottom};
	private boolean visited;
	private boolean l, r, t, b;
	private int x, y;
	
	public cell(int x, int y) {
		super();
		this.visited = false;
		this.l = true;
		this.r = true;
		this.t = true;
		this.b = true;
		this.x = x;
		this.y = y;
	}
	
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean isL() {
		return l;
	}
	public void setL(boolean l) {
		this.l = l;
	}
	public boolean isR() {
		return r;
	}
	public void setR(boolean r) {
		this.r = r;
	}
	public boolean isT() {
		return t;
	}
	public void setT(boolean t) {
		this.t = t;
	}
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void removeWall(walls w){
		switch (w) {
		case left: 
			this.l = false;
			break;
		case right:
			this.r = false;
			break;
		case top:
			this.t = false;
			break;
		case bottom:
			this.b = false;
			break;
		}
		this.visited = true;
	}
	
}
