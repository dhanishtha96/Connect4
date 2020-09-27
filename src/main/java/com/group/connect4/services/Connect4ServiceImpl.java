package com.group.connect4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.connect4.enums.Color;
import com.group.connect4.enums.GameStatus;
import com.group.connect4.model.Connect4;
import com.group.connect4.repositories.Connect4Dao;

@Service
public class Connect4ServiceImpl implements Connect4Service {
	
	@Autowired
	Connect4Dao connect4Dao;

	@Override
	public Connect4 createNewGame() {
		
		Connect4 game = new Connect4();
		int gameId = connect4Dao.createNewGame(game);

		if(gameId != 0) {
			game.setGameID(gameId);
			return game;
		}
		else {
			return game;
		}
	}

	@Override
	public Connect4 getGame(int gameId) {
		// TODO Auto-generated method stub
		return connect4Dao.getGame(gameId);
	}
	
	@Override
	public String move(int gameId, String color, int col) {
		String message = "Added coin for " + color + "at column " + col;
		Connect4 game = connect4Dao.getGame(gameId);
		if(game != null) {
			if(game.getGameStatus() != GameStatus.COMPLETED) {
				if(isValidMove(color, game)) {
					char[] matrix = game.getMatrix().toCharArray();
					if(matrix[(game.rows-1)*7 + col] == 'N') {
						
						for(int i = 0; i < game.rows; i++) {
							if(matrix[i*7 + col] == 'N') {
								matrix[i*7 + col] = color.charAt(0);
								System.out.println(color.charAt(0));
								game.setLastX(i);
								game.setLastY(col);
								break;
							}
						}
						game.setMatrix(String.valueOf(matrix));
						if(isCompleted(game, color)) {
							game.setGameStatus(GameStatus.COMPLETED);
							message = Color.valueOf(color).toString() + " wins!";
						}
						save(game);
						
					}
					else {
						message = "Invalid move! Column is already filled!";
					}
				}
				else {
					message = "Invalid move! Wrong player!";
				}
			}
			else {
				message = "Invalid Move! Game already completed!";
			}
		}
		else {
			message = "Game with " + gameId + " not found !";
		}
		return message;
		
	}

	private void save(Connect4 game) {
		
		connect4Dao.saveGame(game);
	}

	private boolean isCompleted(Connect4 game, String color) {
		return checkRows(game, color) || checkCols(game, color) || 
				checkforwardSlash(game, color) || checkBackwardSlash(game, color);
	}

	private boolean checkBackwardSlash(Connect4 game, String color) {
		int x = game.getLastX();
		int y = game.getLastY();
		char[] matrix = game.getMatrix().toCharArray();
		int count = 0;
		
		for(int i = 0; i <= 5; i++) {
			int j = x+y-i;
			
			if(j >= 0 && j <= 6) {
				if(Character.toString(matrix[i*7 +j]).equalsIgnoreCase(color)) {
					count++;
					if(count == 4) {
						return true;
					}
					
				}
				else {
					count = 0;
				}
			}
		}
		return false;
	}

	private boolean checkforwardSlash(Connect4 game, String color) {
		int x = game.getLastX();
		int y = game.getLastY();
		System.out.println(x + " " + y);
		char[] matrix = game.getMatrix().toCharArray();
		int count = 0;
		
		for(int i = 0; i <= 6; i++) {
			int j = x-y+i;
			
			if(j >= 0 && j <= 5) {
				System.out.println("*************************************" + (i + j*7));
				if(Character.toString(matrix[i + j*7]).equalsIgnoreCase(color)) {
					System.out.println("************************a*********************");
					count++;
					if(count == 4) {
						return true;
					}
					
				}
				else {
					count = 0;
				}
			}
		}
		return false;
	}

	private boolean checkCols(Connect4 game, String color) {
		int l = 0, r = 5;
		int count = 0;
		char[] matrix = game.getMatrix().toCharArray();
		
		for(int i = l; i <= r; i++) {
			
			if(Character.toString(matrix[i*7 + game.getLastY()]).equalsIgnoreCase(color)) {
				count++;
				if(count == 4) {
					return true;
				}
				
			}
			else {
				count = 0;
			}
		}
		return false;
	}

	private boolean checkRows(Connect4 game, String color) {
		
		int l = 0, r = 6;
		int count = 0;
		String matrix = game.getMatrix();
		
		for(int i = l; i <= r; i++) {
			//System.out.println(Character.toString(matrix.charAt(game.getLastX()*7 + i)));
			if(Character.toString(matrix.charAt(game.getLastX()*7 + i)).equalsIgnoreCase(color)) {
				count++;
				if(count == 4) {
					return true;
				}
			}
			else {
				count = 0;
			}
		}
		return false;
	}

	private boolean isValidMove(String color, Connect4 game) {
		
		try {
			if(Color.valueOf(color) != null) {
				if(game.getLastX() == -1 && game.getLastY() == -1) {
					if(Color.valueOf(color) == Color.Y) {
						return true;
					}
					return false;
				}
				else {
					
					if(Color.valueOf(Character.toString(game.getMatrix().charAt(game.getLastX()*7 + game.getLastY()))) != Color.valueOf(color)) {
						return true;
					}
					return false;
				}
			}
		}
		catch(Exception IllegalArgumentException) {
			return false;
		}

		return false;
	}
	

}
