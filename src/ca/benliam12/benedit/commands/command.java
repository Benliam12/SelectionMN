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
							player.sendMessage(((Selection) SessionManager.getInstance().getSession(player.getName()).getInfo("selection")).set(mat,(byte)0));
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
				} 
				catch(Exception ex)
				{
					if(args[0].contains(":"))
					{
						String[] nargs = args[0].split(":");
						try
						{
							int datavalue = Integer.parseInt(nargs[1]);
							int blockID = Integer.parseInt(nargs[0]);
							if(datavalue < 16 && datavalue >= 0)
							{
								if(Material.getMaterial(blockID) != null)
								{
									if(Material.getMaterial(blockID).isBlock())
									{
										Material mat = Material.getMaterial(blockID);
										player.sendMessage(((Selection) SessionManager.getInstance().getSession(player.getName()).getInfo("selection")).set(mat,(byte)datavalue));
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
							}
							else 
							{
								player.sendMessage(ChatColor.RED + "Datatag must be between 0-15");
							}
						} 
						catch (Exception exep)
						{
							player.sendMessage(ChatColor.RED + "Invalid block ID / Datavalue");
						}
					}
					else
					{
						player.sendMessage(ChatColor.RED + "Usage : /bset <BlockID>");
					}
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
