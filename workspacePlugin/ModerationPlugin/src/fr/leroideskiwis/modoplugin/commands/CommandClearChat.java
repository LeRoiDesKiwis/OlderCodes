package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.leroideskiwis.modoplugin.Main;


public class CommandClearChat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		for(int i = 0; i < 100; i++){
			Bukkit.broadcastMessage("");
		}
		
		Bukkit.broadcastMessage(Main.getInstance().prefixe+"§e "+s.getName()+" §7vient de nettoyer le tchat !");
		
		return false;
	}

}
