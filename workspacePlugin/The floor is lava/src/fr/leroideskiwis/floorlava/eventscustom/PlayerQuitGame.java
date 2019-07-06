package fr.leroideskiwis.floorlava.eventscustom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerQuitGame extends Event {

	private Player p;
	private static HandlerList handlers = new HandlerList();
	
	public PlayerQuitGame(Player p) {
		this.p = p;
	}

	public Player getPlayer() {
		return p;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}

}
