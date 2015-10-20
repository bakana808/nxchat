package com.hyperfresh.nxchat;

import java.util.UUID;

/**
 * @author octopod
 */
public interface Player extends User
{
	/**
	 * Gets the UUID associated with a player.
	 *
	 * @return
	 */
	public UUID getUUID();
}
