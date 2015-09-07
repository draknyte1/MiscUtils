package miscutil.render.item;

import miscutil.tile_entity.TileEntityBloodSteelChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderBloodSteelChest implements IItemRenderer {

	@SuppressWarnings("unused")
	private ModelChest chestModel;

	public ItemRenderBloodSteelChest() {
		chestModel = new ModelChest();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityBloodSteelChest(), 0.0D, 0.0D, 0.0D, 0.0F);
	}

}
