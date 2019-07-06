package fr.leroideskiwis.modoplugin.utils;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private ItemStack stack;
	private ItemMeta meta;
	
	public ItemBuilder(Material material, short damage) {
		this.stack = new ItemStack(material, 1, damage);
		this.meta = this.stack.getItemMeta();
	}
	
	public ItemBuilder(Material material) {
		this.stack = new ItemStack(material);
		this.meta = this.stack.getItemMeta();
	}
	
	public ItemBuilder setLore(List<String> strings){
		meta.setLore(strings);
		return this;
	}
	
	public ItemBuilder setName(String name){
		meta.setDisplayName(name);
		return this;
	}
	
	
	public ItemStack build(){
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		stack.setItemMeta(meta);
		return stack;
	}
	
}
