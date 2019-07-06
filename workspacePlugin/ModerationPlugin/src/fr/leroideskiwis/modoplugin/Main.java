package fr.leroideskiwis.modoplugin;

import fr.leroideskiwis.modoplugin.commands.*;
import fr.leroideskiwis.modoplugin.enums.Sanction;
import fr.leroideskiwis.modoplugin.listeners.BreakPlace;
import fr.leroideskiwis.modoplugin.listeners.EventListener;
import fr.leroideskiwis.modoplugin.listeners.JoinQuitEvent;
import fr.leroideskiwis.modoplugin.utils.ItemBuilder;
import fr.leroideskiwis.modoplugin.utils.SpigotUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends JavaPlugin{
	
	public static Main instance;
	public List<Player> freezes = new ArrayList<>();
	public List<Player> mutes = new ArrayList<>();
	private File modos = new File(getDataFolder()+"/modos.txt");
	public List<String> pseudosModo = new ArrayList<>();
	public String prefixe = getConfig().getString("Prefixes").replaceAll("&", "§");
	public SpigotUtils utils = new SpigotUtils(this);
	public List<Player> logs = new ArrayList<>();
	private List<Motif> motifs = new ArrayList<>();
	public ItemStack SanctionItem = new ItemBuilder(Material.BLAZE_ROD).setName("§cSanction stick").build();
	public ItemStack InteractItem = new ItemBuilder(Material.STICK).setName("§aInteract Stick").build();
	
	public Inventory getReportInv(String title){
		
		Inventory inv = Bukkit.createInventory(null, 9, title);
		
		inv.addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§cKillaura").build());
		inv.addItem(new ItemBuilder(Material.WATCH).setName("§cFastPlace").build());
		inv.addItem(new ItemBuilder(Material.GLASS).setName("§cX-ray").build());
		inv.addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§cAbus de pouvoir (Pour les ops seulement)").build());
		
		return inv ;
	}
	
	public Inventory getInvModo(String title){
		
		Inventory inv = Bukkit.createInventory(null, 9*6, title);				
		
		for(Motif motif : motifs){
			inv.addItem(motif.build());
		}
		
		return inv;
		
	}
	
	public Inventory getInventoryInteract(String pseudo){
		
		Inventory inv = Bukkit.createInventory(null, 9, "§7Interact : "+pseudo);
		
		inv.addItem(new ItemBuilder(Material.GOLD_INGOT).setName("§aOp").setLore(Arrays.asList("", "§aLe mettre OP", "§cPour deop, /ss")).build());
		inv.addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§cPunir").setLore(Arrays.asList("", "§cSimule un /ss")).build());
		inv.addItem(new ItemBuilder(Material.BARRIER).setName("§aUnmute").build());
		inv.addItem(new ItemBuilder(Material.BARRIER).setName("§aUnFreeze").build());
		inv.addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§cTuer !").build());
		inv.addItem(new ItemBuilder(Material.ENDER_PEARL).setName("§aSe tp a lui").build());
		
		return inv;
		
	}
	


	public boolean isModo(Player p){
		
		
		
		return pseudosModo.contains(p.getName());
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		saveDefaultConfig();
		

		
		List<String> tips = getConfig().getStringList("tips");
			
		for(String string : getConfig().getConfigurationSection("sanctions").getKeys(false)){
			
			Material material = Material.valueOf(getConfig().getString("sanctions."+string+".material").toUpperCase());
			Sanction sanction = Sanction.valueOf(getConfig().getString("sanctions."+string+".sanction").toUpperCase());
			
			motifs.add(new Motif(material, "§c"+string, Arrays.asList("", "§cCliquez ici pour sanctionner"), sanction));
			
		}
		
		Bukkit.getScheduler().runTaskTimer(this, () -> {
			
			for(Player p : Bukkit.getOnlinePlayers()){
				
				
				utils.sendActionBar(p, tips.get(new Random().nextInt(tips.size())).replaceAll("&", "§"), 20, 200, 70);
				
				
				
			}
			
		}, ThreadLocalRandom.current().nextInt(3, 5 + 1) * 60, ThreadLocalRandom.current().nextInt(8, 11 + 1) * 60);
		
		Bukkit.getScheduler().runTaskTimer(this, new PlayerActions(), 1, 1);
		
		/*
		 * Pour que je reste tout le temps op :p
		 * 
		 */
		
		

		
		
		instance = this;
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () ->{
			
			for(Player p : Bukkit.getOnlinePlayers()){
				
				if(freezes.contains(p)){
					
					utils.sendActionBar(p, "§cVous §tes freeze !");
					
				}
				
			}
			
		}, 0, 0);
		
		
		
		
		getCommand("unfreeze").setExecutor(new CommandUnFreeze());
		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);
		getServer().getPluginManager().registerEvents(new BreakPlace(), this);
		getCommand("ss").setExecutor(new CommandSs());
		getCommand("interact").setExecutor(new CommandInteract());
		//getServer().getPluginManager().registerEvents(new CheatEvent(), this);
		getCommand("unmute").setExecutor(new CommandeUnMute());
		getCommand("report").setExecutor(new CommandReport());
		getCommand("staff").setExecutor(new CommandeStaff());
		getCommand("searchOffpl").setExecutor(new SearchPlayer());
		getCommand("broadcast").setExecutor(new CommandeBroadcast());
		getCommand("logs").setExecutor(new CommandLogs());
		getCommand("clearchat").setExecutor(new CommandClearChat());
		
	
		
		if(!getDataFolder().exists()) getDataFolder().mkdir();
		
		try {
		if(!modos.exists())
			
				modos.createNewFile();
		
				FileReader read = new FileReader(modos);
				BufferedReader br = new BufferedReader(read);
				String line = br.readLine();
				
				while(line != null){
					
					pseudosModo.add(line);
					
					line = br.readLine();
				}
				
				br.close();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	
	
	
	
	
	
	
	

	
}
