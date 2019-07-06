package fr.leroideskiwis.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.modoplugin.utils.ItemBuilder;
import fr.leroideskiwis.shop.listener.Events;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin{
	
	public static Economy economy = null;
	
	@Override
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new Events(this), this);
		getCommand("shop").setExecutor(new CommandShop(this));
		
	}

	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	public Inventory getInvShop(){
		
		Inventory inv = Bukkit.createInventory(null, 9*6, "§eShop");
		
		inv.addItem(new ItemBuilder(Material.GOLD_INGOT).setName("§eVIP").build());
		inv.addItem(new ItemBuilder(Material.DIAMOND).setName("§2SuperVip").build());
		inv.addItem(new ItemBuilder(Material.EMERALD).setName("§2GigaVip").build());
		
		return inv;
		
	}
	
}
