package fr.leroideskiwis.banmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.banmanager.Main;
import fr.leroideskiwis.banmanager.ban.Ban;

public class CommandBan implements CommandExecutor {

	private Main main;
	
	public CommandBan(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		Player target = Bukkit.getPlayer(args[0]);
		
		if(target == null){
			sender.sendMessage("§cErreur : le joueur n'est pas connecté");
			return false;
		}
		
		StringBuilder build = new StringBuilder();
		
		for(String part : args){
			if(part.equals(args[0])) continue;
			build.append(part + " ");
		}
		
		main.getBan().add(new Ban(main, target.getName(), (sender instanceof Player ? ((Player)sender).getName() : null), build.toString()));

		return false;
	}

}
