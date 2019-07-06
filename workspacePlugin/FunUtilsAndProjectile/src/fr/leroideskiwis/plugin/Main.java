package fr.leroideskiwis.plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.plugin.commands.CommandDressWolf;
import fr.leroideskiwis.plugin.commands.CommandSetPopo;
import fr.leroideskiwis.plugin.commands.CommandVote;
import fr.leroideskiwis.plugin.listeners.ProjectileEvent;

public class Main extends JavaPlugin{

	private static Main instance;

	@Override
	public void onEnable(){
		
		instance = this;
		
		PluginManager manage = getServer().getPluginManager();
		
		manage.registerEvents(new ProjectileEvent(), this);
		getCommand("dressAllWolf").setExecutor(new CommandDressWolf());
		getCommand("vote").setExecutor(new CommandVote());
		getCommand("setpopo").setExecutor(new CommandSetPopo());
	}
	
	public static Main getInstance(){
		return instance;
	}
	
}
