package Caro;

import javax.swing.ImageIcon;


public enum Player {
	X("X", 1), O("O", 2);
	
	private String iconName;
	private int hashValue;
	private ImageIcon icon;
	

	Player(String iconName, int hashValue) {
		this.iconName = iconName;
		this.hashValue = hashValue;
		this.icon = new ImageIcon(iconName+".png");
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public String getIconName() {
		return iconName;
	}

	public int getHashValue() {
		return hashValue;
	}

	public Player getOpponent() {
		if (this == X)
			return Player.O;
		return Player.X;
	}
}