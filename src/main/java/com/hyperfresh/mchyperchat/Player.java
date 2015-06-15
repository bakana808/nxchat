package com.hyperfresh.mchyperchat;

import java.util.UUID;

/**
 * @author octopod
 */
public interface Player extends User
{
	/**
	 * Gets the UUID associated with a player.
	 * @return
	 */
	public UUID getUUID();
}
