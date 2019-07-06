package fr.leroideskiwis.inventory.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.leroideskiwis.inventory.Main;

public class UtilMenu {

	private Main main;

	public UtilMenu(Main main) {
		this.main = main;
	}

	
	public String name() {
		// TODO Auto-generated method stub
		return "§8utilitaire";
	}

	
	public void contents(Player player, Inventory inv) {

		ItemStack gamemode = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta meta = gamemode.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "changer de mode de jeu");
		gamemode.setItemMeta(meta);

		ItemStack suicide = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta suicidemeta = suicide.getItemMeta();
		suicidemeta.setDisplayName("§csuicide toi");
		suicide.setItemMeta(suicidemeta);


		
		inv.setItem(0, gamemode);
		inv.setItem(1, suicide);

	}

	
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
		switch (current.getType()) {
		case GOLD_BLOCK:
			//main.open(player, Gamemode.class);
			break;

		case DIAMOND_SWORD:
			player.setHealth(0.0);
			break;

		default:
			break;

		}

	}

	
	public int getSize() {
		// TODO Auto-generated method stub
		return 9 * 3;
	}

}
