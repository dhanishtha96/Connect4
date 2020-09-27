package com.group.connect4.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.connect4.model.Connect4;
import com.group.connect4.restUtils.RestStatus;
import com.group.connect4.services.Connect4Service;

@RestController
public class Connect4Controller {

	@Autowired
	Connect4Service connect4Service;

	@RequestMapping(value= "/newGame" , method=RequestMethod.GET)
	public ResponseEntity<RestStatus<String>> createNewGame() {  
	  
		  Connect4 game = connect4Service.createNewGame();
		  RestStatus<String> response = null;
		  String message = "New Game created successfully!";
		  HttpStatus status = HttpStatus.OK;
		  
		  if(null == game) {
			  message = "Unable to create a new game";
			  response = new RestStatus<String>(status.value(), message);
		  }
		  else {
			  message += "Game Id " + game.getGameID();
			  response = new RestStatus<String>(status.value(), message);
		  }
	
		  return new ResponseEntity<RestStatus<String>>(response, status);
  	}

	 @RequestMapping(value= "/getGame" , method=RequestMethod.GET)
	 public ResponseEntity<RestStatus<String>> getGame(
			  @RequestParam(required = true) int gameId) {
	  
		  Connect4 game = connect4Service.getGame(gameId);
		  RestStatus<String> response = null;
		  String message = "Game retrieved successfully!";
		  HttpStatus status = HttpStatus.OK;
		  
		  if(game == null) {
			  message = "No game found with gameId " + gameId;
			  response = new RestStatus<String>(status.value(), message);
		  }
		  else {
			  response = new RestStatus<String>(status.value(), message, game.getGameStatus().toString());
		  }
		
		  return new ResponseEntity<RestStatus<String>>(response, status);
	}

	@RequestMapping(value="/{gameId}/move" , method=RequestMethod.GET)
	public ResponseEntity<RestStatus<String>> addMove(
			@PathVariable int gameId,
			@RequestParam String color, @RequestParam  int col) {
		    
			RestStatus<String> response = null;
			String message = "";
			HttpStatus status = HttpStatus.OK;
			
			if(col < 0 || col > 6){
				message = "Invalid Column! Column should in range 0 to 6";
		    }
			else {
				message = connect4Service.move(gameId, color, col);
			}
		   
		    response = new RestStatus<String>(status.value(), message);
		    return new ResponseEntity<RestStatus<String>>(response, status);
	  }
}
