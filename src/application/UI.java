package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BOLD = "\u001b[1m";
	
	
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_BROWN_BACKGROUND = "\u001B[48;5;130m";
	public static final String ANSI_LIGHT_YELLOW_BACKGROUND = "\u001B[48;5;226m";
	
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Invalid ChessPosition. "
					+ "Valid values range from a1 to h8.");
		}
	}
	
	
	public static void printBoard(ChessPiece[][] pieces) {
		System.out.println();
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {

				if ((i + j) % 2 == 0) {
					System.out.print( ANSI_BROWN_BACKGROUND + ANSI_BOLD 
							 + displayPiece(pieces[i][j])+ ANSI_RESET );
				} else {
					System.out.print( "\u001b[48;2;128;0;0m" + ANSI_BOLD 
							+ displayPiece(pieces[i][j])+ ANSI_RESET );					
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("   a b c d e f g h");
	}
	
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn "+ chessMatch.getTurn()+": "+chessMatch.getCurrentPlayer() + "'s move");
		if (chessMatch.getCheck()) {
			System.out.println("CHECK!");
		}
	}
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves, ChessPosition source) {
		System.out.println();
		int n = pieces.length;
		String[][] backgroundColor = new String[n][n];
		ChessPiece sourcePiece = pieces[source.toPosition().getRow()][source.toPosition().getColumn()];

		for (int i = 0; i < n; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < n; j++) {

				if ((i + j) % 2 == 0) {

					backgroundColor[i][j] = possibleMoves[i][j] ? "\u001B[48;5;33m" : ANSI_BROWN_BACKGROUND;
					
					if (pieces[i][j] != null &&  possibleMoves[i][j] ) {
						if (pieces[i][j].getColor() != sourcePiece.getColor()) {
							backgroundColor[i][j] = "\u001b[48;2;230;0;0m";
						}
					}
					
					if (i == source.toPosition().getRow() && j == source.toPosition().getColumn()) {
						backgroundColor[i][j] = ANSI_GREEN_BACKGROUND;
					}
					System.out.print(backgroundColor[i][j] + displayPiece(pieces[i][j]) + ANSI_RESET);
					
				} else {
					backgroundColor[i][j] = possibleMoves[i][j] ? ANSI_BLUE_BACKGROUND : "\u001b[48;2;128;0;0m";
					
					if (pieces[i][j] != null &&  possibleMoves[i][j] ) {
						if (pieces[i][j].getColor() != sourcePiece.getColor()) {
							backgroundColor[i][j] = "\u001b[48;2;230;0;0m";
						}
					}
					
					if (i == source.toPosition().getRow() && j == source.toPosition().getColumn()) {
						backgroundColor[i][j] = ANSI_GREEN_BACKGROUND;
					}
					System.out.print(backgroundColor[i][j] + displayPiece(pieces[i][j]) + ANSI_RESET);
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("   a b c d e f g h");
	}
	
	private static String displayPiece(ChessPiece piece) {
		String str;
		if (piece == null) {
			str = "  ";
		} else {
			if (piece.getColor() == Color.WHITE) {
				str =  ANSI_WHITE + ANSI_BOLD + piece;
						 
			} else {
				str = ANSI_YELLOW + ANSI_BOLD + piece;
			}
		}
		return str;
	}

	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());
		System.out.println("Captured Pieces");

		//System.out.println(ANSI_WHITE + Arrays.toString(white.toArray()) + ANSI_RESET);
		//System.out.println(ANSI_YELLOW + Arrays.toString(black.toArray()) + ANSI_RESET);
		
		String capturedWhite = "";
		String capturedBlack= "";
		
		for (ChessPiece x : white) {
			capturedWhite += x;
		}
		for (ChessPiece x : black) {
			capturedBlack += x;
		}
		System.out.println(ANSI_YELLOW +capturedBlack + ANSI_RESET);
		System.out.println(ANSI_WHITE + capturedWhite + ANSI_RESET);
	} 
	

	
	
	
	
	
}
