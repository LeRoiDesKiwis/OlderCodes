package fr.leroideskiwis.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.inventory.commands.CommandUtils;
import fr.leroideskiwis.inventory.listeners.InteractionEvent;

public class Main extends JavaPlugin{
	
	private List<Inventory> inventaires = new ArrayList<>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new InteractionEvent(this), this);
		getCommand("utils").setExecutor(new CommandUtils(this));
		
	}
	
	public List<Inventory> getInventories(){
		return inventaires;
	}
	
	public void registerInventories(){
		/*
		 *  UTILS MENU
		 * 
		 */
		
		Inventory utils = Bukkit.createInventory(null, 9*3, "§8utilsMenu");
		ItemStack gamemode = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta meta = gamemode.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "changer de mode de jeu");
		gamemode.setItemMeta(meta);

		ItemStack suicide = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta suicidemeta = suicide.getItemMeta();
		suicidemeta.setDisplayName("§csuicide toi");
		suicide.setItemMeta(suicidemeta);
		
		utils.setItem(0, gamemode);
		utils.setItem(1, suicide);
		
		/*
		 * GAMEMODE MENU (relié au utils menu)
		 * 
		 */
		
		Inventory gamemodeChange = Bukkit.createInventory(null, 9*3, "§8gamemodeMenu");
		
		ItemStack survie = itemWithMeta(Material.WOOD_AXE, "§asurvie");
		ItemStack creatif = itemWithMeta(Material.DIAMOND_BLOCK, "§3creatif");
		ItemStack aventure = itemWithMeta(Material.WOOD_HOE, "§6aventure");
		ItemStack spectateur = itemWithMeta(Material.GLASS, "§7spectateur");
		ItemStack retour = itemWithMeta(Material.ARROW, "§cretour");
		
		gamemodeChange.setItem(11, survie);
		gamemodeChange.setItem(12, creatif);
		gamemodeChange.setItem(13, aventure);
		gamemodeChange.setItem(14, spectateur);
		gamemodeChange.setItem(26, retour);
		
		/*
		 * SAUVEGARDE DES MENUS
		 * 
		 */
		
		inventaires.add(gamemodeChange);
		inventaires.add(utils);
	}
	
	public ItemStack itemWithMeta(Material item, String displayName){
		if(displayName == null || displayName.equals("")) return new ItemStack(item);
		
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		item2.setItemMeta(meta);
		return item2;
		
	}

}