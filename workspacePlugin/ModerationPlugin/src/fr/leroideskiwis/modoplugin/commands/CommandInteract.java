package fr.leroideskiwis.modoplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.modoplugin.Main;

public class CommandInteract implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(s instanceof Player){
			
			Player p = (Player)s;
			
			if(!p.isOp()){
				
				p.sendMessage("§cErreur : vous n'avez pas la permission d'utiliser cette commande !");
				
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null){
				
				p.sendMessage("§cJoueur introuvable !");
				
				return false;
			} else {
				
				p.openInventory(Main.getInstance().getInventoryInteract(target.getName()));
				
			}
			
		} else s.sendMessage("§cError !");
		
		return false;
	}

}
