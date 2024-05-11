package game_board;

public class Piece {
	
	protected Position position; 
	private Board board;
	
	public Piece(Board board) {
		super();
		this.board = board;
		position = null; //(this declaration is not necessary)
	}
	
	protected Board getBoard() {
		return board;
	}

	
	
	
}
