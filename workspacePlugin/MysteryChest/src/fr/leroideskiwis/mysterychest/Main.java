package fr.leroideskiwis.mysterychest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.mysterychest.commands.CommandGetKey;
import fr.leroideskiwis.mysterychest.commands.CommandRegisterChest;
import fr.leroideskiwis.mysterychest.listeners.ClickOnChest;
import fr.leroideskiwis.mysterychest.listeners.ClickOnInv;
import fr.leroideskiwis.mysterychest.listeners.OtherEvents;

public class Main extends JavaPlugin{
	
	public String nameOfChest = "MysteryChest";
	public String errorSender = "�cVous devez etre un joueur pour executer cette commande !";
	public List<Player> jump = new ArrayList<>();
	
	private ItemStack keyChest = new ItemStack(Material.TRIPWIRE_HOOK);
	
	
	
	public ItemStack getKeyChest(){
		return keyChest;
	}
	
	@Override
	public void onDisable() {
	
		try {
			getConfig().save(new File(getDataFolder()+"/config.yml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onEnable() {
		
		
	
		
		ItemMeta meta = keyChest.getItemMeta();
		
		meta.setDisplayName("�7cl� du coffre des recompenses");
		meta.setLore(Arrays.asList("","�8Cette cl� te permet d'acceder au coffre des recompenses"));
		keyChest.setItemMeta(meta);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new OtherEvents(this), this);
		pm.registerEvents(new ClickOnChest(this), this);
		pm.registerEvents(new ClickOnInv(this), this);
		registerCommand("getkey", new CommandGetKey(this));
		registerCommand("registerChest", new CommandRegisterChest(this));
		
		
	}
	
	public void registerCommand(String name, CommandExecutor executor){
			
		getCommand(name).setExecutor(executor);
		
	}
	
	public boolean isPlayer(CommandSender sender){
		
		if(sender instanceof Player) return true;
		else {
			sender.sendMessage(errorSender);
			return false;
		}
		
	}
	
}
