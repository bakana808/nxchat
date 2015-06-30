package com.hyperfresh.mchyperchat;

import java.util.Collection;
import java.util.UUID;

public interface Plugin
{
	/**
	 * Gets a Player by their UUID.
	 * <p/>
	 * If this method returns null, then the Player is not online.
	 *
	 * @param id
	 * @return a Player or null if that player is not online
	 */
	public Player getPlayer(UUID id);

	/**
	 * Gets all Users on the server, which includes both all online players and the console.
	 *
	 * @return a set of Players on the server
	 */
	public Collection<User> getUsers();
}
