package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

import java.util.Collections;
import java.util.Set;

/**
 * @author octopod
 */
public interface Field
{
	/**
	 * Returns a list of names of this field.
	 *
	 * @return an array of strings
	 */
	public String[] getFieldNames();

	/**
	 * Returns true if this field contains a certain FieldFlag.
	 * @return
	 */
	public default Set<FieldFlag> getFlags()
	{
		return Collections.emptySet();
	}

	/**
	 * Returns an array of expected argument sizes. Defaults to 0.
	 *
	 * @return an array of expected argument sizes
	 */
	public default int[] numArgs()
	{
		return new int[0];
	}

	/**
	 * Gets the value of the field. Optional arguments if the field takes any.
	 *
	 * @param args optional arguments
	 * @return the current value
	 */
	public String getFieldValue(User user, String... args);
}
