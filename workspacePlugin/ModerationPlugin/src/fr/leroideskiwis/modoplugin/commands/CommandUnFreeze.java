package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;

public class CommandUnFreeze implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
	
			
			
			if(!s.isOp()){
				
				s.sendMessage("§cErreur : Vous n'avez pas la permission d'utiliser cette commande !");
				
				return false;
			}
			
			if(args.length <= 0){
				s.sendMessage("§cArguments invalide !");
				return false;
			}else {
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null){
					
					s.sendMessage("§c"+args[0]+"n'est pas un joueur valide !");
					
				} else {
					
					if(s instanceof Player) if(target.equals((Player)s)){
						s.sendMessage("§cVous ne pouvez pas vous unfreeze vous meme !");
						
						return true;
					}
					
					if(Main.getInstance().freezes.contains(target)){
						
						Main.getInstance().freezes.remove(target);
						
						s.sendMessage("§a"+target.getDisplayName()+" a §t§ unfreeze avec succ§s !");
						target.sendMessage("§aVous avez §t§ unfreeze !");
						Bukkit.broadcastMessage(Main.getInstance().prefixe+"§e "+target.getName()+" §ca §t§ unfreeze par §e"+s.getName());
						return true;
					} else {
						
						s.sendMessage("§cErreur : "+target.getDisplayName()+" n'est pas freeze !");
						
					}
					
				}
			}
			
		
		
		return false;
	}

}
