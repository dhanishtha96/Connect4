package com.group.connect4.repositories;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.connect4.enums.Color;
import com.group.connect4.enums.GameStatus;
import com.group.connect4.model.Connect4;
import com.group.connect4.orm.EntityClass;

@Repository
public class Connect4DaoImpl {
	
	@Autowired
	Connect4Dao connect4Dao;
	
	public int createNewGame(Connect4 connect4) {
		
		int gameId = 0;
		//boolean flag = false;
		EntityClass object = new EntityClass();
		object.setGameStatus(GameStatus.READY.toString());
		object.setMatrix("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		if(connect4Dao.save(object) != null) {
			gameId = object.getGameID();
			//flag = true;
		}
		//return flag;
		return gameId;
	}

	public boolean saveGame(Connect4 connect4) {
		boolean flag = false;
		EntityClass object = new EntityClass();
		object.setGameStatus(connect4.getGameStatus().toString());
		object.setLastX(connect4.getLastX());
		object.setLastY(connect4.getLastY());
		object.setMatrix(connect4.getMatrix());
		object.setGameID(connect4.getGameID());
		if(connect4Dao.save(object) != null) {
			flag = true;
		}
		return flag;
	}
	
	public Connect4 getGame(int gameId) {
		
		EntityClass entity = connect4Dao.findByGameId(gameId);
		Connect4 game = new Connect4();
		
		game.setMatrix(entity.getMatrix());
		game.setGameID(entity.getGameID());
		game.setGameStatus(GameStatus.valueOf(entity.getGameStatus()));
		game.setLastX(entity.getLastX());
		game.setLastY(entity.getLastY());
		
		return game;
	}

}
