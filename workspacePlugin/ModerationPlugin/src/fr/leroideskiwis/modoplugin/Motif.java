package fr.leroideskiwis.modoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.leroideskiwis.modoplugin.enums.Sanction;
import fr.leroideskiwis.modoplugin.utils.ItemBuilder;

public class Motif {
	
	private ItemBuilder builder;
	
	public Motif(Material mat, String title, List<String> description, Sanction sanction){
		builder = new ItemBuilder(mat);
		
		List<String> desc = new ArrayList<>();
		
		desc.add("");
		
		if(description != null){
		
		for(String s : description){
			desc.add("§c"+s);
		}
		
		desc.add("");
		}
		desc.add("§7Sanction : "+sanction.getSanction());
		
		builder.setName("§c"+title);
		builder.setLore(desc);
	}
	
	public ItemStack build(){
		return builder.build();
	}
	
}
