package fr.leroideskiwis.enderpearlbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

public class CommandDisable implements CommandExecutor {

	private Main main;
	
	public CommandDisable(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if(main.isStatus(Gstatus.DISABLE)){
			main.setStatus(Gstatus.WAITING);
			sender.sendMessage("§aLe plugin a été activée avec succès !");
		} else if(main.isStatus(Gstatus.WAITING)){
			main.setStatus(Gstatus.DISABLE);
			sender.sendMessage("§cLe plugin a été desactivée avec succès !");
		} else {
			sender.sendMessage("§cDésolé, mais une partie est deja en cours !");
		}
		
		
	
		
		return false;
	}

}
