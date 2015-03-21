package ca.benliam12.benedit.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Class to Copy/Paste/Cut
 * 
 * @author Benliam12
 * @version 1.0
 */
public class Clipboard
{
	private HashMap<Integer,String> clipboard = new HashMap<>();
	
	/**
	 * Contructor 
	 */
	public Clipboard(){}
	
	/**
	 * 
	 * @param playerlocation
	 * @param blocks
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String copy(Vector playerLocation, ArrayList<Block> blocks)
	{
		this.clipboard.clear();
		int i = 0;
		for(Block b : blocks)
		{
			int x = b.getX() + playerLocation.getBlockX() * -1;
			int y = b.getY() + playerLocation.getBlockY() * -1;
			int z = b.getZ() + playerLocation.getBlockZ() * -1;
			this.clipboard.put(i, b.getTypeId() + ":" + b.getData() + ":" + x + ":" + y + ":" +z);
			i++;
		}
		return null;
		
	}
	/**
	 * Paste method
	 * 	
	 * @param playerLocation 
	 */
	@SuppressWarnings("deprecation")
	public String paste(Vector playerLocation,World w)
	{
		if(this.clipboard.size() != 0)
		{
			for(Entry<Integer, String> clipboard : this.clipboard.entrySet())
			{
				String[] data = clipboard.getValue().split(":");
				if(data.length == 5)
				{
					try
					{
						int x = Integer.parseInt(data[2]) + playerLocation.getBlockX();
						int y = Integer.parseInt(data[3]) + playerLocation.getBlockY();
						int z = Integer.parseInt(data[4]) + playerLocation.getBlockZ();
						Material mat = Material.getMaterial(Integer.parseInt(data[0]));
						byte datavalue = (byte) Integer.parseInt(data[1]);
						if(mat != null && datavalue < 16 && datavalue >= 0)
						{
							w.getBlockAt(x, y, z).setType(mat);
							w.getBlockAt(x, y, z).setData(datavalue);
						}
					}
					catch (Exception ex)
					{
						
					}
				} 
			}
			return ChatColor.GREEN + "Pasted !";
		} 
		return ChatColor.RED + "Please copy something !";
	}
	
}
