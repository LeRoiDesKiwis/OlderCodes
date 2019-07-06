package fr.leroideskiwis.petplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.leroideskiwis.petplugin.Main;

public class CommandPet implements CommandExecutor {

	private Main main;

	public CommandPet(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			
			
		}
		
		return false;
	}

}
