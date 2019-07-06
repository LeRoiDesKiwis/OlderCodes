package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.utilsplugin.Main;

public class CommandClearEnd implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(target == null){
				
				s.sendMessage("§c"+args[0]+" n'est pas connecté !");
				
				return true;
			} else {
				target.getEnderChest().clear();
				s.sendMessage("§cL'enderchest de "+args[0]+" a été clear avec succès !");
			}
			
		}
		
		return false;
	}

}
