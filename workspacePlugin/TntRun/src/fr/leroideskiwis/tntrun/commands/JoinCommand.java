package fr.leroideskiwis.tntrun.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.tntrun.Main;
import fr.leroideskiwis.tntrun.eventcustom.PlayerJoinGameEvent;

public class JoinCommand implements CommandExecutor {

	private Main main;
	
	public JoinCommand(Main main) {
		this.main = main;
	}

	public boolean sendMessage(Player p, String msg){
		p.sendMessage(msg);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.senderIsPlayer(s)){
			
			Player joinPlayer = (Player)s;
			
			if(args.length != 0){
				
				Player p = (Player)s;
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) return sendMessage(p, "§cle joueur n'est pas connectée ou n'existe pas");
				else joinPlayer = target;
				
				
			}
			
			Bukkit.getPluginManager().callEvent(new PlayerJoinGameEvent(joinPlayer));
			
		}
		
		return false;
	}

}
