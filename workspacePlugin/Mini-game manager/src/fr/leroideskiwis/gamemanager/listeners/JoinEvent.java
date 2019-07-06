package fr.leroideskiwis.gamemanager.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.leroideskiwis.gamemanager.Main;

public class JoinEvent implements Listener {

	private Main main;
	
	public JoinEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		Player p = e.getPlayer();
		Inventory PInv = p.getInventory();
		
		
		String name = "§7Joueur "+p.getName();
		
		if(p.hasPermission("chat.vip")) name ="§eVIP "+p.getName();
		if(p.isOp()) name = "§cOp "+p.getName();
		if(p.getName().equalsIgnoreCase("MaxGame113")) name = "§2pomme §a"+p.getName();
		if(p.getName().equalsIgnoreCase("LeRoiDesKiwis")) name = "§4Fondateur §c"+p.getName();
		
		System.out.println(name);
		
		p.setPlayerListName(name);
		
		if(main.bypass.contains(p)) return;
		
		p.teleport(new Location(Bukkit.getWorld("world"), -248.246, 145, 265.520, 90.0f, 0.0f));
		
		p.sendMessage("§eBon retour parmi nous, §a"+p.getName());
		
		for(int i = 0; i != 3; i++) p.sendMessage("");
		
		p.sendMessage("§3--- §e INFORMATIONS §3 ----------");
		p.sendMessage("");
		p.sendMessage("§3Pseudo : §e"+p.getName());
		p.sendMessage("§3Grade : " + (p.isOp() ? "§cOp" : (p.hasPermission("chat.pomme") ? "§2pomme" : p.hasPermission("chat.vip") ? "§eVIP" : "§7Joueur")));
		p.sendMessage("");
		p.sendMessage("§3--------------------------");
		
		for(int i = 0; i != 3; i++) p.sendMessage("");
		
		p.setGameMode(p.isOp() ? GameMode.CREATIVE : GameMode.SURVIVAL);
		
		ItemStack boussole = new ItemStack(Material.WATCH);
		ItemMeta meta = boussole.getItemMeta();
		meta.setDisplayName("§7Sélecteur de jeux");
		boussole.setItemMeta(meta);
		

		
		PInv.clear();
		PInv.setItem(4, boussole);
		
	}
	
}
