package fr.leroideskiwis.tntrun.eventcustom;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.leroideskiwis.tntrun.game.GamePlayer;

public class PlayerQuitGame extends Event {

	private Player p;
	private static HandlerList handlers = new HandlerList();
	
	public PlayerQuitGame(Player p) {
		this.p = p;
	}

	public Player getPlayer() {
		return p;
	}

	public GamePlayer getGamePlayer(){
		return new GamePlayer(p);
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}

}
