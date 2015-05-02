package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.HyperChat;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The entry class for the Bukkit plugin.
 */
public class BukkitPlugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        HyperChat.onEnable();
    }

    @Override
    public void onDisable()
    {
        HyperChat.onDisable();
    }
}
