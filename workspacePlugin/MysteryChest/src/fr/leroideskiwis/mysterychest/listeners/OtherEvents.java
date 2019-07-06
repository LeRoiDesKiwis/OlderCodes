package fr.leroideskiwis.mysterychest.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.leroideskiwis.mysterychest.Main;

public class OtherEvents implements Listener {

	private Main main;

	public OtherEvents(Main main){
		this.main = main;
	}
	
	@EventHandler
	public void onStartJump(PlayerMoveEvent e)
	{
		
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		
		
		if(loc.getBlock().getType() == Material.GOLD_PLATE){
			
			if(loc.add(0.0, -1.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK)
			{
				if(loc.add(0.0, -2.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK)
				{
					
					if(loc.add(0.0, -3.0, 0.0).getBlock().getType() == Material.REDSTONE_BLOCK)
					{
						
						main.jump.add(p);
						p.sendMessage("!cTu commence le jump !");
						
					} else if(loc.add(0.0, -3.0, 0.0).getBlock().getType() == Material.GOLD_BLOCK) {
						
						p.sendMessage("§eTu as finis le jump !");
						
					}
					
				}
			}
			
		}
		
		
		
	}
}
