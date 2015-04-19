package com.hyperfresh.mchyperchat;

/**
 * Created by Octopod on 4/16/2015.
 */
public abstract class ChatField
{
    /**
     * Returns true if this field is "dynamic."
     * Static fields will be processed into messages once when it is registered.
     * Dynamic fields will be processed into messages every time a player chats.
     * Keep fields static if the values are constant (never changes).
     * @return
     */
    public boolean isDynamic() { return true; }

    private static int[] EMPTY = new int[0];

    /**
     * Returns an array of expected argument sizes. Defaults to 0.
     * @return an array of expected argument sizes
     */
    public int[] numArgs() { return EMPTY; }

    /**
     * Gets the value of the field. Optional arguments if the field takes any.
     * @param args optional arguments
     * @return the current value
     */
    public abstract String getValue(ChatMessager sender, String... args);
}