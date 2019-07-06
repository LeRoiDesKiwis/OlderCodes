package fr.leroideskiwis.enderpearlbattle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.enderpearlbattle.commands.CommandDisable;
import fr.leroideskiwis.enderpearlbattle.commands.CommandElimine;
import fr.leroideskiwis.enderpearlbattle.commands.CommandEnderJoin;
import fr.leroideskiwis.enderpearlbattle.commands.LeaveCommand;
import fr.leroideskiwis.enderpearlbattle.events.JoinLeaveGame;
import fr.leroideskiwis.enderpearlbattle.events.ListenerPlugin;
import fr.leroideskiwis.enderpearlbattle.events.WorldGuard;
import fr.leroideskiwis.enderpearlbattle.game.Gstatus;
import fr.leroideskiwis.enderpearlbattle.task.ReloadChestTask;
import fr.leroideskiwis.enderpearlbattle.task.runnableSign;

public class Main extends JavaPlugin{

	private BukkitRunnable runnablereloadchests = new ReloadChestTask(this);
	private List<Player> players = new ArrayList<>();
	private List<Sign> signs = new ArrayList<>();
	private Gstatus gameStatus = Gstatus.WAITING;
	private List<Location> locations = new ArrayList<>();
	private Map<Player, Inventory> inventaires = new HashMap<>();
	private List<Player> deadPlayers = new ArrayList<>();
	private Map<Location, Block> cages = new HashMap<>();
	private List<Location> locationChests = new ArrayList<>();
	private static Main instance;
	
	
	public List<Sign> getSigns(){
		return signs;
	}
	
	public BukkitRunnable getReloadChests(){
		return runnablereloadchests;
	}
	public Map<Location, Block> getCages(){
		return cages;
	}
	
	public static Main getInstance(){
		return instance;
	}
	
	public void onEnable(){
		
		instance = this;
		
		setStatus(getConfig().getBoolean("isDisable") ? Gstatus.DISABLE : Gstatus.WAITING);
		
		for(String string : getConfig().getConfigurationSection("chests").getKeys(false)){
			
			locationChests.add(new Location(Bukkit.getWorld("world"), getConfig().getConfigurationSection("chests").getInt(string + ".x"), getConfig().getConfigurationSection("chests").getInt(string + ".y"), getConfig().getConfigurationSection("chests").getInt(string + ".z")));
			
		}
		
		BukkitRunnable runnableSign = new runnableSign(this);
		runnableSign.runTaskTimer(this, 0, 1);
		locations.add(new Location(Bukkit.getWorld("world"), getConfig().getDouble("teleports.spawns.spawn1.x"), getConfig().getDouble("teleports.spawns.spawn1.y"), getConfig().getDouble("teleports.spawns.spawn1.z")));
		locations.add(new Location(Bukkit.getWorld("world"), getConfig().getDouble("teleports.spawns.spawn2.x"), getConfig().getDouble("teleports.spawns.spawn2.y"), getConfig().getDouble("teleports.spawns.spawn2.z")));
		locations.add(new Location(Bukkit.getWorld("world"), getConfig().getDouble("teleports.spawns.spawn3.x"), getConfig().getDouble("teleports.spawns.spawn3.y"), getConfig().getDouble("teleports.spawns.spawn3.z")));
		
		saveDefaultConfig();
		
		PluginManager pm = getServer().getPluginManager();
		
		registerCommand("leaveepg", new LeaveCommand(this));
		registerCommand("enderpearlgame", new CommandEnderJoin(this));
		registerCommand("elimine", new CommandElimine(this));
		registerCommand("toggleDisable", new CommandDisable(this));
		
		pm.registerEvents(new ListenerPlugin(this), this);
		pm.registerEvents(new WorldGuard(this), this);
		pm.registerEvents(new JoinLeaveGame(this), this);
		
	}

	public void onDisable(){
		
			//getConfig().set("isDisable", isStatus(Gstatus.DISABLE));
			
	}
	
	public void registerCommand(String command, CommandExecutor commandExecutor){
	
		getCommand(command).setExecutor(commandExecutor);
		
	}

	public File getConfigAtFile(){
	
		return new File(getDataFolder()+"/config.yml");
		
		
	}
	
	public long secondsToMillis(double seconds){
		
		return (long)seconds * 1000;
		
	}
	
	public boolean sendMessage(Player player, String message){
		player.sendMessage(message);
		return true;
	}
	
	public void setStatus(Gstatus status){
		this.gameStatus = status;
	}
	
	public boolean isStatus(Gstatus status){
		return status == this.gameStatus;
	}
	
	public List<Player> getPlayers(){
		return players;
	}

	public int getMaxPlayers() {
		return getConfig().getInt("maxplayers");
	}
	
	public void eliminate(Player player){
		if(!getPlayers().contains(player)) return;
		getPlayers().remove(player);
		deadPlayers.add(player);
		player.setGameMode(GameMode.SPECTATOR);
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1, 1));
		
		player.getInventory().clear();
			
		getPlayers().forEach(p -> {
			p.sendMessage("§6"+player.getName()+"§c a été eliminé !");
		});
		
		player.sendMessage("§cvous avez perdu !");
		}
		
	
		
	
	
	public List<Location> getLocationsSpawn(){
		return locations;
	}
	
	public List<Player> getDeadPlayers(){
		return deadPlayers;
	}
	
	/**
	 * 
	 * 
	 * @return la location de la config "teleports.hub"
	 */
	
	public Location getHubLocation(){
		return new Location(Bukkit.getWorld("world"), this.getConfig().getDouble("teleports.hub.x"), getConfig().getDouble("teleports.hub.y"), getConfig().getDouble("teleports.hub.z"));
	}
	
	public Map<Player, Inventory> getSaveInv(){
		return inventaires;
	}

	public void reloadChest() {
		for(Location loc : locationChests){
			if(loc == null) return;
			if(loc.getBlock() == null) return;
			if(loc.getBlock().getType() != Material.CHEST) return;
			
			ItemStack enderKB = new ItemStack(Material.STICK);
			ItemMeta meta = enderKB.getItemMeta();
			meta.addEnchant(Enchantment.KNOCKBACK, 2, false);
			enderKB.setItemMeta(meta);
			ItemStack plastron = new ItemStack(Material.IRON_CHESTPLATE);
			ItemStack sword = new ItemStack(Material.IRON_SWORD);
			
			ItemStack[] itemsRandom = {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), enderKB, plastron, sword};
			
			Chest chest = (Chest)loc.getBlock().getState();
			Inventory chestinv = chest.getInventory();
			
			List<Integer> ints = new ArrayList<>();
			
			int random = new Random().nextInt(5 - 1) + 2;
			
			for(int i = 0; i != random; i++){
				
				if(ints.contains(i)) continue;
				
				ItemStack item = itemsRandom[new Random().nextInt(itemsRandom.length)];
				if((item.getType() == Material.IRON_SWORD && !(new Random().nextInt(3) + 1 == 3)) || (item.getType() == Material.IRON_SWORD && !(new Random().nextInt(3) + 1 == 3))) continue;
				ints.add(i);
						
				chestinv.setItem(i, item);
				
			}
			
			
		}
		
	}
	
}
