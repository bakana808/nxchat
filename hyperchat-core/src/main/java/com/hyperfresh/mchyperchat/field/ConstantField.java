package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

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
	public FieldFlag[] getFlags()
	{
		return new FieldFlag[]{FieldFlag.CONSTANT};
	}

	@Override
	public String getFieldValue(User sender, String... args)
	{
		return value;
	}
}
