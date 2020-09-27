package com.group.connect4.services;

import com.group.connect4.model.Connect4;

public interface Connect4Service {
	
	public Connect4 createNewGame();
	public Connect4 getGame(int gameId);
	public String move(int gameId, String color, int col);
	
}
