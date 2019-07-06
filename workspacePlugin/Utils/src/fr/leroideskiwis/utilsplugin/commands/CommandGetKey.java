package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.utilsplugin.Main;

public class CommandGetKey implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		
		if(main.SenderIsPlayer(s)){
			
			
			Player p = (Player)s;
			
			if(!p.getName().equalsIgnoreCase(main.ownerN)){
				
				p.sendMessage("§cVous devez etre le proprietaire du nether pour obtenir la clé ! propriétaire actuel : "+main.ownerN);
				
				return true;
			}
			
			p.getInventory().addItem(main.getKeyN());
			p.getInventory().addItem(main.perm);
			p.getInventory().addItem(main.gamemode);
			p.sendMessage("§aVous avez reçu la clé du nether !");
			
		}
		
		return false;
	}

}
