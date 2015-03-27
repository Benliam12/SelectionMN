package ca.benliam12.benedit.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ca.benliam12.benedit.session.Session;
import ca.benliam12.benedit.session.SessionManager;
/**
 * 
 * @author Benliam12
 * @version 1.0
 */
public class PlayerListener implements Listener
{
	/**
	 * Event when player join the game
	 */
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent event)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		
		sessionManager.addSession(event.getPlayer().getUniqueId());
	}

	/**
	 * Event when player leave the game
	 */
	@EventHandler
	public void PlayerQuit(PlayerQuitEvent event)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		
		sessionManager.removeSession(event.getPlayer().getUniqueId());
	}
	/**
	 * Event when player click for selection
	 */
	@EventHandler
	public void PlayerSelection(PlayerInteractEvent event)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		
		Player player = event.getPlayer();
		Session session = sessionManager.getSession(player.getUniqueId());

		if(player.getItemInHand().getType() == Material.STICK)
		{
			if(event.getAction() == Action.LEFT_CLICK_BLOCK)
			{
				Block block = event.getClickedBlock();
				int x = (int) block.getLocation().getX();
				int y = (int) block.getLocation().getY();
				int z = (int) block.getLocation().getZ();
				
				session.getSelection().setPosition1(block.getLocation());
				player.sendMessage(ChatColor.GREEN + "position 1: " + ChatColor.GRAY + x + ChatColor.GREEN + ", " + ChatColor.GRAY + y + ChatColor.GREEN + ", " + ChatColor.GRAY + z);
				event.setCancelled(true);
			}
			else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				Block block = event.getClickedBlock();
				int x = (int) block.getLocation().getX();
				int y = (int) block.getLocation().getY();
				int z = (int) block.getLocation().getZ();
				
				session.getSelection().setPosition2(block.getLocation());
				player.sendMessage(ChatColor.GREEN + "position 2: " + ChatColor.GRAY + x + ChatColor.GREEN + ", " + ChatColor.GRAY + y + ChatColor.GREEN + ", " + ChatColor.GRAY + z);
				event.setCancelled(true);
			}
		}
	}
}
