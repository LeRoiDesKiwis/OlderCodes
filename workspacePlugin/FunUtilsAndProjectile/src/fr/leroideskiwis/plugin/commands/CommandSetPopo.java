package fr.leroideskiwis.plugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.SplashPotion;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class CommandSetPopo implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		if(s instanceof Player){
			
			Player p = (Player)s;
			
			
			
			if(p.getItemInHand().getType() == Material.POTION){
				
				
				
				Potion potion = Potion.fromItemStack(p.getItemInHand());
				
				potion.setLevel(Integer.parseInt(args[0]));
				potion.setType(PotionType.getByEffect(PotionEffectType.getByName(args[1])));
				
				p.getInventory().addItem(potion.toItemStack(1));
				p.getInventory().remove(p.getItemInHand());
				
			} else if(p.getItemInHand().getType() == Material.SPLASH_POTION){
				
				Potion potion = new Potion(PotionType.getByEffect(PotionEffectType.getByName(args[1])));
				
				potion.setLevel(Integer.parseInt(args[0]));
				potion.setSplash(true);
				p.getInventory().addItem(potion.toItemStack(1));
				p.getInventory().remove(p.getItemInHand());
				
				
				
			}
			
		}
		return false;
	}

}
