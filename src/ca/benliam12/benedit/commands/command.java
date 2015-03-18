package ca.benliam12.benedit.commands;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.benliam12.benedit.selection.Selection;
import ca.benliam12.benedit.session.SessionManager;
/**
 * Class controling main commands
 * 
 * @author Benliam12
 * @version 1.0
 */
public class command implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return true;
		}
		
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("bset"))
		{
			if(args.length == 1)
			{
				try
				{
					int matID = Integer.parseInt(args[0]);
					if(Material.getMaterial(matID) != null)
					{
						if(Material.getMaterial(matID).isBlock())
						{
							Material mat = Material.getMaterial(matID);
							player.sendMessage(((Selection) SessionManager.getInstance().getSession(player.getName()).getInfo("selection")).set(mat));
						} 
						else 
						{
							player.sendMessage(ChatColor.RED + "This is not a valid block !");
						}
					} 
					else 
					{
						player.sendMessage(ChatColor.RED + "This is not a valid block !");
					}
				} catch(Exception ex)
				{
					player.sendMessage(ChatColor.RED + "Usage : /bset <BlockID>");
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Usage : /bset <BlockID>");
			}
		}
		else if(label.equalsIgnoreCase("bcut"))
		{
			
		}
		return false;
	}

}
