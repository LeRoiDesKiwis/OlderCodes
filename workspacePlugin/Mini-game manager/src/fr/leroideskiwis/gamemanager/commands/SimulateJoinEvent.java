package fr.leroideskiwis.gamemanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.leroideskiwis.gamemanager.Main;

public class SimulateJoinEvent implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main main;
	
	public SimulateJoinEvent(Main main) {
		this.main = main;
	}
	
	public boolean sendMessage(Player p, String string){
		p.sendMessage(string);
		
		return true;
	}
	
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if((s instanceof Player) && (((Player)s).isOp())){
			
			Player p = (Player)s;
			
			if(args.length == 1){
				
				Player target = Bukkit.getPlayer(args[0]);
				
				if(target == null) return sendMessage(p, "§c"+args[0]+" n'est pas connecté");
				
				Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(target, ""));
				return true;
			}
			
			Bukkit.getPluginManager().callEvent(new PlayerJoinEvent((Player)s, "coucou les amis c'est Popi de la street"));
			
		} else {
			s.sendMessage("§cNon");
		}
		
		return false;
	}
	
	

}
