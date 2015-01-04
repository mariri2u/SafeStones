package mariri.safestones;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockSafeStone extends ItemBlock {
	
	public ItemBlockSafeStone(Block block){
		super(block);
		this.setHasSubtypes(true);
	}
	
    @Override
    public int getMetadata(int meta){
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack){
    	return this.block.getUnlocalizedName() + "." + itemStack.getItemDamage();
    }
    
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List tooltip, boolean par4) {
		tooltip.add(StatCollector.translateToLocal("safestones.tooltip"));
	}
}
