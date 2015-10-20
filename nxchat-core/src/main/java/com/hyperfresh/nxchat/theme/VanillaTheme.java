package com.hyperfresh.mchyperchat.theme;

import com.hyperfresh.mchyperchat.HyperChat;

/**
 * The default theme in case no other themes were loaded.
 */
public class VanillaTheme extends Theme
{
	protected VanillaTheme(HyperChat hyperChat)
	{
		super(hyperChat);
	}

	@Override
	public String getHeaderFormat()
	{
		return null;
	}

	@Override
	public String getBodyFormat()
	{
		return "<${name}> ${message}";
	}

	@Override
	public String getFooterFormat()
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "Vanilla Classic";
	}

	@Override
	public String getAuthor()
	{
		return "Mojang";
	}
}
