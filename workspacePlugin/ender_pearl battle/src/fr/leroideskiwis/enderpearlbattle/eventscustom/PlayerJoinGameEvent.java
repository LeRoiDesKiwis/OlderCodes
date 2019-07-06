package fr.leroideskiwis.enderpearlbattle.eventscustom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinGameEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player p;
	
	public PlayerJoinGameEvent(Player p) {
		this.p = p;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	
}
