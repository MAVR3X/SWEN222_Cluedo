
public class GameController {

	GameInterface gameInterface;
	
	public GameController(){
		gameInterface = new GameInterface();
	}

	private void start() {
		// TODO Create game start
		
	}
	
	public static void main(String args[]){
		GameController game = new GameController();
		game.start();
		
	}
}