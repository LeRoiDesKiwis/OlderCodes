package fr.leroideskiwis.coins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.coins.Main;

public class CommandCoins implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main main;

	public CommandCoins(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(sender instanceof Player){
			
			Player p = (Player)sender;
			
			if(!main.getCoins().containsKey(p)) main.getCoins().put(p, 0.0);
			
			p.sendMessage("§eVous avez actuellement "+main.getCoins().get(p)+" coins");
			
		} else {
			sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
		}
		
		return false;
	}

	
}
