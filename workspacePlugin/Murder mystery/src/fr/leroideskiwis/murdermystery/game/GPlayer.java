package fr.leroideskiwis.murdermystery.game;

import org.bukkit.entity.Player;

public class GPlayer {

	private Player player;
	private Role role;
	
	public GPlayer(Player p) {
		this.player = p;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Role getRole(){
		return role;
	}
	
	public boolean isRole(Role role){
		return this.role == role;
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	
}
