package fr.leroideskiwis.utilsplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CommandButcher implements CommandExecutor {
	
	private Main main;
	
	public CommandButcher(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		
		if(s instanceof Player){
			
			Player p = (Player)s;
			for(Entity e : p.getWorld().getEntities()){
				
				if(e instanceof Player) continue;
				
				e.teleport(new Location(p.getWorld(), 0, -100, 0));
				
			}
			
		} else {
			
			for(World w :Bukkit.getServer().getWorlds()){
				
				for(Entity e : w.getEntities()){
					if(e instanceof Player) continue;
					e.teleport(new Location(w, 0, -100, 0));
				}
				
			}
			
		}
		
		s.sendMessage("§aTout les mobs ont été tués avec succès !");
		return false;
	}

}
