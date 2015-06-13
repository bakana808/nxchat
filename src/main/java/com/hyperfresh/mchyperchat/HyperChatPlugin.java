package com.hyperfresh.mchyperchat;

import java.util.UUID;

public interface HyperChatPlugin
{
	public void onHyperChatEnable();

	public void onHyperChatDisable();

	public User getPlayer(UUID id);

	public User getConsole();
}
