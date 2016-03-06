package com.builtbroken.mc.api.event;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Object that can tell a block or entity how it was triggered. Used as
 * an internal event system for explosive triggering.
 * Created on 11/18/2014.
 *
 * @author Darkguardsman
 */
public abstract class TriggerCause
{
    /** Name of the trigger cause */
    public final String triggerName;
    /** Amount to change the size of the effect */
    public float effectScaleChange = 1;

    public TriggerCause(String name)
    {
        this.triggerName = name;
    }

    /** Side based Trigger */
    public static class TriggerCauseSide extends TriggerCause
    {
        public final ForgeDirection triggeredSide;

        public TriggerCauseSide(String name, ForgeDirection side)
        {
            super(name);
            triggeredSide = side;
        }
    }

    /** Triggered by an entity */
    public static class TriggerCauseEntity extends TriggerCause
    {
        public final Entity source;

        public TriggerCauseEntity(Entity source)
        {
            this("entity", source);
        }

        public TriggerCauseEntity(String name, Entity source)
        {
            super(name);
            this.source = source;
        }
    }

    public static class TriggerCauseExplosion extends TriggerCause
    {
        public final Explosion source;

        public TriggerCauseExplosion(Explosion source)
        {
            super("explosion");
            this.source = source;
        }
    }

    /** Trigger by fire */
    public static class TriggerCauseFire extends TriggerCauseSide
    {
        public TriggerCauseFire(ForgeDirection side)
        {
            super("fire", side);
        }
    }

    /** Trigger by restone signal */
    public static class TriggerCauseRedstone extends TriggerCauseSide
    {
        /** 0-15 how strong the signal of the restone was */
        public final int strength;

        public TriggerCauseRedstone(ForgeDirection side, int strength)
        {
            super("redstone", side);
            this.strength = strength;
        }
    }
}
