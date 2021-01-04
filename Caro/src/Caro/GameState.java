package Caro;

import java.util.ArrayList;
import java.util.Arrays;


public class GameState {
	private Player currentPlayer;
	private ArrayList<Piece> move;
	private int[][] board;
	public static final int size = 21;

	public GameState(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		move = new ArrayList<Piece>();
		board = new int[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				board[i][j]=0;
	}

	public void addSquare(Square square) {
		int coordX = square.getCoordX();
		int coordY = square.getCoordY();
		board[coordX][coordY] = currentPlayer.getHashValue();
		move.add(new Piece(square, currentPlayer));
		currentPlayer = currentPlayer.getOpponent();
	}

	public void removeSquare(Square square) {
		int coordX = square.getCoordX();
		int coordY = square.getCoordY();
		board[coordX][coordY] = 0;
		move.remove(move.size() - 1);
		currentPlayer = currentPlayer.getOpponent();
	}

	public boolean isInBoard(int coordX, int coordY) {
		if (coordX < 0 || coordX >= size) return false;
		if (coordY < 0 || coordY >= size) return false;
		return true;
	}
	
	public ArrayList<Square> getSuccessors() {
		ArrayList<Square> successors = new ArrayList<Square>();
		boolean[][] mark = new boolean [size][size];
		for (int i = 0; i < size; i++)
			Arrays.fill(mark[i], false);
		for (Piece piece : move) {
			int coordX = piece.getSquare().getCoordX();
			int coordY = piece.getSquare().getCoordY();
			for (int i = -2; i <= 2; i++)
				for (int j = -2; j <= 2; j++) {
					int dimX = coordX + i;
					int dimY = coordY + j;
					if (isInBoard(dimX, dimY) && board[dimX][dimY] == 0) 
						mark[dimX][dimY] = true;
				}	
		}
		for (int coordX = 0; coordX < size; coordX++)
			for (int coordY = 0; coordY < size; coordY++)
				if (mark[coordX][coordY])
					successors.add(new Square(coordX, coordY));
		return successors;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Piece> getMove() {
		return move;
	}

	public void setMove(ArrayList<Piece> move) {
		this.move = move;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public Square lastMove() {
		int n = move.size() - 1;
		Piece curent = move.get(n);
		return curent.getSquare();
	}
	
	public void undo() {
		int n = move.size() - 1;
		Piece curent = move.remove(n);
		int coordX = curent.getSquare().getCoordX();
		int coordY = curent.getSquare().getCoordY();
		board[coordX][coordY] = 0;
		currentPlayer = currentPlayer.getOpponent();
	}

}
