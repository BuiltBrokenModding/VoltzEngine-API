package com.builtbroken.mc.api.abstraction.imp;

/**
 * Applied to objects that act as a wrapper
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 8/15/2017.
 */
public interface IWrapper<O extends Object>
{
    /**
     * Internal call to unwrap the tile data
     * back to a Block instance
     *
     * @return block
     */
    O unwrap();
}
