package futuristicLux.deepmining.common.Blocks;


import futuristicLux.deepmining.TileEntityTypesList;
import futuristicLux.deepmining.common.TileEntitys.DepositTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class DeepDeposit extends Block {

	public static final String NAME = "deep_deposit";
	public static final String UNLOCALIZED_NAME = "deep_deposit";
	public static final String  REGISTRY_NAME= "deep_deposit";
	
	private Block block;
	private int range; 
	private int period;
	
	public DeepDeposit(final Properties properties, Block block, int range,int period) {
		super(properties);
		this.block=block;
		this.range=range;
		this.period=period;
		
	}
	
	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		DepositTileEntity te = TileEntityTypesList.deepDeposit.create();
		te.setBlock(block);
		te.setRange(range);
		te.setPeriod(period);
		return te;
	}
	

}
