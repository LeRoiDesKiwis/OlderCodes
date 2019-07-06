package fr.leroideskiwis.shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShop implements CommandExecutor {

	private Main main;
	
	public CommandShop(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		
		if(sender instanceof Player){
			
			main.openInventory((Player)sender, "§7shop");
			
		} else {
			
			sender.sendMessage("§cVous devez être joueur pour executer commande commande !");
			
		}
		
		return false;
	}

}
