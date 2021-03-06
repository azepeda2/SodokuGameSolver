
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the Board.  
 * 
 *
 */

public class Board {
	
	public static final int ROWS = 9;
	public static final int COLS = 9;
	public static final int SUBSQUARELEN = 9;
	
	private Square[][] board;
	
	
	/**
	 * The Board constructor initializes the board array.
	 */
	public Board() {
		
		board = new Square[ROWS][COLS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				Square s = new Square();
				board[i][j] = s;
			}
		}
	}
		

		
	
	/**
	 * Returns true if every row contains all numbers 1-9.
	 * @return
	 */
	public boolean checkRows() {
	    /* The test array initializes boolean values that will be used set to true if the number is found in the set squares. */
		boolean[][] test;
		test = new boolean[ROWS][COLS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				test[i][j] = false;
			}
		}
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(board[i][j].getValue() > 0 && board[i][j].getValue() <= ROWS) {
					test[i][board[i][j].getValue() - 1] = true;
				}else { 
					return false;
				}
				
				/*Use this to print out the numbers that are stored and see if there are errors. If not, disregard this piece of code.
				 * System.out.println(board[i][j].getValue());
				 */
			}
		}
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(test[i][j] == false)
					return false;
			}
		}
			
		return true;
	}

	public boolean checkCols() {
	    /* The test array initializes boolean values that will be used set to true if the number is found in the set squares. */
		boolean[][] test;
		test = new boolean[ROWS][COLS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				test[j][i] = false;
			}
		}
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(board[j][i].getValue() > 0 && board[j][i].getValue() <= ROWS) {
					test[board[j][i].getValue() - 1][i] = true;
				}else { 
					return false;
				}
				
				/*Use this to print out the numbers that are stored and see if there are errors. If not, disregard this piece of code.
				 * System.out.println(board[i][j].getValue());
				 */
			}
		}
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(test[j][i] == false)
					return false;
			}
		}
			
		return true;
	}

	
	/**
	 * Displays the board. 
	 */
	public void display() {
		for(int i = 0; i < ROWS; i++) {
			
			System.out.print((i+1) + " [ ");
			for(int j = 0; j < COLS; j++) {
				if(j == COLS-1) {
					System.out.print(this.board[i][j] + " ] ");
				} else if((j+1)%3 == 0) {
					System.out.print(this.board[i][j] + " || ");					
				} else {
					System.out.print(this.board[i][j] + " | ");
				}
			}
			System.out.println();
			if((i+1)%3 == 0) {
				System.out.println("   -----------  -----------  -----------");
			}
		}
		System.out.println("  =======================================");
		System.out.println("  | 1 | 2 | 3 || 4 | 5 | 6 || 7 | 8 | 9 |");
	}
	
	/**
	 * Helper method for testing checkRows method.
	 */
	
	public boolean fillBoard(String filename) {
		//ask for help
        Scanner scan = null;
        int row = 1;
        int col = 0;
        try {
        	scan = new Scanner(new FileReader(filename));
            
            while(scan.hasNext()) {
            	col = 0;
                String line = scan.nextLine();
                String[] array = line.split("\\|");
                
                for(int i = 1; i < array.length; i++) {	
                	int num;
                	try {
                		num = Integer.parseInt(array[i]);
                		board[row - 1][col].setValue(num);
                		board[row - 1][col].setOriginal(true);
                		board[row - 1][col].setSet(true);
                		col++;
                		
                		
                	} catch(NumberFormatException nfe) {
                		if(array[i].trim().equals("")) {
                			col++;
                   		}
                	}
                }
                row = row + 1;
            }
        } catch(IOException ioe) { 
            System.out.println(ioe.getMessage());
        } finally {
            scan.close();
        }
		
		return true;
	}
	
	public boolean isFull() {
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(board[j][i].getSet() == false) {
					return false;
				} 	
			}
		}
		return true;
	}
	public boolean isOriginal(Choice c) {
		//ask for help
		if(board[c.getRow()][c.getCol()].getOriginal() == false) {
			return false;
		}			
		return true;
	}
	
	public void setSquare(Choice c) {
		board[c.getRow()][c.getCol()].setValue(c.getValue());
		board[c.getRow()][c.getCol()].setSet(true);
		board[c.getRow()][c.getCol()].setOriginal(true);
	}
	
	public boolean check() {
		if(checkCols() && checkRows() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public void initializeCorrect() {
		//initialize each row with 1-9 (in order)
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				board[i][j].setValue((j+1));
			}
		}
		
		//randomize numbers
		Random r = new Random();
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				int toswap = r.nextInt(COLS);
				Square tmp = board[i][j];
				board[i][j] = board[i][toswap];
				board[i][toswap] = tmp;
			}
		}
		
	}
	
	/**
	 * Helper method for testing checkRows method.
	 */	
	public void initializeIncorrect() {
		
		//start with a correct board
		initializeCorrect();
		
		Random r = new Random();
		for(int i = 0; i < ROWS; i++) {
			//choose a random value
			int value = r.nextInt(ROWS);
			//choose two random cells and put the same
			//value in both cells
			int col1 = r.nextInt(COLS);
			int col2 = r.nextInt(COLS);
			
			board[i][col1].setValue(value);
			board[i][col2].setValue(value);
		}
	}
	
	public boolean checkSubSquares() {
		
		int count;
		boolean[] test;
		for(int i = 0; i < ROWS; i =i + 3) {
			for(int j = 0; j < COLS; j = j + 3) {
				test = new boolean[SUBSQUARELEN];
				count = 0;
				for(int y = i; y < (i + 3); y++) {
					for(int z = j; z < (j + 3); z++) {
						if(board[y][z].getValue() > 0 && board[y][z].getValue() <= ROWS) {
							test[board[j][i].getValue() - 1] = true;
						}
						System.out.println(y + ", " + z);
						System.out.println(test[count]);
						count++;
					}
				
				} 
				count = 0;
				for(int y = i; y < (i + 3); y++) {
					for(int z = j; z < (j + 3); z++) {
						if(test[count] == false) {
							return false;
						}
						
						System.out.println(y + ", " + z);
						System.out.println(test[count]);
						count++;
					}
				
				} 
			} 
		}
		return true; 
	}
	
	public static void main(String[] args) {
		
		
		Board b = new Board();
		b.initializeCorrect(); 
		if(b.checkRows()) {
			System.out.println("Check rows returned true for correct board.  Test passed!");
		} else {
			System.out.println("Check rows returned false for correct board.  Time for more debugging!");
		}

		b.initializeIncorrect(); 
		if(b.checkRows()) {
			System.out.println("Check rows returned true for incorrect board.  Time for more debugging!");
		} else {
			System.out.println("Check rows returned false for incorrect board.  Test passed!");
		}

	}
}
