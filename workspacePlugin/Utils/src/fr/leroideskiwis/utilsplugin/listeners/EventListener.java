package fr.leroideskiwis.utilsplugin.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import fr.leroideskiwis.utilsplugin.Main;
import net.md_5.bungee.api.ChatColor;

public class EventListener implements Listener {

	private List<Player> skeletonPlayer = new ArrayList<>();
	private Main main;
	private List<Material> toexplode = new ArrayList<>();

	public EventListener(Main main) {
		this.main = main;
		toexplode.add(Material.TNT);
		toexplode.add(Material.CACTUS);
		toexplode.add(Material.SANDSTONE);
	}

	//@EventHandler
	public void onSpawn(EntitySpawnEvent e){
		
		if(!(e.getEntity() instanceof Player)){
			
			Entity entity = e.getEntity();
			
			if(entity.getType() == EntityType.PIG_ZOMBIE || entity.getType() == EntityType.GHAST) e.setCancelled(true);
						
		}
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		
		e.setFormat((e.getPlayer().isOp() ? "§4[§cOP§4]§c" : "§8[§7Joueur§8]§7")+" "+e.getPlayer().getName()+"§8 : §7"+e.getMessage());
		
	}
	
	
	
	//@EventHandler
	public void onExplode(EntityExplodeEvent e){
		
		List<Block> blocklist = e.blockList();
		Iterator<Block> iterator = blocklist.iterator();
		
		
		while(iterator.hasNext()){
			
			Block b = iterator.next();
			
			if(toexplode.contains(b.getType())) continue;
			
			iterator.remove();
			blocklist.remove(b);
			
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		
		
		
		if(e.getEntity() instanceof Player){

			Player victim = (Player)e.getEntity();
			Location vLoc = victim.getLocation();
			victim.getWorld().spawnEntity(vLoc, EntityType.LIGHTNING);
			World w = victim.getWorld();
			
			
			vLoc.getBlock().setType(Material.CHEST);
			
			if(vLoc.getBlock().getType() == Material.CHEST) {
				Chest chest = (Chest) vLoc.getBlock().getState();
				
				if(victim.getInventory().getSize() == 0) chest.getInventory().setItem(13, new ItemStack(Material.BARRIER));
				
				for(int i = 0; i < victim.getInventory().getSize(); i++){
					
					chest.getInventory().setItem(i, victim.getInventory().getItem(i));
					
				}
				
			}
			
			if(main.randomChance(20)){
				victim.getWorld().dropItem(vLoc, new ItemStack(Material.GOLDEN_APPLE, new Random().nextInt(11) + 5));
			}
			
			if(main.randomChance(100)){
				w.dropItem(vLoc, new ItemStack(Material.GOLDEN_APPLE, 1, (short)1));
			}
			
			
			
		} else if(e.getEntity() instanceof Villager){
			
			Zombie zombie = (Zombie)e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
			zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
			zombie.setVillager(true);
			
			Profession[] professions = {Profession.BLACKSMITH, Profession.BUTCHER, Profession.FARMER, Profession.PRIEST};
			
			zombie.setVillagerProfession(professions[new Random().nextInt(professions.length - 1)]);
			
			List<Entity> entities = new ArrayList<>();
			
			entities.add(e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(4.0, 0.0, 0.0), EntityType.LIGHTNING));
			entities.add(e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(4.0, 0.0, 4.0), EntityType.LIGHTNING));
			entities.add(e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation().add(-4.0, 0.0, 4.0), EntityType.LIGHTNING));
			
			for(Entity entity : entities){
				
				if(entity.getType() == EntityType.LIGHTNING){
					
					LightningStrike light = (LightningStrike)entity;
					
					light.setTicksLived(40);
					
					
				}
				
			}
			
		} else if(e.getEntity() instanceof Zombie){
			
			if(((Zombie)e.getEntity()).isVillager()){
				
				e.getEntity().setHealth(20.0f);
				
			}
			
		}
		
	}
	
	public String convertChatColor(String s){
		
		return ChatColor.translateAlternateColorCodes('&', s);
		
	}
	
	@EventHandler
	public void onWriteOnBook(PlayerEditBookEvent e){
		
		e.getNewBookMeta().setTitle(convertChatColor(e.getNewBookMeta().getTitle()));
		
		BookMeta metaBook = e.getNewBookMeta();
		
		for(int i = 0; i < e.getNewBookMeta().getPages().size(); i++){
			
			String current = metaBook.getPages().get(i);
			metaBook.setPage(i, convertChatColor(current));
			
			
		}
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		
		if(e.getEntity() instanceof Player){
			Player victim = (Player)e.getEntity();
			
			if(main.getGods().containsKey(victim)){
				
				if((main.getGods().get(victim) == 0 || main.getGods().get(victim) == 2) && e.getCause() == DamageCause.VOID) return;
				
				e.setCancelled(true);
				if(!(main.getGods().get(victim) == 3) && !(main.getGods().get(victim) == 2)) victim.sendMessage("§cLe degat de type§e "+e.getCause().toString().toLowerCase(Locale.ROOT)+"§c a été annulé : vous êtes en §emode Dieu !");
			}
	
		}
		
	}
	
}
