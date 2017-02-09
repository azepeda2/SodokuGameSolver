

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the Board.  
 * 
 * @author srollins
 *
 */

public class Board {
	public static final int ROWS = 9;
	public static final int COLS = 9;
	
	private Square[][] board;
	
	
	/**
	 * The Board constructor initializes the board array.
	 */
	public Board() {
		this.board = new Square[ROWS][COLS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				this.board[i][j] = new Square();
			}
		}
	}
		
	/**
	 * Takes as input the filename, opens the file, reads in each line, sets the value for each Square in the array, and returns true if successful and false if not. 
	 * The method may not be successful in the event that the file does not exist or is not formatted correctly.
	 * @param filename
	 * @return
	 */

	public boolean fillBoard(String filename) {	
		
		Scanner scan = null;
		boolean toreturn = true;
		try {            
			scan = new Scanner(new FileReader(filename));
			int row = 0;

			while (scan.hasNext()) {
				String line = scan.nextLine();
		
				String[] nums = line.split("\\|");
				int col = 0;
		
				for (int i = 1; i < nums.length; i++) {
				    if(nums[i].trim().equals("")) {
						board[row][col].setValue(0);	
						col++;
						continue;
					}
					int val = Integer.parseInt(nums[i]);
					if(val >= 1 && val <= ROWS) {
						board[row][col].setValue(val);
						board[row][col].setOriginal(true);
					} else {
						//debug message
						System.out.println("Board.fillBoard::Invalid value in original board.");
						return false;
					}
					col++;
				}
				
				if(col != COLS) {
					//debug message
					System.out.println("Board.fillBoard::Invalid number of columns in original board.");
					return false;
				}
				row++;	
			}
			if(row != ROWS) {
				//debug message
				System.out.println("Board.fillBoard::Invalid number of rows in original board.");
				return false;				
			}
		} catch(IOException ioe) {
			//debug message
			System.out.println("Board.fillBoard::Error opening file.");			
			toreturn = false;
		} catch(NumberFormatException nfe) { 
			//debug message
			System.out.println("Board.fillBoard::Non-integer input found.");			
			toreturn = false;
		} finally {
			if(scan != null) {
				scan.close();
			}
		}
		return toreturn;
		
	}
	/**
	 * Returns true if the row and column specified by Choice c refer to a Square filled in the original board and false otherwise.
	 * @param c
	 * @return
	 */
	public boolean isOriginal(Choice c) {
		return this.board[c.getRow()][c.getCol()].isOriginal();
	}
	
	/**
	 * Sets the value of the Square specified by the row and column in c.
	 * @param c
	 * @return
	 */
	public boolean setSquare(Choice c) {
		if(this.board[c.getRow()][c.getCol()].isOriginal()) {
			return false;
		}
		this.board[c.getRow()][c.getCol()].setValue(c.getValue());
		return true;
	}
	
	/**
	 * Returns true if all Squares are set and false otherwise.
	 * Must iterate through every Square and return false if any are not set.
	 * @return
	 */
	public boolean isFull() {
		for(int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(!this.board[i][j].isSet()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns true if the solution is correct and false otherwise.
	 * A solution is correct if rows, columns, and subsquares are all correct.
	 * @return
	 */
	public boolean check() {
		return checkRows() && checkCols() && checkSquares();
		
	}
	/**
	 * Returns true if every row contains all numbers 1-9.
	 * @return
	 */
	private boolean checkRows() {
		for(int i = 0; i < ROWS; i++) {
			for (int num = 1; num <= ROWS; num++) {
				boolean found = false;
				for (int j = 0; j < COLS; j++) {
					if(this.board[i][j].getValue() == num) {
						found = true;
						break;
					}
				}
				if (!found) {
					return false;
				}
			}
		}
		return true;
		
	}

	/**
	 * Returns true if every column contains all numbers 1-9.
	 * @return
	 */
	private boolean checkCols() {

		for(int i = 0; i < COLS; i++) {
			for (int num = 1; num <= ROWS; num++) {
				boolean found = false;
				for (int j = 0; j < ROWS; j++) {
					if(this.board[j][i].getValue() == num) {
						found = true;
						break;
					}
				}
				if (!found) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns true if every subsquare contains all numbers 1-9.
	 * @return
	 */
	private boolean checkSquares() {
		for(int i = 0; i < ROWS; i+=3) {
			for(int j = 0; j < COLS; j+=3) {
				for (int num = 1; num <= ROWS; num++) {
					boolean found = false;
					for(int k = i; k < (i+3); k++) {
						for(int l = j; l < (j+3); l++) {
							if(this.board[k][l].getValue() == num) {
								found = true;
								break;
							}
						}						
					}
					if (!found) {
						return false;
					}
				}
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

	public ArrayList<Integer> findPossible(Location l) {
		boolean nums[] = new boolean[ROWS];
		int row = l.getRow();
		int col = l.getCol();
		
		for(int i = 0; i < ROWS; i++) {
			nums[i] = true;
		}
		
		for(int j = 0; j < ROWS; j++) {
			if(this.board[row][j].getValue() > 0 &&
					this.board[row][j].getValue() <= ROWS ) {
				//System.out.println("value row=" + row + " j= " + j + " =" + this.board[row][j].getValue());
				nums[(this.board[row][j].getValue() - 1)] = false;
			}
		}
		
		for(int i = 0; i < COLS; i++) {
			if(this.board[i][col].getValue() > 0 &&
					this.board[i][col].getValue() <= COLS ) {
				//System.out.println("value i=" + i + " col= " + col + " =" + this.board[i][col].getValue());
				nums[(this.board[i][col].getValue() - 1)] = false;
			}
		}
		
		int adjRow = ((row / 3) * 3);
		int adjCol = ((col / 3) * 3);
		//System.out.println("adjrow= " + adjRow + " adjcol= " + adjCol);
		for(int k = adjRow; k < (adjRow + 3); k++) {
			for(int m = adjCol; m < (adjCol + 3); m++) {
				if(this.board[k][m].getValue() > 0 &&
						this.board[k][m].getValue() <= ROWS) {
					//System.out.println("value k=" + k + " m= " + m + " =" + this.board[k][m].getValue());
					nums[(this.board[k][m].getValue() - 1)] = false;
				}
			}
		}
		
		
		ArrayList<Integer> list= new ArrayList<Integer>();
		for(int a = 0; a < ROWS; a++) {
			if(nums[a] == true) {
				list.add(a + 1);
			}
		}
		return list;
	}
	
	public void resetSquare(Location l) {
		this.board[l.getRow()][l.getCol()] = new Square();
	}
	
	public Location getLocation(int row) {
		//looks for next empty square starting at row of last empty square
		//to search more efficiently than starting at i=0 every time
		for(int i = row; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(!this.board[i][j].isSet()) {
					Location loc = new Location(i, j);
					return loc;
				}
			}
		}
		return null;
	}

	public boolean Solve() {
		Location l = getLocation(0);
		if(l == null) {
			// returns false if there is no empty square
			return false;
		}
		ArrayList<Integer> list = findPossible(l);
		if(list.isEmpty()) {
			//returns false if there is no possible integers for that square
			return false;
		}
		
		for(int i = 0; i < list.size(); i++) {
			Choice c = new Choice(l.getRow(), l.getCol(), list.get(i));
			this.setSquare(c);
			if(l.getRow() != 8 || l.getCol() != 8) {
				//recursively calls Solve if there is still a chance
				//of having an empty square, location 8, 8 is last square
				if(!Solve(l.getRow())) { 
					//calls solve and resets square if
					//the next square couldn't be set
					this.resetSquare(l);
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Solve(int row) {
		Location l = getLocation(row);
		if(l == null) {
			//returns true if no other square is empty
			return true;
		}
		ArrayList<Integer> list = findPossible(l);
		if(list.isEmpty()) {
			//returns false if there are no possible solutions
			return false;
		}
		System.out.println(list.toString());
		for(int i = 0; i < list.size(); i++) {
			Choice c = new Choice(l.getRow(), l.getCol(), list.get(i));
			this.setSquare(c);
			
			if(l.getRow() != 8 || l.getCol() != 8) {
				//recursively calls Solve if there is still a chance
				//of having an empty square, location 8, 8 is last square
				if(!Solve(l.getRow())) { 
					//calls solve and resets square if
					//the next square couldn't be set
					this.resetSquare(l);
				} else {
					return true;
				}
			} else {
				//last empty square was set
				return true;
			}
		}
		//returns false if could not set all of the squares
		return false;
	}
}
