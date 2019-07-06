package fr.leroideskiwis.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

public class CommandDressWolf implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
	
		
		if(s instanceof Player){
			
			Player p = (Player)s;
			
			for(Entity e : p.getWorld().getEntities()){
				
				if(e.getType() == EntityType.WOLF){
					
					Wolf loup = (Wolf)e;
					if(!loup.isTamed()) loup.setOwner((AnimalTamer)p);
					loup.setHealth(20.0f);
					
				} else if(e.getType() == EntityType.PIG_ZOMBIE){
					
					PigZombie pigZ = (PigZombie)e;
					pigZ.setAngry(false);
					
				}
				
			}
			
		} else s.sendMessage("�cErreur !");
		
		return false;
	}

}
