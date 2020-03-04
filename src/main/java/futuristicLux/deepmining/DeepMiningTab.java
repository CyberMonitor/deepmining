package futuristicLux.deepmining;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DeepMiningTab extends ItemGroup{

	public DeepMiningTab(String label) {
		super(label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(BlockList.deepDepositDiamond);
	}

}
