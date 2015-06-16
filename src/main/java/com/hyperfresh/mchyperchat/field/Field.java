package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

/**
 * Created by Octopod on 4/16/2015.
 */
public abstract class Field
{
	// an empty integer array
	private static int[] EMPTY = new int[0];

	/**
	 * Returns a list of names of this field.
	 *
	 * @return an array of strings
	 */
	public abstract String[] getFieldNames();

	/**
	 * Returns true if this field is "dynamic."
	 * Static field will be processed into messages once when it is registered.
	 * Dynamic field will be processed into messages every time a player chats.
	 * Keep field static if the values are constant (never changes).
	 *
	 * @return true if this field is dynamic
	 */
	public boolean isDynamic()
	{
		return true;
	}

	/**
	 * Returns an array of expected argument sizes. Defaults to 0.
	 *
	 * @return an array of expected argument sizes
	 */
	public int[] numArgs()
	{
		return EMPTY;
	}

	/**
	 * Gets the value of the field. Optional arguments if the field takes any.
	 *
	 * @param args optional arguments
	 * @return the current value
	 */
	public abstract String getFieldValue(User sender, String... args);
}