package mod.learn.tileentities;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityGate extends TileEntity {

    private static final int MAX_SIZE = 16;
    private ForgeDirection facingDirection = ForgeDirection.UP;

    private int length = 0; // x-axis
    private int depth = 0; // z-axis
    private int height = 0; // y-axis

    private int[] cornerCoords = new int[3];

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

    public boolean isStructreVaild() {

        int checkX = this.xCoord + facingDirection.getOpposite().offsetX;
        int checkY = this.yCoord + facingDirection.getOpposite().offsetY;
        int checkZ = this.zCoord + facingDirection.getOpposite().offsetZ;


        // find inside's lower north-west corner in 16x16x16.

        boolean needToCheckX = true;
        boolean needToCheckY = true;
        boolean needToCheckZ = true;

        boolean foundCorner = false;
        int cornerX = checkX;
        int cornerY = checkY;
        int cornerZ = checkZ;

        for (int i = 0; i < TileEntityGate.MAX_SIZE - 2; i++) {

            if (cornerX < -30000000 && cornerZ < -30000000 && cornerX >= 30000000
                    && cornerZ >= 30000000) {
                // Out of world bound
                return false;
            }

            if (cornerY < 0 || cornerY >= 256) {
                // out of world bound
                return false;
            }

            if (this.worldObj.getBlock(cornerX, cornerY, cornerZ) != Blocks.air) {
                // non-air block
                return false;
            }

            if (needToCheckX)
                needToCheckX = worldObj.getBlock(cornerX - 1, cornerY, cornerZ) == Blocks.air;

            if (needToCheckY)
                needToCheckY = worldObj.getBlock(cornerX, cornerY - 1, cornerZ) == Blocks.air;

            if (needToCheckZ)
                needToCheckZ = worldObj.getBlock(cornerX, cornerY, cornerZ - 1) == Blocks.air;

            if (!needToCheckX && !needToCheckY && !needToCheckZ) {
                foundCorner = true;
                break;
            }


            if (needToCheckX)
                cornerX--;

            if (needToCheckY)
                cornerY--;

            if (needToCheckZ)
                cornerZ--;

        }

        if (!foundCorner)

            return false;

        // length measurement
        int measurement = 0;
        for (measurement = 0; measurement <= TileEntityGate.MAX_SIZE - 2; measurement++) {
            boolean isAir =
                    worldObj.getBlock(cornerX + measurement, cornerY, cornerZ) == Blocks.air;

            if (!isAir)
                break; // reached south wall

            if ((measurement == TileEntityGate.MAX_SIZE - 2) && isAir) {
                // Length is larger than max
                return false;
            }
        }

        length = measurement;

        // depth measurement
        for (measurement = 0; measurement <= TileEntityGate.MAX_SIZE - 2; measurement++) {
            boolean isAir =
                    worldObj.getBlock(cornerX, cornerY, cornerZ + measurement) == Blocks.air;

            if (!isAir)
                break; // reached east wall

            if ((measurement == TileEntityGate.MAX_SIZE - 2) && isAir) {
                // Depth is larger than max
                return false;
            }
        }

        depth = measurement;

        // height measurement
        for (measurement = 0; measurement <= TileEntityGate.MAX_SIZE - 2; measurement++) {
            boolean isAir =
                    worldObj.getBlock(cornerX, cornerY + measurement, cornerZ) == Blocks.air;

            if (!isAir)
                break; // reached top wall

            if ((measurement == TileEntityGate.MAX_SIZE - 2) && isAir) {
                // Height is larger than max
                return false;
            }
        }

        height = measurement;


        // inner space check
        for (int y = 0; y < height; y++) {
            for (int z = 0; z < depth; z++) {
                for (int x = 0; x < length; x++) {
                    boolean isAir =
                            worldObj.getBlock(cornerX + x, cornerY + y, cornerZ + z) == Blocks.air;

                    if (!isAir) {
                        // Failed internal space check
                        return false;
                    }

                }
            }
        }

        // north, and south walls check
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                boolean isAir =
                        worldObj.getBlock(cornerX + j, cornerY + i, cornerZ - 1) == Blocks.air;

                if (isAir) {
                    // Invalid North wall
                    return false;
                }

                isAir = worldObj.getBlock(cornerX + j, cornerY + i, cornerZ + depth) == Blocks.air;

                if (isAir) {
                    // Invalid South wall
                    return false;
                }
            }
        }

        // west, and east walls check
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < depth; j++) {
                boolean isAir =
                        worldObj.getBlock(cornerX - 1, cornerY + i, cornerZ + j) == Blocks.air;

                if (isAir) {
                    // Invalid West wall
                    return false;
                }

                isAir = worldObj.getBlock(cornerX + length, cornerY + i, cornerZ + j) == Blocks.air;

                if (isAir) {
                    // Invalid East wall
                    return false;
                }
            }
        }

        // top, and bottom walls check
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < depth; j++) {
                boolean isAir =
                        worldObj.getBlock(cornerX + i, cornerY - 1, cornerZ + j) == Blocks.air;

                if (isAir) {
                    // Invalid Bottom wall
                    return false;
                }

                isAir = worldObj.getBlock(cornerX + i, cornerY + height, cornerZ + j) == Blocks.air;

                if (isAir) {
                    // Invalid top wall
                    return false;
                }
            }
        }

        // Valid structure
        return true;
    }
}
