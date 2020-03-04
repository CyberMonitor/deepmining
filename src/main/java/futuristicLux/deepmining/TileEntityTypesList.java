package futuristicLux.deepmining;

import futuristicLux.deepmining.common.TileEntitys.DepositTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("deepmining")
public class TileEntityTypesList {

	@ObjectHolder("deepmining:deep_deposit")
	public static TileEntityType<DepositTileEntity> deepDeposit = null;
}
