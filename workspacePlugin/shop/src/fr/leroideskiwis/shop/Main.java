package fr.leroideskiwis.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.shop.listeners.ListenerOnClick;
import fr.leroideskiwis.utils.ItemBuilder;

public class Main extends JavaPlugin{

	private List<Inventory> inventories = new ArrayList<>();
	private fr.leroideskiwis.coins.Main eMain = fr.leroideskiwis.coins.Main.getInstance();
	public String nameShop = "§7shop";
	
	public fr.leroideskiwis.coins.Main getEMain(){
		return eMain;
	}
	
	@Override
	public void onEnable(){
		
		getCommand("shop").setExecutor(new CommandShop(this));
		registerInventories();
		getServer().getPluginManager().registerEvents(new ListenerOnClick(this), this);
		
	}
	
	public void registerInventories(){
		
		Inventory first = Bukkit.createInventory(null, 6*9, "§7shop");
		ItemStack key = new ItemBuilder(Material.TRIPWIRE_HOOK).setName("§7clé du coffre des récompenses").setLore(Arrays.asList("", "§8cliquez pour recuperer", "§7coûte 30 coins")).toItemStack();
			
		first.setItem(10, key);
		first.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4));
		first.setItem(1, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4));
		first.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4));
		
		inventories.add(first);
		
	}
	
	public void openInventory(Player player, String name){
		
		inventories.stream().filter(inv -> inv.getName().equals(name)).forEach(inv -> {
			
			player.closeInventory();
			player.openInventory(inv);
			
		});
		
	}
}
