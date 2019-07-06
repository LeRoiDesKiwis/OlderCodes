package fr.leroideskiwis.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private ItemStack item;
	private ItemMeta meta;
	
	
	public ItemBuilder(Material material) {
		
		this.item = new ItemStack(material);
		
	}
	
	public ItemBuilder setDisplayName(String name){
		meta.setDisplayName(name);
		return this;
	}
	
	public ItemBuilder setLore(List<String> lore){
		meta.setLore(lore);
		return this;
	}
	
	public ItemStack build(){
		
		item.setItemMeta(meta);
		
		return item;
	}

}
