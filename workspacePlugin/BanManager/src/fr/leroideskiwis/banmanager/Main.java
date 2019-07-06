package fr.leroideskiwis.banmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.banmanager.ban.Ban;
import fr.leroideskiwis.banmanager.commands.CommandBan;
import fr.leroideskiwis.banmanager.commands.CommandUnBan;
import fr.leroideskiwis.banmanager.listener.ListenerPlugin;

public class Main extends JavaPlugin {

	private List<Ban> ban = new ArrayList<>();
	private FileConfiguration config;
	
	@Override
	public void onEnable() {
		try{
		if(!getDataFolder().exists()) getDataFolder().mkdir();
		File file = new File(getDataFolder()+"/"+"bans");
		config.set("bans", "");
		config.save(file);
		for(String part : config.getConfigurationSection("bans").getKeys(false)){
			
			ban.add(new Ban(this, part, config.getString(part+".banner"), config.getString(part+".reason")));
			
		}
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ListenerPlugin(this), this);
		getCommand("ban").setExecutor(new CommandBan(this));
		getCommand("unban").setExecutor(new CommandUnBan(this));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onDisable() {
		
		try{
		if(!getDataFolder().exists()) getDataFolder().mkdir();
		File file = new File(getDataFolder()+"/"+"bans");
		if(!file.exists()) file.createNewFile();
		if(ban.isEmpty()) return;
		
		config = YamlConfiguration.loadConfiguration(file);
		
		for(String part : config.getConfigurationSection("bans.").getKeys(false)){
			
			boolean isTrue = false;
			
			
			for(int i = 0; i < ban.size(); i++){
				if(ban.get(i).getBanned().equals(part)) isTrue = true;
			}
			
			if(!isTrue) {
				config.set("bans."+part, "");
				config.set("bans."+part+".reason", "");
				config.set("bans."+part+"banner", "");
			}
			
		}
		
		for(Ban ban : getBan()){
			config.set("bans."+ban.getBanned()+".banner", ban.getBanner());
			config.set("bans."+ban.getBanned()+".reason", ban.getReason());
		}
		
		config.save(file);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public List<Ban> getBan(){
		return ban;
	}
	
}
