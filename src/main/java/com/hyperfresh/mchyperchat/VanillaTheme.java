package com.hyperfresh.mchyperchat;

public class VanillaTheme implements Theme
{
	@Override
	public String getID()
	{
		return "vanilla";
	}

	@Override
	public String getName()
	{
		return "Vanilla Classic";
	}

	@Override
	public String getAuthor()
	{
		return "octopod";
	}

	@Override
	public String getChatHeaderFormat()
	{
		return "<${name}>";
	}

	@Override
	public String getChatMessageFormat()
	{
		return "    ${message}";
	}

	@Override
	public String getChatFooterFormat()
	{
		return null;
	}
}
