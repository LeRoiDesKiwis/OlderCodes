package fr.leroideskiwis.gamemanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.gamemanager.Main;

public class CommandBypass implements CommandExecutor {
	
	private Main main;
	
	public CommandBypass(Main main){
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			
			
			Player p = (Player)s;
			
			if(!p.isOp()) p.sendMessage("§cVous n'avez pas la permission d'executer cette commande !");
			
			else{
				
			if(main.bypass.contains(p)){
				
				main.bypass.remove(p);
				p.sendMessage("§cVous avez etez retirer du bypass mode");
				
			} else {
				
				main.bypass.add(p);
				p.sendMessage("§aVous avez rejoint le bypass mode");
				
			}
		}
		} else {
			s.sendMessage("§cErreur : vous devez etre un joueur pour executer cette commande !");
		}
		
		return false;
	}

}
