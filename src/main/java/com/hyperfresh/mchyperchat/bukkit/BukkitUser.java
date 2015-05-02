package com.hyperfresh.mchyperchat.bukkit;

import com.hyperfresh.mchyperchat.User;
import org.bukkit.entity.Player;

public class BukkitUser extends User
{
    private final Player handle;

	public BukkitUser(Player player)
	{
		this.handle = player;
	}

    public String getName()
    {
        return handle.getName();
    }
}
