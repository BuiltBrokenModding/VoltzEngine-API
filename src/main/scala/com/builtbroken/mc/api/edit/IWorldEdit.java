package com.builtbroken.mc.api.edit;

import com.builtbroken.jlib.data.vector.IPos3D;
import com.builtbroken.mc.api.IWorldPosition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Location with data on changing the block
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 11/13/2015.
 */
public interface IWorldEdit extends IWorldPosition, IPos3D
{
    /**
     * Has the block changed, in respect to the new data.
     *
     * @return true if it has changed and a block set should be performed.
     */
    boolean hasChanged();

    /**
     * Called to place the block edits.
     *
     * @return result of the placement.
     */
    BlockEditResult place();

    /**
     * Gets the bounding box of the block being placed or edited. Used
     * to prevent mobs from getting stuck in the edit.
     *
     * @return new axis aligned bb shifted by location
     */
    AxisAlignedBB getBounds();

    /**
     * Gets the block being placed.
     *
     * @return block or null for no change
     */
    IBlockState getNewBlockState();

    /**
     * Gets the block at the location.
     *
     * @return block.
     */
    IBlockState getBlockState();

    /**
     * Gets the current tile entity
     *
     * @return tile entity or null if there is non
     */
    TileEntity getTileEntity();

    /**
     * Called to log the previous block before the edit
     */
    IWorldEdit logPrevBlock();

    /**
     * Called to change the edit result
     *
     * @param state
     */
    IWorldEdit set(IBlockState state);
}
