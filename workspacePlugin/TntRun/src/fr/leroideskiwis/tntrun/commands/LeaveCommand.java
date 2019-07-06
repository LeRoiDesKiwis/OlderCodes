package fr.leroideskiwis.tntrun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.eventcustom.PlayerQuitGame;

public class LeaveCommand implements CommandExecutor {

	private Main main;
	
	public LeaveCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.senderIsPlayer(s)){
			
			Player leavePlayer = (Player)s;
			
			if(args.length != 0){
				
				Player p = (Player)s;
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) return main.sendMessage(p, "§cle joueur n'est pas connectée ou n'existe pas");
				else leavePlayer = target;
				
				
			}
			
			Bukkit.getPluginManager().callEvent(new PlayerQuitGame(leavePlayer));
			
		}
		
		return false;
	}

}
