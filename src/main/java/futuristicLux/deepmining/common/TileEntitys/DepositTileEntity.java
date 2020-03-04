package futuristicLux.deepmining.common.TileEntitys;



import java.util.Iterator;
import java.util.Random;

import futuristicLux.deepmining.TileEntityTypesList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class DepositTileEntity extends TileEntity implements ITickableTileEntity{
	
	private int period = 40;
	private int tick;
	private Block block;
	private int range;
	
	public DepositTileEntity(final TileEntityType<DepositTileEntity> type) {
		super(type);
		tick=0;
	}
	
	public DepositTileEntity() {
		super(TileEntityTypesList.deepDeposit);
		tick=0;
	}
	
	public void read(CompoundNBT compound) {
	      super.read(compound);
	      this.tick = compound.getInt("tick");
	   }

	   public CompoundNBT write(CompoundNBT compound) {
	      super.write(compound);
	      compound.putInt("tick", this.tick);
	      return compound;
	   }

	
	@Override
	public void tick() {
		if(!world.isRemote()) {
			tick++;
			if(tick>=period) {
				tick=0;
				int x,y,z;
				Random r = new Random();
				x = r.nextInt(range*2 + 1) - range;
				y = r.nextInt(range*2 + 1) - range;
				z = r.nextInt(range*2 + 1) - range;
				if(!(x == 0 && y == 0 && z == 0)) {
					BlockPos placepos = pos.add(x, y, z);
					world.setBlockState(placepos, block.getDefaultState());
				}
			}
		}
		
	}

	public void setBlock(Block block) {
		
		this.block=block;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public void setPeriod(int period) {
		this.period=period;
	}
	

}
