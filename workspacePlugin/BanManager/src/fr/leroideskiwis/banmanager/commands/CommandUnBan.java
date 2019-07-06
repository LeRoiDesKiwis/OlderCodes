package fr.leroideskiwis.banmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.leroideskiwis.banmanager.Main;
import fr.leroideskiwis.banmanager.ban.Ban;

public class CommandUnBan implements CommandExecutor {

	private Main main;
	
	public CommandUnBan(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		
		
		boolean isBanned = false;
		
		for(int i = 0; i < main.getBan().size(); i++){
		if(main.getBan().get(i).getBanned().equals(args[0])) main.getBan().remove(i);
		sender.sendMessage(args[0]+" a été debanni");
		isBanned = true;
		Bukkit.reload();
		}
		
		if(!isBanned){
			sender.sendMessage("§cErreur : "+args[0]+" n'est pas banni ou n'existe pas");
		}
		
		return false;
	}

}
