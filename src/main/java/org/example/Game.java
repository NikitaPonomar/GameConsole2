package org.example;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Game<T extends Player> {
	private String gameName;
	 List<T> players;
	private Map<Character, GameAction> gameActions;

	public Game(String gameName, List<T> players, Map<Character, GameAction> gameActions) {
		this.gameName = gameName;
		this.players = players;
		this.gameActions = gameActions;
	}

	public abstract T createNewPlayer(String name);

	public abstract Map<Character, GameAction> getGameActions();

	public final int addPlayer(String name) {
		T player = createNewPlayer(name);
		players.add(player);
		return players.indexOf(player);
	}

	public boolean executeGameAction(char key, int playerIndex) {
		if (gameActions.containsKey(key)) {
			Predicate<Integer> action = gameActions.get(key).action();
			return action.test(playerIndex);
		}
		return false;
	}

	// Other concrete methods, getters, and helper methods
}


