package fr.leroideskiwis.floorlava.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.leroideskiwis.floorlava.Main;
import fr.leroideskiwis.floorlava.eventscustom.PlayerJoinGameEvent;
import fr.leroideskiwis.floorlava.eventscustom.PlayerQuitGame;

public class JoinQuitEvent implements Listener {

	@SuppressWarnings("unused")
	private Main main;
	
	public JoinQuitEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinGameEvent e){
			
	}
	
	@EventHandler
	public void onQuit(PlayerQuitGame e){
		
	}
	
}
