package fr.leroideskiwis.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.inventory.commands.CommandUtils;
import fr.leroideskiwis.inventory.inventories.Gamemode;
import fr.leroideskiwis.inventory.inventories.UtilMenu;

public class Main extends JavaPlugin implements Listener{
	
	public Map<Class<? extends CustomInventory>, CustomInventory > registeredMenus = new HashMap<>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		addMenu(new UtilMenu(this));
		addMenu(new Gamemode(this));
		getCommand("utils").setExecutor(new CommandUtils(this));
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event){
	
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		ItemStack current = event.getCurrentItem();
		
		if(event.getCurrentItem() == null) return;
		
		registeredMenus.values().stream()
		.filter(menu -> inv.getName().equalsIgnoreCase(menu.name()))
		.forEach(menu -> {
			player.closeInventory();
			menu.onClick(player, inv, current, event.getSlot());
			event.setCancelled(true);
		});
		
	}

	public void addMenu(CustomInventory m){
		this.registeredMenus.put(m.getClass(), m);
	}

	public void open(Player player, Class<? extends CustomInventory> gClass){
		
		if(!this.registeredMenus.containsKey(gClass)) return;

		CustomInventory menu = this.registeredMenus.get(gClass);
		Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
		menu.contents(player, inv);
		player.openInventory(inv);
		
	}
	
}