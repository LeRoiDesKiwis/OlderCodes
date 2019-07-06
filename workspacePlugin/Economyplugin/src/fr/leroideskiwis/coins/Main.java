package fr.leroideskiwis.coins;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.leroideskiwis.coins.commands.CommandCoins;

public class Main extends JavaPlugin {

	private Map<Player, Double> coins = new HashMap<>();
	private static Main instance;
	private FileConfiguration configCoins;
	private File configCoin = new File(getDataFolder() + "/coins.yml");

	public Map<Player, Double> getCoins() {
		return coins;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		try{
		registerCoinsConfig();
		instance = this;
		
		getConfigCoins().getConfigurationSection("database").getKeys(false).forEach(string -> {
			
			
			Player p = Bukkit.getPlayer(string);
			
			if(p == null) p = (Player) Bukkit.getOfflinePlayer(string);
			if(p != null) coins.put(p, getConfig().getDouble("database."+string));
			
		});
		
		
		
		getCommand("coins").setExecutor(new CommandCoins(this));
		
		new BukkitRunnable(){

			@Override
			public void run() {
				for(Player online : Bukkit.getOnlinePlayers()){
					if(coins.containsKey(online)) continue;
					else coins.put(online, 0.0);
				}
				
				
				getConfig().getConfigurationSection("database").getKeys(false).forEach(string -> {
					
					
					Player p = Bukkit.getPlayer(string);
					
					if(p != null) coins.put(p, getConfig().getDouble("database."+string));
					
				});
				
				
			}
			
		}.runTaskTimer(this, 0, 1);
		
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {

		try {
			coins.forEach((p, d) -> {

				getConfigCoins().set("database." + p.getName(), d);

			});
			getConfigCoins().save(getConfigCoinsFile());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void registerCoinsConfig() throws Exception {

		if (!getDataFolder().exists())
			getDataFolder().mkdir();
		configCoin = new File(getDataFolder() + "/coins.yml");
		if (!configCoin.exists())
			configCoin.createNewFile();
		configCoins = YamlConfiguration.loadConfiguration(configCoin);
		
		configCoins.save(configCoin);

	}

	public File getConfigCoinsFile() {
		return configCoin;
	}

	public FileConfiguration getConfigCoins() {
		return configCoins;
	}

	public static Main getInstance() {
		return instance;
	}

}
