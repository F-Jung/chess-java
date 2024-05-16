package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch(); 
		List<ChessPiece> captured = new ArrayList<>();
		
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				//System.out.print( "\u001B[32m" + "Source: " + "\u001B[0m");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source); 
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves, source);
				System.out.println();
				System.out.print("Source: " + source);
				//System.out.print("\u001B[32m" + "Source: " + "\u001B[0m" + source);
				System.out.println();
				System.out.print("Target: ");
				//System.out.print("\u001B[38;5;33m" + "Target: " + "\u001B[0m");
				ChessPosition target = UI.readChessPosition(sc);
				

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			
			catch (ChessException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.print(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		
	}
}
