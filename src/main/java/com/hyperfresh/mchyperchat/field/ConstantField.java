package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

import java.util.EnumSet;
import java.util.Set;

/**
 * Represents a field that returns a constant value.
 *
 * Such fields should be processed when a theme is
 * added into the ThemeManager.
 *
 * @author octopod
 */
class ConstantField implements Field
{
	private String value;
	private String[] names;

	public ConstantField(String value, String... names)
	{
		this.value = value;
		this.names = names;
	}

	@Override
	public String[] getFieldNames()
	{
		return names;
	}

	@Override
	public Set<FieldFlag> getFlags()
	{
		return EnumSet.of(FieldFlag.CONSTANT);
	}

	@Override
	public String getFieldValue(User sender, String... args)
	{
		return value;
	}
}
