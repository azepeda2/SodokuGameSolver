
public class Driver {

	public static void main(String[] args) {
		//creates a new game and calls play
		Player p = new Player(null);
		Board b = new Board();
		Game g = new Game(b,p);
		g.play();
	}
}
