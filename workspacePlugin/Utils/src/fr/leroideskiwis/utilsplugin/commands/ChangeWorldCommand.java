package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.leroideskiwis.utilsplugin.Main;
import fr.leroideskiwis.utilsplugin.enums.Message;

public class ChangeWorldCommand implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			if(args.length == 0){
				
				s.sendMessage("§cSyntaxe : /world <nom du monde>");
				
				return true;
			}
			
			Player p = (Player)s;
			
			World w = Bukkit.getWorld(args[0]);
			
			p.teleport(new Location(w, 0, 100.0, 0.0));
			
			if(w == null){
				
				p.sendMessage(Message.MESSAGE_CHANGE_WORLD.getMessage().replaceAll("<world>", args[0]));
				
				return true;
			}
			
		}
		
		return false;
	}

}
