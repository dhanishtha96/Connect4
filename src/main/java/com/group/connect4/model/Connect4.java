package com.group.connect4.model;

import com.group.connect4.enums.Color;
import com.group.connect4.enums.GameStatus;

public class Connect4 {

	private int gameId;
	private GameStatus gameStatus;
	final int columns = 7;
	public final int rows = 6;
	private String matrix = new String();
	private int lastX = -1;
	private int lastY = -1;
	
	
	public int getGameID() {
		return gameId;
	}
	
	public void setGameID(int gameId) {
		this.gameId = gameId;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String getMatrix() {
		return matrix;
	}

	public void setMatrix(String matrix) {
		this.matrix = matrix;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public void setLastY(int lastY) {
		this.lastY = lastY;
	}
	
}
