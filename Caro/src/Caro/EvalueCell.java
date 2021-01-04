package Caro;

public class EvalueCell {
	private Square square;
	private int value;
	
	public EvalueCell(Square square, int value) {
		super();
		this.square = square;
		this.value = value;
	}
	
	public Square getSquare() {
		return square;
	}
	
	public void setSquare(Square square) {
		this.square = square;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
}
