package fr.leroideskiwis.hapsyplugin;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.hapsyplugin.commands.CommandMsg;
import fr.leroideskiwis.hapsyplugin.commands.CommandRepondre;
import fr.leroideskiwis.hapsyplugin.listener.EventPlugin;

public class Main extends JavaPlugin {

	private Map<Player, Player> reponse = new HashMap<>();
	
	public Map<Player, Player> getReponse(){
		return reponse;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable(){
		
		saveDefaultConfig();
		
		getCommand("msg").setExecutor(new CommandMsg(this));
		getServer().getPluginManager().registerEvents(new EventPlugin(this), this);
		getCommand("r").setExecutor(new CommandRepondre(this));		

	
}
}
