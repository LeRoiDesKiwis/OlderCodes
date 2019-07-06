package fr.leroideskiwis.enderpearlbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.enderpearlbattle.Main;

public class CommandElimine implements CommandExecutor {

	private Main main;
	
	public CommandElimine(Main main) {
		this.main = main;
	}

	public boolean sendMessage(Player p, String msg){
		p.sendMessage(msg);
		return true;
	}
	
	public boolean sendMessage(CommandSender s, String msg){
		s.sendMessage(msg);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2, String[] args) {
		Player target = Bukkit.getPlayer(args[0]);
		
		if(target == null) return sendMessage(s, "§cErreur : "+args[0]+" n'existe pas ou n'est pas connecté");
		if(main.getPlayers().contains(target)){
			
			main.eliminate(target);
			
		} else return sendMessage(s, "§cErreur : "+target.getName()+" n'est pas dans une partie");
		
		return false;
	}

}
