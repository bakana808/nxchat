package com.hyperfresh.mchyperchat.field;

import com.hyperfresh.mchyperchat.User;

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
	 * Returns true if this field can potentially return different values every time it is used.
	 *
	 * <p>
	 *     Static fields will be processed into a theme's formats when it is registered,
	 *     whereas dynamic fields will be processed into messages every time a player chats.
	 * </p>
	 *
	 * <p>
	 *     If your Field returns the same value every time it is used, it is recommended that
	 *     you return {@code false} for this method or consider using {@code ConstantField} instead.
	 * </p>
	 *
	 * @return true if this field is dynamic
	 */
	public default boolean isDynamic()
	{
		return true;
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
