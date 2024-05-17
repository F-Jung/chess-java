package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import game_board.Board;
import game_board.Position;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;
	
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
		    
		    //especial move  white en passant 
		    if (position.getRow() == 3) {
		    	Position left = new Position(position.getRow(), position.getColumn() - 1);
		    	if (getBoard().positionExists(left) && isThereOpponentPiece(left) && 
		    			getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
		    		mat[left.getRow() - 1][left.getColumn()] = true;
		    	}
		    	Position right = new Position(position.getRow(), position.getColumn() + 1);
		    	if (getBoard().positionExists(right) && isThereOpponentPiece(right) && 
		    			getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
		    		mat[right.getRow() - 1][right.getColumn()] = true;
		    	}
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
		    

		    //especial move black en passant 
		    if (position.getRow() == 4) {
		    	Position left = new Position(position.getRow(), position.getColumn() - 1);
		    	if (getBoard().positionExists(left) && isThereOpponentPiece(left) && 
		    			getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
		    		mat[left.getRow() + 1][left.getColumn()] = true;
		    	}
		    	Position right = new Position(position.getRow(), position.getColumn() + 1);
		    	if (getBoard().positionExists(right) && isThereOpponentPiece(right) && 
		    			getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
		    		mat[right.getRow() + 1][right.getColumn()] = true;
		    	}
		    }
		}
		return mat;
	}
	
}
