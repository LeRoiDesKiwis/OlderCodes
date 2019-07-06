package fr.leroideskiwis.mysterychest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.mysterychest.Main;

public class CommandGetKey implements CommandExecutor {

	private Main main;
	
	public CommandGetKey(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(main.isPlayer(sender)){
			
			Player p = (Player)sender;
			
			if(p.isOp()){
				
				p.getInventory().addItem(main.getKeyChest());
				p.sendMessage("§7vous avez reçu la clé !");
				
			} else {
				p.sendMessage("§cAccès refusé !");
			}
		}
		
		return false;
	}

	}
