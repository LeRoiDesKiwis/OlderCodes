package fr.leroideskiwis.gamemanager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.gamemanager.commands.CommandBypass;
import fr.leroideskiwis.gamemanager.commands.CommandHub;
import fr.leroideskiwis.gamemanager.commands.SimulateJoinEvent;
import fr.leroideskiwis.gamemanager.listeners.ClickEvent;
import fr.leroideskiwis.gamemanager.listeners.ClickInvEvent;
import fr.leroideskiwis.gamemanager.listeners.JoinEvent;
import fr.leroideskiwis.gamemanager.tasks.TaskBroadcast;

public class Main extends JavaPlugin{

	public List<Player> bypass = new ArrayList<>();
	public Inventory compassInventory = Bukkit.createInventory(null, 9*6, "sélécteur de mini-jeux");
	
	public void registerInventory(){
		
		ItemStack glass3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
		ItemStack glass11 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
		
		compassInventory.setItem(0, glass3);
		compassInventory.setItem(1, glass11);
		compassInventory.setItem(9, glass11);
		
		compassInventory.setItem((9*6)-1, glass3);
		compassInventory.setItem((9*6)-2, glass11);
		compassInventory.setItem(44, glass11);
		
		compassInventory.setItem(8, glass3);
		compassInventory.setItem(7, glass11);
		compassInventory.setItem(17, glass11);
		
		compassInventory.setItem(45, glass3);
		compassInventory.setItem(46, glass11);
		compassInventory.setItem(36, glass11);
		
		ItemStack enderpearlG = new ItemStack(Material.ENDER_PEARL);
		ItemMeta metaEnder = enderpearlG.getItemMeta();
		metaEnder.setDisplayName("§aEnderPearl Battle");
		
		ItemStack tntRun = new ItemStack(Material.TNT);
		ItemMeta TNTmeta = tntRun.getItemMeta();
		TNTmeta.setDisplayName("§cTntRun");
		
		ItemStack floorLava = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta lavaMeta = floorLava.getItemMeta();
		lavaMeta.setDisplayName("§cThe floor is lava");
		
		ItemStack murder = new ItemStack(Material.IRON_SWORD);
		ItemMeta murderMeta = murder.getItemMeta();
		murderMeta.setDisplayName("§cMurder Mystery");
		
		murder.setItemMeta(murderMeta);
		floorLava.setItemMeta(lavaMeta);
		enderpearlG.setItemMeta(metaEnder);
		tntRun.setItemMeta(TNTmeta);
		
		
		
		compassInventory.setItem(21, enderpearlG);
		compassInventory.setItem(22, tntRun);
		compassInventory.setItem(23, floorLava);
		compassInventory.setItem(24, murder);
		
	}
	
	@Override
	public void onEnable() {
		registerInventory();
		
		new TaskBroadcast().runTaskTimer(this, 20, (20*60)*3);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new JoinEvent(this), this);
		pm.registerEvents(new ClickEvent(this), this);
		pm.registerEvents(new ClickInvEvent(this), this);
		getCommand("bypass").setExecutor(new CommandBypass(this));
		getCommand("join_s").setExecutor(new SimulateJoinEvent(this));
		getCommand("hub").setExecutor(new CommandHub());
		
	}
	
}
