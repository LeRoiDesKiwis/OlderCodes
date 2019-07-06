package fr.leroideskiwis.modoplugin.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SearchPlayer implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		
		
		List<OfflinePlayer> list = new ArrayList<>();
		
		for(OfflinePlayer off : Bukkit.getOfflinePlayers()){
			
			if(args[0].equalsIgnoreCase("*")){
				list.add(off);
				continue;
			}
			
			if(off.getName().toLowerCase(Locale.ROOT).contains(args[0].toLowerCase(Locale.ROOT))) list.add(off);
			
		}
		
		if(list.isEmpty()){
			
			s.sendMessage("§cAucun joueur ne correspond a votre recherche");
			
			return true;
		}
		
		for(OfflinePlayer off : list){
			s.sendMessage("§c"+off.getName().replaceAll(args[0].equals("*") ? "" : args[0], "§e"+args[0]));
		}
		
		s.sendMessage("");
		s.sendMessage("§a"+list.size()+" recherches enregistr§es");
		
		return false;
	}

}
