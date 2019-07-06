package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandSs implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		
		
		if(s instanceof Player){
			
			
			
			Player p = (Player)s;
			
			if(!p.isOp()){
				
				if(!Main.getInstance().pseudosModo.contains(p.getName())){
				p.sendMessage("§cErreur : vous n'avez pas la permission d'executer cette commande !");
				
				return false;
				}
			}
			
			if(args.length <= 0){
				
				s.sendMessage("§cTrop peu d'arguments !");
				
				return false;
			} else {
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null){
					
	
					
					s.sendMessage("§cle joueur "+args[0]+" n'a pas §t§ trouver");
					
					return true;


				} else
				
					
				{
					p.sendMessage(Main.getInstance().prefixe+" §7Vous entrez dans le menu de mod§ration.");
					TextComponent comp = new TextComponent("Merci de sanctionner avec une raison valable et de ne pas vous amuser a sanctionner pour le fun (sauf autorisation du Fondateur). Vous pourriez §tre sanctionner pour abus de pouvoir !");
					comp.setBold(true);
					comp.setColor(ChatColor.RED);
					p.sendMessage("");
					p.spigot().sendMessage(comp);
					p.openInventory(Main.getInstance().getInvModo("§cMod§ration : §e"+target.getName()));
					p.sendMessage("");
					
				}
				
			}
			
		} else {
			
			s.sendMessage("§cErreur !");
			
		}
		
		return false;
	}

}
