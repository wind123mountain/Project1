package AI;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Caro.*;

public class AlphaBetaPrunning {
	
	public static int DEPTH  = 2;
	
	public static EvalueCell AlphaBetaSearch(GameState gameState) {
		
		int depth = DEPTH;
		
		return MaxValue(gameState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
		
	}
	
	public static ArrayList<EvalueCell> getSuccessorSort(GameState gameState){
		ArrayList<Square> successors = gameState.getSuccessors();
        ArrayList<EvalueCell> successorSort = new ArrayList<EvalueCell>();
        for (Square square : successors) {
            gameState.addSquare(square);
            int f = HeuristicFunction.valueCell(gameState, gameState.getCurrentPlayer().getOpponent());
            successorSort.add(new EvalueCell(square, f));
            gameState.removeSquare(square);
        }
        
        Collections.sort(successorSort, new Comparator<EvalueCell>() {

			@Override
			public int compare(EvalueCell o1, EvalueCell o2) {
				// TODO Auto-generated method stub
				return o2.getValue() - o1.getValue();
		}});
        
        return successorSort;
	}
	
	public static EvalueCell MaxValue(GameState gameState, int alpha, int beta, int depth) {
		if(depth == 0 || HeuristicFunction.checkVictory(gameState)) {
			EvalueCell node = HeuristicFunction.valueState(gameState);
			//System.out.println("++"+node.getValue());	
			return node;
		}
			
		//ArrayList<Square> successors = gameState.getSuccessors();
		ArrayList<EvalueCell> successorSort = getSuccessorSort(gameState);
		int value = Integer.MIN_VALUE;
        Square bestSquare = null;
        for (EvalueCell cell : successorSort) {
            Square square = cell.getSquare();
            gameState.addSquare(square);
            EvalueCell tryMove = MinValue(gameState, alpha, beta, depth - 1);
            if (value < tryMove.getValue()) {
                value = tryMove.getValue();
                bestSquare = square;
            }
            if (value >= beta) {
                gameState.removeSquare(square);
                return new EvalueCell(bestSquare, value);
            }
            alpha = Math.max(alpha, value);
            gameState.removeSquare(square);
        }
        return new EvalueCell(bestSquare, value);
        
	}
	
	public static EvalueCell MinValue(GameState gameState, int alpha, int beta, int depth) {
		if(depth == 0 || HeuristicFunction.checkVictory(gameState)) {
			EvalueCell node = HeuristicFunction.valueState(gameState);
			//System.out.println("++"+node.getValue());	
			return node;
		}
		
		//ArrayList<Square> successors = gameState.getSuccessors();
		ArrayList<EvalueCell> successorSort = getSuccessorSort(gameState);
		int value = Integer.MAX_VALUE;
        Square bestSquare = null;
        for (EvalueCell cell : successorSort) {
            Square square = cell.getSquare();
            gameState.addSquare(square);
            EvalueCell tryMove = MaxValue(gameState, alpha, beta, depth - 1);
            if (value > tryMove.getValue()) {
                value = tryMove.getValue();
                bestSquare = square;
            }
            if (value <= alpha) {
                gameState.removeSquare(square);
                return new EvalueCell(bestSquare, value);
            }
            alpha = Math.min(alpha, value);
            gameState.removeSquare(square);
        }
        return new EvalueCell(bestSquare, value);
        
	}

}
