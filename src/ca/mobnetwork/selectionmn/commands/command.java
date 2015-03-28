package ca.mobnetwork.selectionmn.commands;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.mobnetwork.selectionmn.session.Session;
import ca.mobnetwork.selectionmn.session.SessionManager;
/**
 * Class controling main commands
 * 
 * @author Benliam12
 * @version 1.0
 */
public class command implements CommandExecutor {

	@Override
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return false;
		}
		
		Player player = (Player) sender;
		
		if(label.equalsIgnoreCase("set"))
		{	
			if(args.length == 1)
			{
				try
				{
					String[] param = args[0].split(":");
					
					if(Material.getMaterial(param[0].toUpperCase()).isBlock())
					{
						Material material = Material.getMaterial(param[0].toUpperCase());
						
						SessionManager sessionManager = SessionManager.getInstance();
						
						if(param.length == 1)
						{	
							Session session = sessionManager.getSession(player.getUniqueId());
							
							session.getSelection().set(material, (byte) 0);
						}
						else if(param.length == 2)
						{
							int data;
							
							try
							{
								data = Integer.parseInt(param[1]);
							}
							catch(Exception exception)
							{
								player.sendMessage(ChatColor.RED + "Wrong usage: data must be between 0 and 15");
								
								return false;
							}
							
							if(data > 15 || data < 0)
							{
								player.sendMessage(ChatColor.RED + "Wrong usage: data must be between 0 and 15");
								
								return false;
							}
							else
							{
								Session session = sessionManager.getSession(player.getUniqueId());
								
								session.getSelection().set(material, (byte) data);
							}
						}
						else
						{
							player.sendMessage(ChatColor.RED + "Wrong usage: /set block:data");
							
							return false;
						}
					}
					
					return false;
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
					player.sendMessage(ChatColor.RED + "Wrong usage: /set block:data");
					
					return false;
				}
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Wrong usage: /set block:data");
			}
		}

		return false;
	}

}
