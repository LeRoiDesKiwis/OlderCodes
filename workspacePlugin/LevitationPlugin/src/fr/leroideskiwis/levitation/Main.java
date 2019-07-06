package fr.leroideskiwis.levitation;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.levitation.commands.CommandLev;
import fr.leroideskiwis.levitation.listeners.Events;

public class Main extends JavaPlugin{

	private static Main instance;
	
	@Override
	public void onEnable() {

		instance = this;
		getCommand("lev").setExecutor(new CommandLev());
		getServer().getPluginManager().registerEvents(new Events(), this);
		
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	public boolean isPlayer(CommandSender sender){
		
		if(sender instanceof Player) return true;
		else {
			sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
			return false;
		}
		
	}
	
}
