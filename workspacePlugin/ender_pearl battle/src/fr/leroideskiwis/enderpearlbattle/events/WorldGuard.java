package fr.leroideskiwis.enderpearlbattle.events;

import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import fr.leroideskiwis.enderpearlbattle.Main;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;

@SuppressWarnings("unused")
public class WorldGuard implements Listener {

	
	private Main main;
	
	public WorldGuard(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		Location locp = p.getLocation();
		if(p.getName().equalsIgnoreCase("LeRoiDesKiwis") || p.getName().equalsIgnoreCase("HapsyGame")) return;
		if(locp.getX() < 5 && locp.getY() < 82 && locp.getZ() < 20 && locp.getBlockX() > -10 && locp.getZ() > -10){
			e.setCancelled(true);
			p.sendMessage("§cvous ne pouvez pas casser ici !");
		}
		
		if(new Location(p.getWorld(), locp.getX(), locp.getBlockY() - 1, locp.getZ()).getBlock().getType() == Material.GLASS){
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Player p = e.getPlayer();
		Location locp = p.getLocation();
		if(p.getName().equalsIgnoreCase("LeRoiDesKiwis") || p.getName().equalsIgnoreCase("HapsyGame")) return;
		if(locp.getX() < 5 && locp.getY() < 82 && locp.getZ() < 20 && locp.getBlockX() > -10 && locp.getZ() > -10){
			e.setCancelled(true);
			p.sendMessage("§cvous ne pouvez pas poser ici !");
		}
	}
	
	@EventHandler
	public void onTntExplode(EntityExplodeEvent e){

		if(main.isStatus(Gstatus.DISABLE)) return;
		
		Iterator<Block> blocks = e.blockList().iterator();
		
		while(blocks.hasNext()){
			Block block = blocks.next();
			if(block.getType() == Material.TNT) continue;
			blocks.remove();
			e.blockList().remove(block);
		}
		
	}
	
}
