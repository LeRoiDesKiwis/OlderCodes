package fr.leroideskiwis.modoplugin.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.leroideskiwis.modoplugin.Main;
import fr.leroideskiwis.modoplugin.utils.SpigotUtils;

public class EventListener implements Listener {

	SpigotUtils utils = new SpigotUtils(Main.getInstance());
	private List<String> blacklistedCommands = new ArrayList<>();

	@SuppressWarnings("unused")
	private Map<Player, Long> cooldowns = new HashMap<>();

	@EventHandler(priority = EventPriority.HIGH)
	public void cooldownChat(AsyncPlayerChatEvent e) {

	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void ChatDifferents(AsyncPlayerChatEvent e){
		
		if(e.getMessage().startsWith("#")){
			
			e.setCancelled(true);
			
			e.getPlayer().performCommand("staff "+e.getMessage().replaceFirst("#", ""));
			
		}
		
	}
	
	public void onClickOnSign(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.SIGN) {

				Sign sign = (Sign) e.getClickedBlock().getState();

				if (sign.getLine(2).contains("infos")) {

					p.sendMessage("");
					p.sendMessage("");
					p.sendMessage("§6Informations :");
					p.sendMessage("");
					p.sendMessage(
							"§aBonjour et bienvenue, tu est sur un serveur priv§ ou je test mes plugins :D. Voila c'§tait une tres courte presentation ! Allez tchao !");
					p.sendMessage("");
					p.sendMessage("");
				}

			}
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {

		String cmd = e.getMessage();

		if (cmd.startsWith("/op") || cmd.startsWith("/kill")) {
			e.getPlayer().sendMessage("§cErreur : merci d'utiliser le /interact !");
			e.setCancelled(true);
			return;
		}

		if (cmd.startsWith("/kick") || cmd.startsWith("/ban") || cmd.startsWith("/deop")) {

			e.getPlayer().sendMessage("§cErreur : merci d'utiliser le /ss !");

			e.setCancelled(true);
			return;

		}

		if (Main.getInstance().mutes.contains(e.getPlayer()) || Main.getInstance().freezes.contains(e.getPlayer())) {
			if (cmd.startsWith("/rl") || cmd.startsWith("/reload") || cmd.startsWith("/ss")) {
				e.getPlayer().sendMessage("§cVous §tes sanctionner, vous ne pouvez pas reload le serveur !");
				e.setCancelled(true);
			}
		}

		blacklistedCommands.add("/say");
		blacklistedCommands.add("/me");
		blacklistedCommands.add("/tellraw");
		blacklistedCommands.add("/title");
		blacklistedCommands.add("/msg");
		blacklistedCommands.add("/r");
		blacklistedCommands.add("/bc");
		blacklistedCommands.add("/tell");
		blacklistedCommands.add("/broadcast");
		blacklistedCommands.add("/staff");

		if (Main.getInstance().mutes.contains(e.getPlayer())) {

			boolean mute = false;

			for (String bla : blacklistedCommands) {

				if (e.getMessage().startsWith(bla)) {
					mute = true;
				}

			}

			if (!mute)
				return;

			e.setCancelled(true);

			e.getPlayer().sendMessage("§cVous §tes r§duit au silence !");

		}
		
		for(Player p : Bukkit.getOnlinePlayers()){
			
			if(Main.getInstance().logs.contains(p)){
				
				p.sendMessage(Main.getInstance().prefixe+" §e"+e.getPlayer().getName()+" §7a execut§ la commande suivante : §e"+e.getMessage());
				
			}
			
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerSpeak(AsyncPlayerChatEvent e) {

		if (Main.getInstance().mutes.contains(e.getPlayer())) {

			e.setCancelled(true);

			e.getPlayer().sendMessage("§cVous §tes r§duit au silence !");

		}

	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

		if (Main.getInstance().freezes.contains(e.getPlayer())) {

			e.setCancelled(true);
			return;
		}

	}

	/**
	 * 
	 * Event pour le /ss
	 * 
	 * @param e
	 */

	@EventHandler
	public void onClickOnSsInv(InventoryClickEvent e) {

		if (e.getInventory().getName().contains("§cMod§ration")) {
			e.setCancelled(true);

			if(e.getCurrentItem() == null) return;
			
			Player p = (Player) e.getWhoClicked();

			String name = e.getInventory().getName().replaceAll("§cMod§ration : §e", "");

			Player target = Bukkit.getPlayer(name);

			if (target == null) {

				p.sendMessage("§cLe joueur cibl§ c'est d§connect§ !");
				return;

			} else {

				String sanction = null;

				for (String s : e.getCurrentItem().getItemMeta().getLore()) {

					if (s.startsWith("§7Sanction : ")) {

						sanction = s.replaceAll("§7Sanction : ", "");

					}

				}

				if (sanction == null) {

					p.sendMessage("§cSanction invalide !");
					return;

				}

				switch (sanction) {

				case "freeze":

					Main.getInstance().freezes.add(target);
					
					
					break;

				case "mute":
					Main.getInstance().mutes.add(target);
					break;

				case "kick":
					target.kickPlayer(
							"§cVous avez §t§ kick ! Raison : " + e.getCurrentItem().getItemMeta().getDisplayName());
					break;

				case "ban":
					target.kickPlayer("§cVous avez §t§ banni par " + p.getName() + " pour "
							+ e.getCurrentItem().getItemMeta().getDisplayName());
					Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),
							e.getCurrentItem().getItemMeta().getDisplayName(), null, p.getName());
					break;

				case "warn":

					break;

				case "deop":
					target.setOp(false);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
							"pex user " + target.getName() + " remove worldedit.*");

					break;
				case "ban (kick si op)":

					if (target.isOp())
						target.kickPlayer(
								"§cVous avez §t§ kick ! Raison : " + e.getCurrentItem().getItemMeta().getDisplayName());
					else {
						target.kickPlayer("§cVous avez §t§ banni par " + p.getName() + " pour "
								+ e.getCurrentItem().getItemMeta().getDisplayName());
						Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),
								e.getCurrentItem().getItemMeta().getDisplayName(), null, p.getName());
					}
					break;

				default:
					p.sendMessage("§cSanction invalide !");
					break;

				}

				p.sendMessage("§a" + target.getName() + " a bien §t§ " + sanction + " pour "
						+ e.getCurrentItem().getItemMeta().getDisplayName());

				utils.sendTitle(target, "§4Attention !", "§cVous avez §t§ " + sanction + " !");

				Bukkit.broadcastMessage((Main.getInstance().prefixe+" §e" + target.getName() + " §cs'est fait §e" + sanction
						+ " §cpar §e" + p.getName() + "§c pour §e"
						+ e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§c", "") + "§c !"));

				target.sendMessage("");

				target.sendMessage("§c----------------------------");
				target.sendMessage("");
				target.sendMessage("§cVous avez §t§ " + sanction + " ! Raison : "
						+ e.getCurrentItem().getItemMeta().getDisplayName());
				target.sendMessage("");
				target.sendMessage("§c----------------------------");

				target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
				target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1), false);
				
				if(sanction.equals("freeze")){
					
					target.sendMessage(Main.getInstance().prefixe+"§c Merci de venir sur le Discord : https://discord.gg/7CFs8XH. Deconnexion = ban def (Le tempban n'a pas §t§ encore dev)");
					
				}
				
				p.closeInventory();

			}

		}

	}

	@EventHandler
	public void onClickOnReportInv(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("§cReport")) {
			e.setCancelled(true);

			Player p = (Player) e.getWhoClicked();

			String name = e.getInventory().getName().replaceAll("§cReport : ", "");

			Player target = Bukkit.getPlayer(name);

			if (target == null) {

				p.sendMessage("§cLe joueur cibl§ c'est d§connect§ !");
				return;

			} else {

				p.sendMessage("§aLe joueur a bien §t§ signal§ !");

				for (Player player : Bukkit.getOnlinePlayers()) {

					if (player.isOp() || Main.getInstance().isModo(player)) {

						player.sendMessage("§cNouveau report de " + p.getName() + " : " + target.getName()
								+ " est suspect§ de " + e.getCurrentItem().getItemMeta().getDisplayName());

					}

				}

			}

			p.closeInventory();

		}

	}

	@EventHandler
	public void onDegatsReceive(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			if (!p.isOp())
				e.setCancelled(true);

		}

	}

	@EventHandler
	public void onClickOnInteractInv(InventoryClickEvent e) {
		if (e.getInventory().getName().contains("§7Interact")) {
			e.setCancelled(true);

			Player p = (Player) e.getWhoClicked();

			String name = e.getInventory().getName().replaceAll("§7Interact : ", "");

			boolean closeInv = true;
			Player target = Bukkit.getPlayer(name);

			if (target == null) {

				p.sendMessage("§cLe joueur cibl§ c'est d§connect§ !");
				return;

			} else {

				String what = e.getCurrentItem().getItemMeta().getDisplayName();
				
				switch (what.toLowerCase(Locale.ROOT)) {

				case "§ase tp a lui":
					p.teleport(target.getLocation());
					break;

				case "§aunmute":

					p.performCommand("unmute " + target.getName());

					break;

				case "§aunfreeze":
					p.performCommand("unfreeze " + target.getName());

					break;
				case "§cpunir":

					p.performCommand("ss " + target.getName());

					closeInv = false;

					break;

				case "§ctuer !":
					target.setHealth(0.0);
					break;

				case "§aop":
					if (target.isOp()) {
						p.sendMessage("§cErreur : il est d§j§ op !");

						break;
					}
					
					target.sendMessage("§aVous avez §t§ OP par " + p.getName() + " !");
					Bukkit.broadcastMessage(
					Main.getInstance().prefixe + " §e" + target.getName() + " §aa §t§ OP par §e" + p.getName());
					target.setOp(true);
					
					
					Firework firework = (Firework)target.getWorld().spawnEntity(target.getLocation(), EntityType.FIREWORK);
					
					FireworkMeta metaF = firework.getFireworkMeta();
					metaF.addEffect(FireworkEffect.builder().withColor(Color.RED, Color.YELLOW, Color.BLUE).build());
					
					utils.spawnFireWork(metaF, utils.randomNumber(15, 40), 10, target);
					
					break;

				}

			}

			if (closeInv)
				p.closeInventory();

		}

	}

	@EventHandler
	public void PlayerInteractEntity(PlayerInteractEntityEvent e) {

		if (e.getPlayer().isOp()) {

			Player target = null;

			if (e.getRightClicked() instanceof Player)
				target = (Player) e.getRightClicked();

			if (target == null)
				return;

			if (e.getPlayer().getItemInHand().equals(Main.getInstance().InteractItem)) {

				e.getPlayer().performCommand("interact " + target.getName());

			} else if (e.getPlayer().getItemInHand().equals(Main.getInstance().SanctionItem)) {

				e.getPlayer().performCommand("ss " + target.getName());

			}
		}

	}

}
