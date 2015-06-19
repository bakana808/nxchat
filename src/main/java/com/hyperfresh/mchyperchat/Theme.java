package com.hyperfresh.mchyperchat;

public interface Theme
{
	/**
	 * Returns the ID of this theme.
	 *
	 * @return
	 */
	public String getID();

	/**
	 * Returns the name of this theme.
	 *
	 * @return
	 */
	public String getName();

	/**
	 * Returns the author of this theme.
	 *
	 * @return
	 */
	public String getAuthor();

	public String getElement(ThemeElement element);
}
