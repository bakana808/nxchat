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

	/**
	 * Returns the format that is used in a chat header.
	 *
	 * @return
	 */
	public String getChatHeaderFormat();

	/**
	 * Returns the format that is used in a chat message.
	 *
	 * @return
	 */
	public String getChatMessageFormat();

	/**
	 * Returns the format that is used in a chat footer.
	 *
	 * @return
	 */
	public String getChatFooterFormat();
}
