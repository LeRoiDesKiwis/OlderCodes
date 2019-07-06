package fr.leroideskiwis.gamemanager.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.enderpearlbattle.Main;

public class CommandHub implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] arg3) {
		
		if(s instanceof Player){
			
			Player p = (Player)s;
			
			Location hub = Main.getInstance().getHubLocation();
			hub.setPitch(0.0f);
			hub.setYaw(90.0f);
			
					
			p.teleport(hub);
			
			
			
			p.sendMessage("§7Vous êtes au hub !");
			
		} else{
			
			s.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
			
		}
			
		
		return false;
	}

}
