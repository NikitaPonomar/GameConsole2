package org.example;

import java.util.Map;
import java.util.Scanner;

public class GameConsole<T extends Player> {
	private final Game<T> game;
	private static final Scanner scanner = new Scanner(System.in);

	public GameConsole(Game<T> game) {
		this.game = game;
	}

	public void addPlayer() {
		System.out.print("Enter player 1 name: ");
		String player1Name = scanner.nextLine();
		game.addPlayer(player1Name);
		System.out.print("Enter player 2 name: ");
		String player2Name = scanner.nextLine();
		game.addPlayer(player2Name);
	}

	public void playGame() {
		Map<Character, GameAction> gameActions = game.getGameActions();
		int currentPlayerIndex = 0;
		while (true) {
			System.out.println("Available game options:");
			for (GameAction action : gameActions.values()) {
				System.out.println(action.key() + ": " + action.prompt());
			}
			System.out.print(game.players.get(currentPlayerIndex).getName() + ", select an option: ");
			char input = scanner.next().charAt(0);
			boolean continuePlaying = game.executeGameAction(input, currentPlayerIndex);
			if (!continuePlaying) {
				scanner.close();
				break;
			}
			currentPlayerIndex = (currentPlayerIndex + 1) % game.players.size(); // Switch players
		}
	}
}



