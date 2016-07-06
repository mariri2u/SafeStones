package mariri.safestones.item;

import mariri.safestones.SafeStones;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagicBucket extends ItemBucket {

	public ItemMagicBucket(){
		super(Blocks.AIR);

		this.setCreativeTab(SafeStones.creativeTab);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("magicBucket");
		GameRegistry.register(this, new ResourceLocation(SafeStones.MODID, "magicBucket"));

        GameRegistry.addShapelessRecipe(new ItemStack(this), Items.BUCKET, Items.REDSTONE, Items.DYE);
        GameRegistry.addShapelessRecipe(new ItemStack(Items.BUCKET), this);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer player, EnumHand hand){
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}

    @SideOnly(Side.CLIENT)
    public void registerTextures(){
    	ResourceLocation json = new ResourceLocation("bucket");
        //モデルJSONファイルのファイル名を登録。1IDで1つだけなら、登録名はGameRegistryでの登録名と同じものにする。
        //ItemStackのmetadataで種類を分けて描画させたい場合。登録名を予め登録する。
        ModelBakery.registerItemVariants(this, json);
        //1IDで複数モデルを登録するなら、上のメソッドで登録した登録名を指定する。
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(json, "inventory"));
    }
}
