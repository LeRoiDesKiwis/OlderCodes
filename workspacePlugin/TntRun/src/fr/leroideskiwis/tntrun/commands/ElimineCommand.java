package fr.leroideskiwis.tntrun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.tntrun.Main;

public class ElimineCommand implements CommandExecutor {

	private Main main;
	
	public ElimineCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(args.length == 0){
			s.sendMessage("désolé, mais vous devez mettre plus de 1 argument !");
		} else {
			
			Player target = Bukkit.getPlayer(args[0]);
			if(target == null) return main.sendMessage(s, "§cErreur : "+args[0]+" n'existe pas ou n'est pas connecté !");
			else {
				main.eliminate(target);
				s.sendMessage("§aLe joueur §c"+target.getName()+" a bien été eliminé !");
			}
			
			
		}
		
		return false;
	}

}
