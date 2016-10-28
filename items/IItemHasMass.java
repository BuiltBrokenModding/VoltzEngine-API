package com.builtbroken.mc.api.items;

import net.minecraft.item.ItemStack;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2016.
 */
public interface IItemHasMass
{
    double getMass(ItemStack stack);
}
