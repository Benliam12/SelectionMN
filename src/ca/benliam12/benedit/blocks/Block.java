package ca.benliam12.benedit.blocks;

import org.bukkit.Material;

public class Block
{
	private Material type;
	private int data;
	
	public Block(Material type)
	{
		this.type = type;
	}
	
	public Block(Material type, int data)
	{
		this.type = type;
		this.data = data;
	}
	
	public Material getTypel()
	{
		return this.type;
	}
	
	public int getData()
	{
		return this.data;
	}
	
	public void setType(Material type)
	{
		this.type = type;
	}
	
	public void setData(int data)
	{
		this.data = data;
	}
}
