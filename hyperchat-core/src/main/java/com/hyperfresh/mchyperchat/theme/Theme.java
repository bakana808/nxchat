package com.hyperfresh.mchyperchat.theme;

public interface Theme
{
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
