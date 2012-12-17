package me.shock.rust;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class RainListener implements Listener
{

	
public Main plugin;
	
	public RainListener(Main instance)
	{
		this.plugin = instance;
	}
	
	
	/*
	 * Do damage when in water / rain
	 */
	
	@EventHandler
	public void inWater(PlayerMoveEvent event)
	{
		Material mat = event.getPlayer().getLocation().getBlock().getType();
		if(mat == Material.STATIONARY_WATER || mat == Material.WATER)
		{
			Player player = event.getPlayer();
			ItemStack[] armor = player.getInventory().getArmorContents();
			
			// Damage the players armor.
			
			player.sendMessage(ChatColor.RED + "Get out of the water!");
			
		}
	}
	
	
	
	/*
	 * Put player in a HashMap so they are immune for
	 * a few seconds while fighting mobs.
	 */
	
	@EventHandler 
	public void onHit(EntityDamageByEntityEvent event)
	{
		// Nothing yet.
	}
	
	@EventHandler
	public void onSwing(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		
		if((action == Action.LEFT_CLICK_AIR) && player.getItemInHand() != null)
		{
			ItemStack it = player.getItemInHand();
			Material inHand = it.getType();
			if(
					inHand.equals(Material.DIAMOND_AXE) 
					|| inHand.equals(Material.DIAMOND_HOE)
					|| inHand.equals(Material.DIAMOND_PICKAXE)
					|| inHand.equals(Material.DIAMOND_SPADE)
					|| inHand.equals(Material.DIAMOND_SWORD)
					|| inHand.equals(Material.IRON_AXE)
					|| inHand.equals(Material.IRON_HOE)
					|| inHand.equals(Material.IRON_PICKAXE)
					|| inHand.equals(Material.IRON_SPADE)
					|| inHand.equals(Material.IRON_SWORD))
			{
				String damageSwingMessage = plugin.getConfig().getString("Message.Swing");
				short damageSwing = (short) plugin.getConfig().getInt("Damage.Swing");
				short dur = it.getDurability();
				final short newDamage = (short) (dur + damageSwing);
				it.setDurability(newDamage);
				player.sendMessage(ChatColor.RED + "" + damageSwingMessage);
			}
		}
	}
	
}
