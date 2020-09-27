package com.group.connect4.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.group.connect4.enums.GameStatus;

@Entity(name="Connect4")
@Table(name="Connect4")
public class EntityClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "gameId", unique=true, updatable = false, nullable = false)
	private int gameId;
	
	@Column(name="gameStatus")
	private String gameStatus;
	
	@Column(name="matrix")
	private String matrix;
	
	@Column(name="lastX")
	private int lastX = -1;
	
	@Column(name="lastY")
	private int lastY = -1;

	public int getGameID() {
		return gameId;
	}
	public void setGameID(int gameId) {
		this.gameId = gameId;
	}
	

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
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
