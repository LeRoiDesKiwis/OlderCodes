package fr.leroideskiwis.levitation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.leroideskiwis.levitation.Main;

public class CommandLev implements CommandExecutor {

	private Main main = Main.getInstance();
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.isPlayer(s)){
			
			Player p = (Player)s;
			
			if(p.hasPotionEffect(PotionEffectType.LEVITATION)) {
				p.removePotionEffect(PotionEffectType.LEVITATION);
				return true;
			}
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 60*20, args.length == 1 ? Integer.parseInt(args[0]) : 7));
			
		}
		
		return false;
	}

}
