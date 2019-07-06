package fr.leroideskiwis.utilsplugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.leroideskiwis.utilsplugin.commands.ChangeWorldCommand;
import fr.leroideskiwis.utilsplugin.commands.CommandBroadcast;
import fr.leroideskiwis.utilsplugin.commands.CommandClearEnd;
import fr.leroideskiwis.utilsplugin.commands.CommandEnchant;
import fr.leroideskiwis.utilsplugin.commands.CommandGetKey;
import fr.leroideskiwis.utilsplugin.commands.CommandGod;
import fr.leroideskiwis.utilsplugin.commands.CommandeInvEnder;
import fr.leroideskiwis.utilsplugin.commands.InvseeCommand;
import fr.leroideskiwis.utilsplugin.commands.TestCommand;
import fr.leroideskiwis.utilsplugin.listeners.ChangeEvents;
import fr.leroideskiwis.utilsplugin.listeners.EventListener;
import fr.leroideskiwis.utilsplugin.listeners.PlaceBreakEvents;
import fr.leroideskiwis.utilsplugin.tasks.VerificateTask;

public class Main extends JavaPlugin {

	public String nether = "server-spawn_nether";
	public String world = "server-spawn";
	private static Main instance;
	//private SpigotUtils u;
	private ItemStack keyNether;
	public final String ownerN = getConfig().getString("nether.owner");
	public ItemStack perm;
	public ItemStack gamemode;

		
	
	/*
	 * int = la puissance du god (0 = tout sauf le vide et 1 = tout sans exception)
	 * 
	 */
	
	private Map<Player, Integer> gods = new HashMap<>();
	
	
	public Map<Player, Integer> getGods() {
		return gods;
	}
	
	public boolean randomChance(int chance){
		
		if(new Random().nextInt(chance) == 2) return true;
		else return false;
		
	}

	public static Main getInstance(){
		return instance;
	}
	
	public void registerCommand(String name, CommandExecutor command){
		getCommand(name).setExecutor(command);
	}
	
	public boolean SenderIsPlayer(CommandSender sender){
		if(sender instanceof Player) return true;
		
		sender.sendMessage("§cVous devez etre un joueur pour executer cette commande !");
		
		return false;
	}
	
	/*public SpigotUtils getUtils(){
		return u;
	}*/
	
	@Override
	public void onEnable(){
		
		instance = this;
		//u = new SpigotUtils(this);
		
		initItems();


		
		saveDefaultConfig();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EventListener(this), this);
		pm.registerEvents(new PlaceBreakEvents(this), this);
		pm.registerEvents(new ChangeEvents(), this);
		registerCommand("god", new CommandGod(this));
		registerCommand("invsee", new InvseeCommand(this));
		registerCommand("invender", new CommandeInvEnder(this));
		registerCommand("butcher", new CommandButcher(this));
		registerCommand("test", new TestCommand(this));
		registerCommand("broadcast", new CommandBroadcast());
		registerCommand("getkeyn", new CommandGetKey());
		registerCommand("clearEnd", new CommandClearEnd());
		registerCommand("changeWorld", new ChangeWorldCommand());
		registerCommand("enchant", new CommandEnchant());
		
		new VerificateTask().runTaskTimer(this, 0, 1);
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
			
			for(Player p : Bukkit.getOnlinePlayers()){
				
				if(p.isOp()){
					if(p.getGameMode() == GameMode.SURVIVAL) p.setGameMode(GameMode.CREATIVE);
				}
				
			}
			
		}, 5);
		
		
	}

	private void initItems() {
		
		/*
		 * nether
		 * 
		 */
		
		keyNether = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta keyNetherM = keyNether.getItemMeta();
		keyNetherM.setDisplayName("§cClé pour le nether");
		keyNetherM.setLore(Arrays.asList("", "§6Copyright", "§e"+ownerN, ""));
		keyNetherM.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		keyNetherM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		keyNether.setItemMeta(keyNetherM);
		
		perm = new ItemStack(Material.PAPER);
		ItemMeta permMeta = perm.getItemMeta();
		permMeta.setDisplayName("§cPermissions");
		permMeta.setLore(Arrays.asList("", "§6Permissions: ", "","§e- Détruire des blocs", "§e- placer des blocs", "§e- interagir avec des blocs", "§e- utiliser worldedit","§e- taper le mobs",""));
		perm.setItemMeta(permMeta);
		
		gamemode = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta gameMeta = gamemode.getItemMeta();
		gameMeta.setDisplayName("§cGamemode 1");
		gameMeta.setLore(Arrays.asList("", "§6Permet de rester en", "§6gamemode 1", ""));
		
		gamemode.setItemMeta(gameMeta);
		

		
	}

	public ItemStack getKeyN() {
		// TODO Auto-generated method stub
		return keyNether;
	}
	
}
