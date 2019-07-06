package fr.leroideskiwis.tntrun.game;

import org.bukkit.entity.Player;

public class GamePlayer {

	private Player player;
	
	public GamePlayer(Player p){
		
		this.player = p;
		
	}
	
	public Player getAsPlayer(){
		return player;	
	}
	
}
