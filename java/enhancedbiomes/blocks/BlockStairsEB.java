package enhancedbiomes.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BlockStairsEB extends BlockStairs 
{
	public BlockStairsEB(Block par2Block, int par3, boolean isWooden) 
	{
		super(par2Block, par3);
		this.useNeighborBrightness = true;
		//if(isWooden) Blocks.fire.setFireInfo(this, 5, 20);
	}

    @SideOnly(Side.CLIENT)

    @Override
     /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if(par1 != Item.getItemFromBlock(EnhancedBiomesBlocks.stairsSEB[2]))
        {
        	par3List.add(new ItemStack(par1, 1, 0));
        }
    }
}
