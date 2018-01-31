package com.builtbroken.mc.imp.transform.vector;

import com.builtbroken.jlib.data.vector.IPos3D;
import com.builtbroken.mc.api.IWorldPosition;
import com.builtbroken.mc.data.Direction;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.ILocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class Location extends AbstractLocation<Location> implements IWorldPosition, IPos3D, Comparable<IWorldPosition>
{
    public static final Location NULL = new Location(null, 0, 0, 0);

    public Location(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    public Location(NBTTagCompound nbt)
    {
        this(DimensionManager.getWorld(nbt.getInteger("dimension")), nbt.getDouble("x"), nbt.getDouble("y"), nbt.getDouble("z"));
    }

    public Location(ByteBuf data)
    {
        this(DimensionManager.getWorld(data.readInt()), data.readDouble(), data.readDouble(), data.readDouble());
    }

    public Location(Entity entity)
    {
        this(entity.world, entity.posX, entity.posY, entity.posZ);
    }

    public Location(TileEntity tile)
    {
        super(tile);
    }

    public Location(IWorldPosition vec)
    {
        this(vec.oldWorld(), vec.x(), vec.y(), vec.z());
    }

    public Location(ILocation loc)
    {
        this(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
    }

    public Location(World world, IPos3D vector)
    {
        this(world, vector.x(), vector.y(), vector.z());
    }

    public Location(World world, BlockPos pos)
    {
        this(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public Location(World world, Vec3d vec)
    {
        this(world, vec.x, vec.y, vec.z);
    }

    public Location(World world, RayTraceResult target)
    {
        this(world, target.hitVec);
    }

    @Override
    public Location newPos(double x, double y, double z)
    {
        return new Location(world, x, y, z);
    }

    public void playSound(SoundEvent soundIn, SoundCategory category, float volume, float pitch, boolean distanceDelay)
    {
        oldWorld().playSound(x(), y(), z(), soundIn, category, volume, pitch, distanceDelay);
    }

    public boolean isSideSolid(Direction side)
    {
        IBlockState state = getBlockState();
        if (state != null && state.getBlock() != Blocks.AIR)
        {
            BlockFaceShape shape = state.getBlockFaceShape(world, toBlockPos(), side.getEnumFacing());
            return shape != null && shape == BlockFaceShape.SOLID;
        }
        return false;
    }

    @Override
    public boolean equals(Object o)
    {
        return o instanceof IWorldPosition && this.world == ((IWorldPosition) o).oldWorld() && ((IWorldPosition) o).x() == x() && ((IWorldPosition) o).y() == y() && ((IWorldPosition) o).z() == z();
    }

    @Override
    public int compareTo(IWorldPosition that)
    {
        if (oldWorld().provider.getDimension() < that.oldWorld().provider.getDimension() || x() < that.x() || y() < that.y() || z() < that.z())
        {
            return -1;
        }

        if (oldWorld().provider.getDimension() > that.oldWorld().provider.getDimension() || x() > that.x() || y() > that.y() || z() > that.z())
        {
            return 1;
        }

        return 0;
    }
}