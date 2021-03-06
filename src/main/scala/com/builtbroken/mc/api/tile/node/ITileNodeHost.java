package com.builtbroken.mc.api.tile.node;

import com.builtbroken.mc.api.abstraction.world.IPosWorld;
import com.builtbroken.mc.api.data.IPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

/**
 * Applied to objects that act as hosts for {@link ITileNode}
 * <p>
 * Keep in mind the host will not always be {@link TileEntity} as it could be an entity
 * or an a ghost object acting as a data provider.
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 3/31/2017.
 */
public interface ITileNodeHost extends IPosWorld
{
    /**
     * Gets the node that controls all logic
     * for this tile
     *
     * @return node, should never be null on
     * a valid tile.
     */
    ITileNode getTileNode();

    /**
     * Called back from TileNode to create a packet
     * with the data so it will be received correctly.
     *
     * @param data
     * @return
     */
    IPacket getPacketForData(Object... data);

    void sendPacketToClient(IPacket packet, double range);

    void sendPacketToServer(IPacket packet);

    /**
     * Checks to see if the host is
     * still a valid object in the world.
     * <p>
     * For Tiles this is {@link TileEntity#isInvalid()} check
     * For entities this is {@link Entity#isEntityAlive()}
     *
     * @return true if still valid
     */
    boolean isHostValid();

    /**
     * Call back to get the block
     * the host represents
     * <p>
     * This is provided so entities can
     * fake values
     *
     * @return
     */
    Block getHostBlock();

    /**
     * Call back to get the meta
     * value the host represents
     * <p>
     * This is provided so entities can
     * fake values
     *
     * @return
     */
    int getHostMeta();


    /**
     * @param meta - new meta state
     */
    void setMetaValue(int meta);

    /**
     * Check to see if the node can
     * access the world at this moment.
     * This is only used by entities
     * that are moving to prevent block
     * access.
     *
     * @return true if can access world
     */
    boolean canAccessWorld();

    /**
     * Checks if the host is receiving redstone signal
     *
     * @return true if there is redstone
     */
    boolean isRedstonePowered();

    /**
     * Gets the redstone level
     *
     * @return 0-15
     */
    int getRedstoneLevel();

}
