package fr.leroideskiwis.tpaplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.PluginCommand;

import fr.leroideskiwis.tpaplugin.commands.CommandTpAccept;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.tpaplugin.commands.CommandTpAccept;
import fr.leroideskiwis.tpaplugin.commands.CommandTpDeny;
import fr.leroideskiwis.tpaplugin.commands.CommandTpa;

public class Main extends JavaPlugin{
	
	public List<TpRequest> tpRequests = new ArrayList<>();
	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		PluginCommand tpdeny = getCommand("tpdeny");
		PluginCommand tpyes = getCommand("tpaccept");
		tpdeny.setAliases(Arrays.asList("tpno"));
		tpyes.setAliases(Arrays.asList("tpyes"));
		tpdeny.setExecutor(new CommandTpDeny());	
		tpyes.setExecutor(new CommandTpAccept());
		getCommand("tpa").setExecutor(new CommandTpa());
		
	}
	
	
	
	public static Main getInstance(){
		return instance;
	}
	
	public boolean senderIsPlayer(CommandSender s){
		if(s instanceof Player){
			
			
			return true;
		} else {
			s.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
			return false;
			
		}
	}
	
	
}
