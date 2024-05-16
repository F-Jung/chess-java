package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import game_board.Board;
import game_board.Position;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override 
	public String toString() {
		return " P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getRows()];
		
		Position p = new Position(0,0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow()-1, position.getColumn());
		    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		    
		    Position q = new Position(position.getRow()-2, position.getColumn());
		    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
		    		getBoard().positionExists(q) && !getBoard().thereIsAPiece(q) && getMoveCount() == 0 ) {
		    	mat[q.getRow()][q.getColumn()] = true;
		    }
		    p.setValues(position.getRow()-1, position.getColumn()-1);
		    if (getBoard().positionExists(p) && isThereOpponentPiece(p) ) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		    p.setValues(position.getRow()-1, position.getColumn()+1);
		    if (getBoard().positionExists(p) && isThereOpponentPiece(p) ) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		} else {
			p.setValues(position.getRow()+1, position.getColumn());
		    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		    
		    Position q = new Position(position.getRow()+2, position.getColumn());
		    if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) &&
		    		getBoard().positionExists(q) && !getBoard().thereIsAPiece(q) && getMoveCount() == 0 ) {
		    	mat[q.getRow()][q.getColumn()] = true;
		    }
		    p.setValues(position.getRow()+1, position.getColumn()-1);
		    if (getBoard().positionExists(p) && isThereOpponentPiece(p) ) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		    p.setValues(position.getRow()+1, position.getColumn()+1);
		    if (getBoard().positionExists(p) && isThereOpponentPiece(p) ) {
		    	mat[p.getRow()][p.getColumn()] = true;
		    }
		}
		return mat;
	}
	
}
