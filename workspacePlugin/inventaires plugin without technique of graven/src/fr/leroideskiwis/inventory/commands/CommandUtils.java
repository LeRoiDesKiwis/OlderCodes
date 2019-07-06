package fr.leroideskiwis.inventory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.inventory.Main;
import fr.leroideskiwis.inventory.inventories.UtilMenu;

public class CommandUtils implements CommandExecutor {

	private Main main;
	
	public CommandUtils(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		
		if(sender instanceof Player){
			
			main.getInventories().stream().filter(menu -> menu.getName().equals("utilsMenu")).forEach(menu -> {
				((Player)sender).closeInventory();
				((Player)sender).openInventory(menu);
			});
		
		} else {
			sender.sendMessage("§cErreur : vous devez être un joueur pour executer cette commande !");
		}
		
		return false;
	}

}
