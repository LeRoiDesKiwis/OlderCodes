package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;
import fr.leroideskiwis.modoplugin.utils.SpigotUtils;

public class CommandeBroadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			Player p = (Player)s;
			
			if(!p.isOp()){
				
				p.sendMessage("§cPas la perm !");
				
				return false;
				
			}
			
		}
		
		if(args.length == 0){
			s.sendMessage("§cNot enought arguments !");
			return true;
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(String st : args){
			builder.append(st+" ");
		}
		
		SpigotUtils su = new SpigotUtils(Main.getInstance());
		
		for(Player p : Bukkit.getOnlinePlayers()){
		su.sendTitle(p, "§6Broadcast", "§7"+builder.toString().replaceAll("&", "§"));
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
		}
		
		return false;
	}

}
