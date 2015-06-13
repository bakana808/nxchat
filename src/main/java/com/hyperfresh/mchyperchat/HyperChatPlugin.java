package com.hyperfresh.mchyperchat;

import java.util.UUID;

public interface HyperChatPlugin
{
	public User getPlayer(UUID id);

	public User getConsole();
}
