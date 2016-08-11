package com.builtbroken.mc.api.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Map;

/**
 * Simple interface for checking connections to an object from a direction.
 * <p>
 * Created on 11/12/2014.
 *
 * @author Darkguardsman
 */
public interface ITileConnection
{
    /**
     * Simple direction based connection check.
     * Should be simple as side == side, and connection instanceof type
     *
     * @param connection - what is trying to connect, can be null
     * @param type       - what type of connector is the connection
     * @param from       - desired side to connect, may be null for internal connectons
     * @return true if the object can connect based on direction, and type
     */
    boolean canConnect(TileEntity connection, ConnectionType type, ForgeDirection from);

    /**
     * Map of connections by the sides they connected to. Is not limited to one
     * connection per side, however all connections must return a side. Even if
     * this side ends up being ForgeDirection.Unknown
     *
     * @return map of connections, or empty map in place of null/no connections
     */
    Map<TileEntity, ForgeDirection> getConnections();

}
