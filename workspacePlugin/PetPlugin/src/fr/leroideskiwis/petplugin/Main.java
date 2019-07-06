package fr.leroideskiwis.petplugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.petplugin.commands.CommandPet;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		PluginManager pm = getServer().getPluginManager();
		
		getCommand("pet").setExecutor(new CommandPet(this));
		
	}
	
	public boolean SenderIsPlayer(CommandSender sender){
		if(sender instanceof Player) return true;
		
		else {
		sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
		
		return false;
		}
	}
	
}
