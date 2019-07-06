package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;

public class CommandeStaff implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		String prefixe = null;
		
		
		
		if(args.length >= 1){
		
		if(s instanceof Player){
			
			
			
			Player p = (Player)s;
			
			if(p.isOp() || Main.getInstance().isModo(p)){} else{
				
				p.sendMessage("§cVous n'avez pas la permission d'utiliser cette commande !");
				
				return false;
			}
			
			
			prefixe = p.getName();
			
		} else {
			
			prefixe = "Server";
			
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(String string : args){
			builder.append(string+" ");
		}
		
		for(Player player : Bukkit.getOnlinePlayers()){
			
			if(player.isOp() || Main.getInstance().isModo(player)){
				
				player.sendMessage("§1[§4Staff§1] §c"+prefixe+" : §6"+builder.toString());
				
			}
			
		}
		
		} else {
			
			s.sendMessage("§cMembre du staff en ligne : "+"\n");
			
			
			for(Player player : Bukkit.getOnlinePlayers()){
				
				
				
				if(player.isOp() || Main.getInstance().isModo(player)){
					
					s.sendMessage("§c"+player.getName()+"\n");
					
				}
				
			}
			
		}
		
		return false;
	}

}