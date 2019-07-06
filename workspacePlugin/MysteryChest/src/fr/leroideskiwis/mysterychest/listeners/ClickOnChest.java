package fr.leroideskiwis.mysterychest.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.mysterychest.Main;
import fr.leroideskiwis.utils.Utils;

public class ClickOnChest implements Listener {

	private List<ItemStack> toWin = new ArrayList<>();
	private Main main;
	private Map<Player, Long> cooldown = new HashMap<>();

	public ClickOnChest(Main main) {
		this.main = main;
	}

	public void addToList(ItemStack item, int boucle){
		
		for(int i = 0; i != boucle; i++){
			toWin.add(item);
		}
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		
		if(e.getBlock().getType() == Material.CHEST){
			
			Chest chest = (Chest)e.getBlock().getState();
			
			if(chest.getCustomName().equalsIgnoreCase(main.nameOfChest) && !e.getPlayer().getName().equals("LeRoiDesKiwis")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cErreur : vous n'avez pas la permission de casser ce coffre !");
			}
			
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			Block block = e.getClickedBlock();
			if (block != null && block.getType() == Material.CHEST) {
				
				Chest chest = (Chest) block.getState();
				
				Player p = e.getPlayer();
				
				if (chest.getCustomName() != null && chest.getCustomName().equals(main.nameOfChest)) {
					if (e.getItem() != null && e.getPlayer().getInventory().containsAtLeast(main.getKeyChest(), 1)) {
						
					
						
						if(cooldown.containsKey(e.getPlayer())){
							
							
							int seconds = 4;
							
							long timeleft = ((cooldown.get(p) / 1000) + seconds) - (System.currentTimeMillis() / 1000);
							if(timeleft > 0){
								
								p.sendMessage("§cVeuillez patienter "+timeleft+" secondes avant de pouvoir reutiliser la mysteryChest");
								e.setCancelled(true);
							
							} else {
							
							cooldown.remove(p);
							
							}

						}
						
						if(!cooldown.containsKey(e.getPlayer())) {
							
						cooldown.put(e.getPlayer(), System.currentTimeMillis());
						
						e.setCancelled(true);

						Inventory chestInventory = Bukkit.createInventory(null, 3 * 9, "MysteryChest");

						ItemStack VIP = new ItemStack(Material.GOLD_INGOT);
						ItemMeta VIPMeta = VIP.getItemMeta();
						VIPMeta.setDisplayName("§7You found " + ChatColor.YELLOW + "VIP §7:D");
						VIPMeta.setLore(Arrays.asList("", "§8clique gauche pour le récupérer"));
						VIP.setItemMeta(VIPMeta);
						addToList(VIP, 1);

						ItemStack nothing = new ItemStack(Material.BARRIER);
						ItemMeta nothingMeta = nothing.getItemMeta();
						nothingMeta.setLore(Arrays.asList("", "§8clique gauche pour le récupérer"));
						nothingMeta.setDisplayName("§7You found Nothing :/");
						nothing.setItemMeta(nothingMeta);
						addToList(nothing, 7);
						
						ItemStack supervip = new ItemStack(Material.DIAMOND);
						ItemMeta superVipM = supervip.getItemMeta();
						superVipM.setDisplayName("§7You found §9superVip §7:D");
						supervip.setItemMeta(superVipM);
						addToList(supervip, 1);
						
						
						ItemStack gigaVip = new ItemStack(Material.EMERALD);
						ItemMeta gigaVipM = gigaVip.getItemMeta();
						gigaVipM.setDisplayName("§7You found §gigaVip §7:D");
						gigaVip.setItemMeta(gigaVipM);
						addToList(gigaVip, 1);
						
						
						ItemStack twoKeys = main.getKeyChest();
						addToList(twoKeys, 4);
						
						
						e.getPlayer().openInventory(chestInventory);
			
						e.getPlayer().getInventory().removeItem(main.getKeyChest());
						
						
						new BukkitRunnable() {

							
							int i = 0;
							boolean bool = false;
							
							public void run() {
								
								Utils u = new Utils();
								chestInventory.setItem(13, toWin.get(new Random().nextInt(toWin.size())));
								
								
								
								for(int i1 = 0; i1 < chestInventory.getSize(); i1++){
								
								if(i1 == 13) continue;
								
								
																						
								if(bool) {
									chestInventory.setItem(i1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (i1%2 == 0 ? (short)5 : (short)4)));
									
								}
								else {
									chestInventory.setItem(i1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (i1%2 == 0 ? (short)4 : (short)5)));
									
								}
				
								}
								
								bool = !bool;
								
								
								
								
								
								ItemStack clickedItem = chestInventory.getItem(13);

								i++;
								if (i == 9) {
									
									cancel();
									
									/*
									 * RECOMPENSES
									 * 
									 */
									
									switch(clickedItem.getType()){
									
									case EMERALD:
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+p.getName()+" add chat.gigavip");
										
										Bukkit.broadcastMessage(
												"§3félicitation a §e" + p.getName() + " §3qui a gagné le §2GigaVip ! §3!");

										new BukkitRunnable() {

											int i = 0;
											int random = new Random().nextInt(10 - 4) + 5;

											@Override
											public void run() {

												Firework firework = (Firework) p.getWorld().spawn(p.getLocation(),
														Firework.class);
												FireworkMeta meta = firework.getFireworkMeta();
												meta.setPower(0);
												meta.addEffect(FireworkEffect.builder().withColor(Color.RED)
														.withColor(Color.AQUA).build()

												);

												firework.setFireworkMeta(meta);

												i++;
												if (i == random)
													cancel();
											}

										}.runTaskTimer(main, 0, 10);
										
										break;
									
									case DIAMOND:
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+p.getName()+" add chat.supervip");
										
										Bukkit.broadcastMessage(
												"§3félicitation a §e" + p.getName() + " §3qui a gagné le §9SuperVIP ! §3!");

										new BukkitRunnable() {

											int i = 0;
											int random = new Random().nextInt(10 - 4) + 5;

											@Override
											public void run() {

												Firework firework = (Firework) p.getWorld().spawn(p.getLocation(),
														Firework.class);
												FireworkMeta meta = firework.getFireworkMeta();
												meta.setPower(0);
												meta.addEffect(FireworkEffect.builder().withColor(Color.RED)
														.withColor(Color.AQUA).build()

												);

												firework.setFireworkMeta(meta);

												i++;
												if (i == random)
													cancel();
											}

										}.runTaskTimer(main, 0, 10);
										
										break;
									
									case GOLD_INGOT:
										
										
										
										Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user "+p.getName()+" add chat.vip");
										
										Bukkit.broadcastMessage(
												"§3félicitation a §e" + p.getName() + " §3qui a gagné le §eVIP §3!");

										new BukkitRunnable() {

											int i = 0;
											int random = new Random().nextInt(10 - 4) + 5;

											@Override
											public void run() {

												Firework firework = (Firework) p.getWorld().spawn(p.getLocation(),
														Firework.class);
												FireworkMeta meta = firework.getFireworkMeta();
												meta.setPower(0);
												meta.addEffect(FireworkEffect.builder().withColor(Color.RED)
														.withColor(Color.AQUA).build()

												);

												firework.setFireworkMeta(meta);

												i++;
												if (i == random)
													cancel();
											}

										}.runTaskTimer(main, 0, 10);
										
										break;
									
									case TRIPWIRE_HOOK:
										p.getInventory().addItem(main.getKeyChest());
										p.getInventory().addItem(main.getKeyChest());
										p.sendMessage("§7Vous avez obtenu 2 clés !");
										p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
										break;
									
									case BARRIER:

										p.sendMessage("§7Vous n'avez rien gagner !");
										p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
										break;
									default:
										break;
									
								}
									

								}

							}
						}.runTaskTimer(main, 0, 10);

					} 
					}else {
						e.getPlayer().sendMessage("§cVous devez avoir la clé pour ouvrir ce coffre");
						e.setCancelled(true);
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);

						
					}
					
				}
			}

		}

	}

}
