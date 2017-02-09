import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Player {

	private static final int ROWS = 9;
	private static final int COLS = 9;
	private Board board;

	public Player(Board board) {
		this.board= board;
	}

	public String getFileName() {
		Scanner scan = new Scanner(System.in);
		Scanner s;
		File file;
		System.out.println("Please enter a file name (remember to include file type eg. correct.txt): ");
		String filename = scan.nextLine();
		boolean workingFile = false;
		while (workingFile == false) {
			try{
				file = new File(filename);
				/* s does not but throw the exception if file not found for the purposes of this program */
				s = new Scanner(new FileReader(file));
				if(file.exists() == true){
					workingFile = true;
				}
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found! Please enter a file name (remember to include file type eg. correct.txt): ");
				filename = scan.nextLine();
				file = new File(filename);
				}
		}

			return filename;
		}

		public void winMsg() {
			System.out.println("You Win! You are the new Sodoku Master!");
		}

		public void loseMsg() {
			System.out.println("You Lose! Practice to improve your skills in the art of Sodoku!");
		}

		public Choice getChoice() {
			Scanner scan = new Scanner(System.in);
			int row;
			int col;
			int value;
			System.out.println("Please enter a row (1-9): ");
			row = scan.nextInt();

			while(row < 1 || row > ROWS) {
				System.out.println("Invalid Entry! Please enter a row (1-9): ");
				row = scan.nextInt();
			}
			System.out.println("Please enter a column (1-9): ");
			col = scan.nextInt();
			while(col < 1 || col > COLS) {
				System.out.println("Invalid Entry! Please enter a column (1-9): ");
				col = scan.nextInt();
			}
			System.out.println("Please enter the number you want to add (1-9): ");
			value = scan.nextInt();
			while(value < 1 || value > ROWS) {
				System.out.println("Invalid Entry! Please enter the number you want to add (1-9): ");
				value = scan.nextInt();
			}

			Choice c = new Choice(row - 1 ,col - 1,value);
			return c;
		}
	}
