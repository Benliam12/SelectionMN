package ca.benliam12.benedit.selection;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Selection 
{
	/**
	 * Location for position 1
	 */
	private Location location1;
	
	/**
	 * Location for position 2
	 */
	private Location location2;
	
	/**
	 * Constructor
	 */
	public Selection()
	{
		
	}
	
	/**
	 * Method to set Pos1
	 *
	 * @param loc Location of the new Pos1
	 * @return Success/Error Message
	 */
	public String setPos1(Location loc)
	{
		if(this.location2 != null)
		{
			if(this.location2.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName()))
			{
				this.location1 = loc;
			}
			else
			{
				this.location2 = null;
				this.location1 = loc;
			}
		}
		else
		{
		this.location1 = loc;
		}
		return ChatColor.GREEN + "Position 1 set on block : " + (int)loc.getX() + ", " + (int)loc.getY() + ", " + (int)loc.getZ();
	}
	
	/**
	 * Method to set Pos2
	 *
	 * @param loc Location of the new Pos2
	 * @return Success/Error Message
	 */
	public String setPos2(Location loc)
	{
		if(this.location1 != null)
		{
			if(this.location1.getWorld().getName().equalsIgnoreCase(loc.getWorld().getName()))
			{
				this.location2 = loc;
			}
			else
			{
				this.location1 = null;
				this.location2 = loc;
			}
		}
		else
		{
			this.location2 = loc;
		}
		return ChatColor.GREEN + "Position 2 set on block : " + (int)loc.getX() + ", " + (int)loc.getY() + ", " + (int)loc.getZ();
	}
	
	/**
	 * Method to get Position 1
	 * 
	 * @return Location of post1
	 */
	public Location getPos1()
	{
		return this.location1;
	}
	
	/**
	 * Method to get Position 2
	 * 
	 * @return Location of post1
	 */
	public Location getPos2()
	{
		return this.location2;
	}
	
	/**
	 * Method to get all block in a specific region
	 * 
	 * @param loc1 The first point
	 * @param loc2 The second point
	 * @return ArrayList containting each blocks
	 */
	public ArrayList<Block> getAllBlocks(Location loc1, Location loc2){
		double x = Math.max(loc1.getX(), loc2.getX());
		double y = Math.max(loc1.getY(), loc2.getY());
		double z = Math.max(loc1.getZ(), loc2.getZ());
		World w = Bukkit.getWorld(loc1.getWorld().getName());
		ArrayList<Block> blocks = new ArrayList<>();
		
		while(x >= Math.min(loc1.getX(), loc2.getX()))
		{
			y = Math.max(loc1.getY(), loc2.getY());
			
			while(y >= Math.min(loc1.getY(), loc2.getY()))
			{
				z = Math.max(loc1.getZ(), loc2.getZ());
				
				while(z >= Math.min(loc1.getZ(), loc2.getZ()))
				{
					blocks.add(w.getBlockAt((int)x, (int)y, (int)z));
					z--;
				}
				y--;
			}
			x--;
		}
		return blocks;
	}
	
	/**
	 * Method to set a region with a specific type of block
	 * 
	 * @param type BlockType
	 */
	@SuppressWarnings("deprecation")
	public String set(Material mat, byte datavalue)
	{
		if(this.location1 != null && this.location2 != null)
		{
			int nbBlocks = 0;
			World w = Bukkit.getWorld(this.location1.getWorld().getName());
			for(Block block : this.getAllBlocks(this.location1, this.location2))
			{
				if(!((w.getBlockAt(block.getLocation()).getType()) == mat) || !(w.getBlockAt(block.getLocation()).getData() == datavalue))
				{
					nbBlocks++;
				}
				block.setType(mat);
				block.setData(datavalue);
			}
			return ChatColor.GREEN + Integer.toString(nbBlocks) + " blocks were changed";
		}
		else 
		{
			return ChatColor.RED + "You must define 2 points";
		}
	}
}
