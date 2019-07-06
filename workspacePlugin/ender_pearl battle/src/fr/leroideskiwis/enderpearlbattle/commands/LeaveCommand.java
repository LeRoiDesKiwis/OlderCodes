package fr.leroideskiwis.enderpearlbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.eventscustom.PlayerQuitGame;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

@SuppressWarnings("unused")
public class LeaveCommand implements CommandExecutor {

	
	private Main main;
	
	public LeaveCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)){
			
			sender.sendMessage("§cVous devez être joueur pour executer cette commande");
			
		} else {
			Player p = (Player)sender;
			if(args.length == 1) {
				p = Bukkit.getPlayer(args[0]);
				
				if(p == null) {
					((Player)sender).sendMessage("§cErreur : "+args[0]+" n'existe pas ou n'est pas connecté");
					return true;
				}
			}
			Bukkit.getPluginManager().callEvent(new PlayerQuitGame(p));
			
		
		
	}

		return false;
}
}	
