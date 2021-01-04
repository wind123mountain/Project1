package AI;


import Caro.*;

public class HeuristicFunction {
	
	public static int weight[] = {10000,10000,500,50};
	
	public static String user[][] = {
			{"011110"},
			{"11111"},
			{"001110","011100","011010","010110"},
			{"001100"}
	};
	
	public static String cpu[][] = {
			{"022220"},
			{"22222"},
			{"002220","022200","022020","020220"},
			{"002200"}
	};
	
	public static int valueCell(GameState gameState, Player player) {
		int value = 0;
		String input ;
		String compare[][];
		int broad[][] = gameState.getBoard();
		Square square = null;
		for (int i =gameState.getMove().size() - 1; i != 0; i--) {
			if(gameState.getMove().get(i).getPlayer() == player) {
				square = gameState.getMove().get(i).getSquare();
			}
		}
		int x = square.getCoordX();
		int y = square.getCoordY();
		if(player == Player.X)
			compare = user;
		else
			compare = cpu;
		
		//ngang
		input = " ";
		for(int i=y-5; i<=y+5;i++) {
			if(0<=i && i< GameState.size)
				input += broad[x][i];
		}
		for(int i = 0; i< compare.length; i++)
			for(int j = 0; j<compare[i].length; j++)
				if(input.contains(compare[i][j])) {
//					if(i==0||i==1) {
//						//System.out.println("n");
//						return 30000;
//					}
					value += weight[i];
					break;
				}
		
		//doc
		input = " ";
		for(int i=x-5; i<=x+5;i++) {
			if(0<=i && i< GameState.size)
				input += broad[i][y];
		}
		for(int i = 0; i< compare.length; i++)
			for(int j = 0; j<compare[i].length; j++)
				if(input.contains(compare[i][j])) {
//					if(i==0||i==1) {
//						//System.out.println("d");
//						return 30000;
//					}
					value += weight[i];
					break;
				}
		
		//cheo1
		input = " ";
		for(int i=-5; i<=5;i++) {
			if((x+i)<GameState.size && (y+i)<GameState.size && (x+i)>=0 && (y+i)>= 0)
				input += broad[x+i][y+i];
		}
		for(int i = 0; i< compare.length; i++)
			for(int j = 0; j<compare[i].length; j++)
				if(input.contains(compare[i][j])) {
					//System.out.println(compare[i][j]+"--"+i+"  "+j);
//					if(i==0||i==1) {
//					//	System.out.println("c1");
//						return 30000;
//					}
					value += weight[i];
					break;
				}
		
		//cheo2
		input = " ";
		for(int i=-5; i<=5;i++) {
			if((x+i)<GameState.size && (y-i)<GameState.size && (x+i)>=0 && (y-i)>= 0)
				input += broad[x+i][y-i];
		}
		for(int i = 0; i< compare.length; i++)
			for(int j = 0; j<compare[i].length; j++)
				if(input.contains(compare[i][j])) {
//					if(i==0||i==1) {
//					//	System.out.println("c2");
//						return 30000;
//					}
					value += weight[i];
					break;
				}
		
			
		return value;
	}
	
	public static EvalueCell valueState (GameState gameState){
		
		int value = valueCell(gameState, Player.X) - valueCell(gameState, Player.O);
		
		return new EvalueCell(null,value);
		
	}
	
	
	
	public static boolean checkVictory(GameState gameState) {
		
		Square curr = gameState.lastMove();
		int x = curr.getCoordX();
		int y = curr.getCoordY();
		int board[][] = gameState.getBoard();
    	int c = board[x][y];
    	int count = 1;
    	
    	//ktra ngang
    	for(int i=x+1;i<GameState.size;i++) {
    		if(board[i][y]==c) {
    			count++;
    			if(count==5) {
    				return true;
    			}
    		}
    		else
    			break;
    	}
    	for(int j=x-1;j>=0;j--) {
			if(board[j][y]==c) {
    			count++;
    			if(count==5) {
    				return true;
    			}
    		}
			else
				break;
		}
    	//ktra doc
    	count = 1;
    	for(int i=y+1;i<GameState.size;i++) {
    		if(board[x][i]==c) {
    			count++;
    			//System.out.println(count);
    			if(count==5) {
    				return true;
    			}
    		}
    		else 
    			break;
    	}
    	for(int j=y-1;j>=0;j--) {
			if(board[x][j]==c) {
    			count++;
    			if(count==5) {
    				return true;
    			}
    		}
			else
				break;
		}
    	//ktra cheo
    	count = 1;
    	for(int i=1;i<6;i++) {
    		if((x+i)<GameState.size && (y+i)<GameState.size && board[x+i][y+i]==c) {
    			count++;
    			if(count==5) {
    				return true;
    			}
    		}
    		else {
    			for(int j=1;j<6;j++) {
    				if((x-j)>=0 && (y-j)>= 0 && board[x-j][y-j]==c) {
    	    			count++;
    	    			if(count==5) {
    	    				return true;
    	    			}
    	    		}
    				else
    					break;
    			}
    			break;
    		}
    	}
    	
    	count = 1;
    	for(int i=1;i<6;i++) {
    		if((x+i)<GameState.size && (y-i)>= 0 && board[x+i][y-i]==c) {
    			count++;
    			if(count==5) {
    				return true;
    			}
    		}
    		else {
    			for(int j=1;j<6;j++) {
    				if((x-j)>=0 && (y+j)<GameState.size && board[x-j][y+j]==c) {
    	    			count++;
    	    			if(count==5) {
    	    				return true;
    	    			}
    	    		}
    				else
    					break;
    			}
    			break;
    		}
    	}
    	
    	return false;
    }

}
