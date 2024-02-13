package org.example;

public class TicTacToePlayer implements Player {
	private String playerName;

	public TicTacToePlayer(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String getName() {
		return playerName;
	}
}


