package ca.benliam12.benedit.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ca.benliam12.benedit.selection.Selection;
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
	public void PlayerJoin(PlayerJoinEvent e)
	{
		SessionManager.getInstance().addSession(e.getPlayer().getName());
		SessionManager.getInstance().getSession(e.getPlayer().getName()).setInfo("selection", new Selection());
	}

	/**
	 * Event when player leave the game
	 */
	@EventHandler
	public void PlayerQuit(PlayerQuitEvent e)
	{
		SessionManager.getInstance().addSession(e.getPlayer().getName());
	}
	/**
	 * Event when player click for selection
	 */
	@EventHandler
	public void PlayerSelection(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		Session session = SessionManager.getInstance().getSession(player.getName());
		// Making pos1
		if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.STICK)
		{
			player.sendMessage(((Selection) session.getInfo("selection")).setPos1(e.getClickedBlock().getLocation()));
			e.setCancelled(true);
		}
		// Making pos2
		else if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() == Material.STICK)
		{
			player.sendMessage(((Selection) session.getInfo("selection")).setPos2(e.getClickedBlock().getLocation()));
			e.setCancelled(true);
		}
	}
}
