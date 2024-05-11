package application;

import chess.ChessMatch;
import game_board.Board;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch(); 
		UI.printBoard(chessMatch.getPieces());
	}
}
