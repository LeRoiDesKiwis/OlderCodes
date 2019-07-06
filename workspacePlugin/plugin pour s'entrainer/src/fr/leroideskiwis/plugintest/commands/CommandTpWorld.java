package fr.leroideskiwis.plugintest.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.plugintest.Main;

public class CommandTpWorld implements CommandExecutor {

	private Main main;
	
	public CommandTpWorld(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		
		if(s instanceof Player){
			
			if(args.length < 1){
				
				s.sendMessage("§cErreur désolé mais t'es moche et les moches doivent mettre plus d'un argument");
				return true;
			}
			
			World worldToTp = Bukkit.getWorld(args[0]);
			
			if(worldToTp == null) {
				s.sendMessage("§cErreur : le monde n'existe pas ou c'est qu't'est moche...");
				return true;
			}
			
			((Player)s).teleport(new Location(worldToTp, 0, 60, 0));
			s.sendMessage("§7téléportation...");
			return true;
			
			
		} else {
			s.sendMessage("§cVous devez être un joueur pour executer cette commande !");
			return true;
		}
	}

}
