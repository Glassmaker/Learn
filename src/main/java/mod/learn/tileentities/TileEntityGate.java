package mod.learn.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGate extends TileEntity {

    private ForgeDirection facingDirection = ForgeDirection.UP;

    public void setFacing(ForgeDirection facing) {
        this.facingDirection = facing;
    }

    public ForgeDirection getFacing() {
        return this.facingDirection;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();
        if (nbt != null)
            readFromNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.facingDirection = ForgeDirection.getOrientation(nbt.getByte("facing"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setByte("facing", (byte) facingDirection.ordinal());
    }

}
