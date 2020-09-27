package com.group.connect4.repositories;

import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;

import com.group.connect4.model.Connect4;
import com.group.connect4.orm.EntityClass;


public interface Connect4Dao extends CrudRepository<EntityClass, Id> {

	public EntityClass findByGameId(int gameId);
	public int createNewGame(Connect4 connect4); 
	public Connect4 getGame(int gameId);
	public boolean saveGame(Connect4 connect4);
}
