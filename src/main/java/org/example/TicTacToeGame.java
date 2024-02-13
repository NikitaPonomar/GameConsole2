package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class TicTacToeGame extends Game<TicTacToePlayer> {
	private static char[][] board = new char[3][3];
	private static final Scanner scanner2 = new Scanner(System.in);

	public TicTacToeGame(String gameName) {
		super(gameName, new ArrayList<>(), createGameActions());
		initializeBoard();
	}

	private void initializeBoard() {
		IntStream.range(0, 3).forEach(i -> IntStream.range(0, 3).forEach(j -> board[i][j] = '-'));
	}

	public static void displayBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public TicTacToePlayer createNewPlayer(String name) {
		return new TicTacToePlayer(name);
	}

	@Override
	public Map<Character, GameAction> getGameActions() {
		return createGameActions();
	}

	private static Map<Character, GameAction> createGameActions() {
		Map<Character, GameAction> actions = new HashMap<>();
		actions.put('1', new GameAction('1', "Place X or O on the board", playerIndex -> {
			displayBoard();
			System.out.print("Enter row number: ");
			int row = scanner2.nextInt() - 1;
			System.out.print("Enter column number: ");
			int col = scanner2.nextInt() - 1;
			char symbol = '-';
			if (board[row][col] == '-') {
				if (playerIndex == 0) {
					symbol = 'X';
				} else {
					symbol = 'O';
				}
				board[row][col] = symbol;
				return checkWin(symbol);
			} else {
				System.out.println("Cell already occupied. Please choose another cell.");
				return true;
			}
		}));
		actions.put('q', new GameAction('q', "Quit game", playerIndex -> {
			System.out.println("Quitting the game");
			return false; // Return false to end the game
		}));

		return actions;
	}

	private static boolean checkWin(char symbol) {
		// Check rows, columns and diagonals for winning positions
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
				System.out.println("Player with symbol " + symbol + " wins!");
				scanner2.close();
				return false;
			}
			if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
				System.out.println("Player with symbol " + symbol + " wins!");
				scanner2.close();
				return false;
			}
		}
		if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
				(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
			System.out.println("Player with symbol " + symbol + " wins!");
			scanner2.close();
			return false;
		}
		return true;
	}
}
