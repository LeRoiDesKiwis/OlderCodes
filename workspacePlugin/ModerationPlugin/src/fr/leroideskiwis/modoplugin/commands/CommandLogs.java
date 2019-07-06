package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;

public class CommandLogs implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			
			
			
			Player p = (Player)s;
			
			if(!p.isOp()) return false;
			
			if(!Main.getInstance().logs.contains(p)) {
				Main.getInstance().logs.add(p);
				p.sendMessage("§aVous recevrez les logs");
			}
			else {
				Main.getInstance().logs.remove(p);
				p.sendMessage("§cVous ne recevrez plus les logs");
			}
			
			
		}
		
		return false;
	}

}
