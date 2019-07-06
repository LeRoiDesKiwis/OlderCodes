package fr.leroideskiwis.plugintest;

import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.plugintest.commands.CommandTest;
import fr.leroideskiwis.plugintest.commands.CommandTpWorld;

public class Main extends JavaPlugin {

	public void onEnable(){
		getCommand("test").setExecutor(new CommandTest(this));
		getCommand("tpworld").setExecutor(new CommandTpWorld(this));
	}
	
}
