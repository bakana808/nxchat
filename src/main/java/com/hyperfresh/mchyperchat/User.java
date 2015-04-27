package com.hyperfresh.mchyperchat;

public interface User
{
    /**
     * Gets the name of this user.
     * @return the name of the user.
     */
    public String getName();

    /**
     * Gets the last message that this user has sent.
     * @return the last message this user has sent
     */
    public String getLastMessage();
}
