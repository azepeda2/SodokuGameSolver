

/**
 * The Game class contains the main logic of game play.
 * @author srollins
 *
 */
public class Game {
	private Board b;
	private Player p;

	/**
	 * The Game constructor first initializes the Board data member, the Player data member.  It then invokes the getFileName method of Player and passes the result to the fillBoard method of Board.
	 */
	public Game() {
		this.b = new Board();
		this.p = new Player(b);
		boolean success = this.b.fillBoard(this.p.getFileName());
		if(!success) {
			System.exit(1);
		}
	}
	
	/**
	 * The main logic of game play.  
	 * Basic algorithm:
	 * As long as the board is not full (1)	get another choice from the user and (2) set the square.  Once the board is full, check if the user has won and print an appropriate message.
	 * 
	 */
	public void play() {
		while(!b.isFull()) {
			b.display();
			if(p.giveUp().equals("yes") == true) {
				System.out.println("You gave up. Looking for a solution.");
				if(b.Solve()) {
					b.display();
					System.out.println("Solution found!");
				} else {
					System.out.println("No possible solution with current " +
							"filled squares! Soduku quitting...");
				}
				System.exit(0);
				
			}
			Choice c = p.getChoice();
			b.setSquare(c);
				
		}
		boolean win = b.check();
		if(win) {
			this.p.winMsg();
		} else {
			this.p.loseMsg();
		}
	}
	
	
}
