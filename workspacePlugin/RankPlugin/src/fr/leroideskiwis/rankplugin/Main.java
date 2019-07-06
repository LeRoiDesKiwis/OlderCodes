package fr.leroideskiwis.rankplugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	private FileConfiguration config;
	
	public void saveRankConfig() throws Exception{
		
		if(!getDataFolder().exists()) getDataFolder().mkdir();
		
		File fileRanks = new File(getDataFolder()+"/ranks.yml");
		if(!fileRanks.exists()) fileRanks.createNewFile();
		
		config = YamlConfiguration.loadConfiguration(fileRanks);
		
		config.createSection("ranks.admin");
		
		
	}
	
	public FileConfiguration getRankConfig(){
		return config;
	}
	
}
