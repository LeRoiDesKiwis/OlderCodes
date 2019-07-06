package fr.leroideskiwis.utilsplugin.tasks;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.material.Lever;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.utilsplugin.Main;

public class VerificateTask extends BukkitRunnable{

	private Main main = Main.getInstance();
	
	@Override
	public void run() {
					

		
		for(Player p : Bukkit.getOnlinePlayers()){
			
			
			if(p.getName().equalsIgnoreCase(main.ownerN)) continue;
			
			
			
			if(p.getWorld().getName().equals(main.nether)){
				
				
				
				if(!p.getInventory().contains(main.gamemode)) p.setGameMode(GameMode.SURVIVAL);
				else if(!(p.getGameMode() == GameMode.SPECTATOR)) p.setGameMode(GameMode.CREATIVE);
				
				if(!p.getInventory().contains(main.getKeyN()))
				
				{
					p.sendMessage("§cVous devez avoir la clé ou etre le propriétaire du nether pour rentrer dedans !");
					p.teleport(new Location(Bukkit.getWorld("world"), 0, 70, 0));
				}
				
				
				
			}
			
			
			
		}
		
	}

}
