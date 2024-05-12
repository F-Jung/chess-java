package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import game_board.Board;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override 
	public String toString() {
		return " K";
	}
}
