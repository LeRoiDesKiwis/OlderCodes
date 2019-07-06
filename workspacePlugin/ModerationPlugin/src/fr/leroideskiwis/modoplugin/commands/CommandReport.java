package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;

public class CommandReport implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			
			Player p = (Player)s;
			
			if(args.length <= 0){
				p.sendMessage("§cErreur : trop peu d'arguments !");
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null){
				
				p.sendMessage("§cLe joueur "+args[0]+" n'existe pas ou n'est pas connect§ !");
				
				return false;
			}
			
			p.openInventory(Main.getInstance().getReportInv("§cReport : "+target.getName()));
			
		} else {
			s.sendMessage("§vVous devez §tre un joueur pour executer cette commande !");
		}
		
		return false;
	}

}
