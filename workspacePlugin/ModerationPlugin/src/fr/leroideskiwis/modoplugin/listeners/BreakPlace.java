package fr.leroideskiwis.modoplugin.listeners;

import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.leroideskiwis.modoplugin.Main;

public class BreakPlace implements Listener {

	

	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		
		if(!e.getPlayer().isOp()){
			
			e.getPlayer().sendMessage("§cNot enought permissions !");
			
			e.getPlayer().updateInventory();
			
			e.setCancelled(true);
			
		}
		
		Location loc = e.getBlock().getLocation();
		
		if(Main.getInstance().freezes.contains(e.getPlayer())) {
			e.setCancelled(true);
			return;
		}
		
		/*for(Player p : Bukkit.getOnlinePlayers()){
			
			if(Main.getInstance().logs.contains(p)){
				
				p.sendMessage("§e"+e.getPlayer().getName()+" §ca casser un bloc de §e"+e.getBlock().getType().toString().toLowerCase(Locale.ROOT)+" aux coordonn§es §e"+loc.getX()+", "+loc.getY()+", "+loc.getZ()+" §c !");
				
			}
			
		}*/
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		
		if(!e.getPlayer().isOp()){
			
			e.getPlayer().sendMessage("§cNot enought permissions !");
			
			e.getPlayer().updateInventory();
			
			e.setCancelled(true);
			
		}
		
		if(Main.getInstance().freezes.contains(e.getPlayer())) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getBlock().getType() == Material.TNT){
		
		if(e.getPlayer().isOp()){
			
			for(Player p : Bukkit.getOnlinePlayers()){
				if(p.isOp() && Main.getInstance().logs.contains(p)){
					p.sendMessage("§e"+e.getPlayer().getName()+" §ca plac§ de la"+e.getBlock().getType()+" en §e"+e.getBlock().getLocation().getX()+", "+e.getBlock().getLocation().getY()+", "+e.getBlock().getLocation().getZ()+" §c!");
					
				}
			}
			
		} else {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cNon, t'a pas le droit de placer de la "+e.getBlock().getType()+" !");
		}
		}
		
	}
	
}
