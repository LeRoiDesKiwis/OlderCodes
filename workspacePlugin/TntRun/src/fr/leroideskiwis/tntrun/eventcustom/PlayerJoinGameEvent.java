package fr.leroideskiwis.tntrun.eventcustom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.leroideskiwis.tntrun.game.GamePlayer;

public class PlayerJoinGameEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Player p;
	
	public PlayerJoinGameEvent(Player p) {
		this.p = p;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public GamePlayer getGamePlayer(){
		return new GamePlayer(p);
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	
}
