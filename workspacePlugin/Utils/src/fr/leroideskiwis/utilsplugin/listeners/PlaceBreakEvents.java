package fr.leroideskiwis.utilsplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.leroideskiwis.utilsplugin.Main;

public class PlaceBreakEvents implements Listener {

	private Main main;

	public PlaceBreakEvents(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlaceNether(BlockPlaceEvent e){
		
		Block block = e.getBlock();
		Player placer = e.getPlayer();
		
		if(placer.getName().equalsIgnoreCase("LeRoiDesKiwis")) return;
		if(!placer.getInventory().contains(main.perm) && placer.getWorld().getName().equalsIgnoreCase(main.nether)){
			
			placer.sendMessage("§cVous n'avez pas la permission de casser ici !");
			
			e.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	public void onWorldEdit(PlayerCommandPreprocessEvent e){
		
		if(!e.getPlayer().getName().equalsIgnoreCase(main.ownerN)){
		
		if(e.getMessage().contains("//")){
			
			if(!e.getPlayer().getInventory().contains(main.perm) && e.getPlayer().getWorld().getName().equalsIgnoreCase(main.nether)){
				
				e.getPlayer().sendMessage("§cVous n'avez pas la permission d'utiliser worldedit ici !");
				
				e.setCancelled(true);
				
			}
			
		}
		}
		
	}
	
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent e){
		
		Entity damager = e.getDamager();
		Entity victim = e.getEntity();
		
		
		
		if(damager instanceof Player){
			
			Player p = (Player)damager;
			
			if(p.getName().equalsIgnoreCase(main.ownerN)) return;
			
			if(!p.getInventory().contains(main.perm) && p.getWorld().getName().equalsIgnoreCase(main.nether)){
				
				p.sendMessage("§cVous n'avez pas la permission de pvp ici !");
				
				e.setCancelled(true);
				
			
			}
			
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		Action action = e.getAction();
		Block block = e.getClickedBlock();
		
		if(p.getWorld().getName().equals(main.world)){
			
			if(e.getItem() != null && e.getItem().equals(main.getKeyN()) && action == Action.RIGHT_CLICK_AIR){
				
				p.teleport(new Location(Bukkit.getWorld(main.nether), 15.013, 49, -24.014, 90.0f, 0.0f));
				p.sendMessage("§7Vous avez été téléporté dans le nether");
				
			}
			
		} else if(p.getWorld().getName().equals(main.nether)){
			
			if(e.getItem().equals(main.getKeyN()) && action == Action.RIGHT_CLICK_AIR){
				
				p.teleport(new Location(Bukkit.getWorld(main.world), -1735.595, 84, -201.599, 180.0f, 0.0f));
				p.sendMessage("§7Vous avez été téléporté dans l'overworld");
				
			}
			
		}
		
		if(p.getName().equalsIgnoreCase(main.ownerN)) return;
		

		
		if(!p.getInventory().contains(main.perm) && p.getWorld().getName().equalsIgnoreCase(main.nether)){
			
			p.sendMessage("§cVous n'avez pas la permission d'interagir avec les blocs ici !");
			
			e.setCancelled(true);
			
		
		}
	}
	
	@EventHandler
	public void onBreakNether(BlockBreakEvent e){
		
		Block block = e.getBlock();
		Player breaker = e.getPlayer();
		
		if(main.randomChance(15) && block.getType() == Material.DIAMOND_ORE && !(breaker.getGameMode() == GameMode.CREATIVE)){
			Bukkit.broadcastMessage("§e"+breaker.getName()+" §3a trouvé un §bDiamant §3en§a x : "+block.getX()+", y : "+block.getY()+", z : "+block.getZ()+" !");
		}
		
		if(breaker.getName().equalsIgnoreCase("LeRoiDesKiwis")) return;
		if(!breaker.getInventory().contains(main.perm) && breaker.getWorld().getName().equalsIgnoreCase(main.nether)){
			
			breaker.sendMessage("§cVous n'avez pas la permission de casser ici !");
			
			e.setCancelled(true);
			
		}
		
	}
	
	
	
	@EventHandler
	public void onChunkLoaded(ChunkLoadEvent e){
		
		if(e.isNewChunk()) {
			
			for(Entity entity : e.getChunk().getEntities()){
				
				if(entity instanceof Player){
					((Player)entity).sendMessage("§aVous avez découvert un nouveau chunk :D");
					
					ItemStack recompense = new ItemStack(Material.DIAMOND);
					ItemMeta meta = recompense.getItemMeta();
					meta.setDisplayName("§brécompense pour avoir découvert un nouveau chunk :D");
					recompense.setItemMeta(meta);
					
					((Player)entity).getInventory().addItem(recompense);
					
				}
				
			}
		}
		
	
	}
}
