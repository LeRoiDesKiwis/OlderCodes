package fr.leroideskiwis.hapsyplugin.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.leroideskiwis.hapsyplugin.Main;

public class EventPlugin implements Listener {

	private Main main;

	public EventPlugin(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onBlockChange() {

	}

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		String message = main.getConfig().getString("messages.join");
		if(message.equals("") || message.equals("default") || message == null) return;
		
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message).replaceAll("<player>", e.getPlayer().getDisplayName()));
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		String message = main.getConfig().getString("messages.quit");
		if(message.equals("") || message.equals("default") || message == null) return;
		
		e.setQuitMessage(message.replaceAll("§", "&").replaceAll("<player>", e.getPlayer().getDisplayName()));
	}
	
	@EventHandler
	public void WeatherInfini(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
}
