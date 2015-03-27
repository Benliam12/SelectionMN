package ca.benliam12.benedit.selection;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import ca.benliam12.benedit.session.Session;

public class Selection
{
	private World world;
	private Vector position1;
	private Vector position2;

	public Selection(Session session)
	{
		
	}
	
	/**
	 * Method to set Pos1
	 *
	 * @param loc Location of the new Pos1
	 * @return Success/Error Message
	 */
	public Vector setPosition1(Location location)
	{
		if(location.getWorld() != world)
		{
			this.position2 = null;
		}
		
		this.position1 = new Vector(location.getX(), location.getY(), location.getZ());
		
		return this.position1;
	}
	
	/**
	 * Method to set Pos2
	 *
	 * @param loc Location of the new Pos2
	 * @return Success/Error Message
	 */
	public Vector setPosition2(Location location)
	{
		if(location.getWorld() != world)
		{
			this.position2 = null;
		}
		
		this.position2 = new Vector(location.getX(), location.getY(), location.getZ());
		
		return this.position2;
	}
	
	public Vector getLocation()
	{
		double x = Math.max(this.position1.getX(), this.position2.getX());
		double y = Math.max(this.position1.getY(), this.position2.getY());
		double z = Math.max(this.position1.getZ(), this.position2.getZ());
		
		return new Vector(x, y, z);
	}
	
	public int getWidth()
	{
		int width = (int) this.position1.getX() - (int) this.position2.getX();
		return Math.abs(width);
	}
	
	public int getLength()
	{
		int length = (int) this.position1.getZ() - (int) this.position2.getZ();
		return Math.abs(length);
	}
	
	public int getHeight()
	{
		int height = (int) this.position1.getY() - (int) this.position2.getY();
		return Math.abs(height);
	}
	
	/**
	 * Method to get Position 1
	 * 
	 * @return Location of post1
	 */
	public Vector getPosition1()
	{
		return this.position1;
	}
	
	/**
	 * Method to get Position 2
	 * 
	 * @return Location of post1
	 */
	public Vector getPosition2()
	{
		return this.position2;
	}
	
	/**
	 * Method to get all block in a specific region
	 * 
	 * @param loc1 The first point
	 * @param loc2 The second point
	 * @return ArrayList containting each blocks
	 */
	public ArrayList<ca.benliam12.benedit.blocks.Block> getBlocks(){
		ArrayList<ca.benliam12.benedit.blocks.Block> blocks = new ArrayList<ca.benliam12.benedit.blocks.Block>();
		
		int maxX = this.getWidth();
		int maxY = this.getHeight();
		int maxZ = this.getLength();
		
		for(int x = 0; x < maxX; x++)
		{
			for(int y = 0; y < maxY; y++)
			{
				for(int z = 0; z < maxZ; z++)
				{
					ca.benliam12.benedit.blocks.Block block = new ca.benliam12.benedit.blocks.Block(this.world.getBlockAt(x, y, z).getType());
					
					blocks.add(block);
				}
			}
		}
		
		return blocks;
	}
	
	/**
	 * Method to set a region with a specific type of block
	 * 
	 * @param type BlockType
	 */
	@SuppressWarnings("deprecation")
	public Selection set(Material material, byte data)
	{
		if(this.position1 != null && this.position2 != null)
		{
			int maxX = this.getWidth();
			int maxY = this.getHeight();
			int maxZ = this.getLength();
			
			for(int x = 0; x < maxX; x++)
			{
				for(int y = 0; y < maxY; y++)
				{
					for(int z = 0; z < maxZ; z++)
					{
						Block block = this.world.getBlockAt(x, y, z);
						
						block.setType(material);
						block.setData(data);
					}
				}
			}
		}
		
		return this;
	}
	
	/*public Clipboard copy(Player player)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		
		Clipboard clipboard = new Clipboard(sessionManager.getSession(player.getUniqueId()).getSelection());
		sessionManager.getSession(player.getUniqueId()).setClipboard(clipboard);
		
		return clipboard;
	}*/
}
