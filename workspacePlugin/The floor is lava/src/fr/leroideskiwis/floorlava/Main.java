package fr.leroideskiwis.floorlava;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.floorlava.listeners.JoinQuitEvent;

public class Main extends JavaPlugin{

	private List<Player> players = new ArrayList<>();
	
	@Override
	public void onEnable() {

		getServer().getPluginManager().registerEvents(new JoinQuitEvent(this), this);
		
	}
	
}
