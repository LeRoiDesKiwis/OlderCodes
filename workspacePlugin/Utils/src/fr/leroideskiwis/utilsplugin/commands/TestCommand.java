package fr.leroideskiwis.utilsplugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;

import fr.leroideskiwis.utilsplugin.Main;
import net.minecraft.server.v1_9_R1.EntityArmorStand;
import net.minecraft.server.v1_9_R1.EntityPlayer;
import net.minecraft.server.v1_9_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_9_R1.PacketPlayOutResourcePackSend;
import net.minecraft.server.v1_9_R1.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_9_R1.WorldServer;


public class TestCommand implements CommandExecutor {

	private Main main;

	public TestCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		
		if(main.SenderIsPlayer(s)){
			
			Player p = (Player)s;
			Location loc = p.getLocation();
			
			WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
			
			EntityArmorStand eas = new EntityArmorStand(world);
			
			eas.setPosition(loc.getX(), loc.getY(), loc.getZ());
			eas.setCustomName("cc");
			eas.setCustomNameVisible(true);
			PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(eas);
			
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
			p.sendMessage("Hello world !");
			
			new BukkitRunnable(){
			
			public void run(){
				PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(eas.getId());
				((CraftPlayer)p).getHandle().playerConnection.sendPacket(destroy);
			
			}
			
			}.runTaskLater(main, args.length == 0 ? 40 : Integer.parseInt(args[0]));
			
			
			
			
		}
		
		return false;
	}

}