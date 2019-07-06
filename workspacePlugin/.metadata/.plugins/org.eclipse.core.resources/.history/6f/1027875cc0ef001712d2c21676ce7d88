package fr.leroideskiwis.inventory.inventories;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.leroideskiwis.inventory.CustomInventory;
import fr.leroideskiwis.inventory.Main;

public class Gamemode implements CustomInventory {

	private Main main;
	
	public Gamemode(Main main) {
		this.main = main;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "§8mode de jeux";
	}

	@Override
	public void contents(Player player, Inventory inv) {
		ItemStack survie = itemWithMeta(Material.WOOD_AXE, "§asurvie");
		ItemStack creatif = itemWithMeta(Material.DIAMOND_BLOCK, "§3creatif");
		ItemStack aventure = itemWithMeta(Material.WOOD_HOE, "§6aventure");
		ItemStack spectateur = itemWithMeta(Material.GLASS, "§7spectateur");
		ItemStack retour = itemWithMeta(Material.ARROW, "§cretour");
		
		inv.setItem(11, survie);
		inv.setItem(12, creatif);
		inv.setItem(13, aventure);
		inv.setItem(14, spectateur);
		inv.setItem(26, retour);
		
		

	}

	@Override
	public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
		
		switch(current.getType()){
		
		case WOOD_AXE:
			player.setGameMode(GameMode.SURVIVAL);
			break;
		case DIAMOND_BLOCK:
			player.setGameMode(GameMode.CREATIVE);
			break;
		case WOOD_HOE:
			player.setGameMode(GameMode.ADVENTURE);
			break;
		case GLASS:
			player.setGameMode(GameMode.SPECTATOR);
			break;
		case ARROW:
			main.open(player, UtilMenu.class);
			break;
			
		default:
			break;
		
		}

	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 9*3	;
	}
	
	private ItemStack itemWithMeta(Material item, String displayName){
		if(displayName == null || displayName.equals("")) return new ItemStack(item);
		
		ItemStack item2 = new ItemStack(item);
		ItemMeta meta = item2.getItemMeta();
		meta.setDisplayName(displayName);
		item2.setItemMeta(meta);
		return item2;
		
	}

}
