package fr.leroideskiwis.bungeetest;

import fr.leroideskiwis.bungeetest.commands.CommandeHub;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

	@Override
	public void onEnable() {
		getProxy().getPluginManager().registerCommand(this, new CommandeHub("hub"));
		
	}
	
	@Override
	public void onDisable() {
		
		
	}

}