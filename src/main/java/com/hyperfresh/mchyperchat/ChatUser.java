package com.hyperfresh.mchyperchat;

public abstract class ChatUser
{
    /**
     * Gets the name of this user.
     * @return the name of the user.
     */
    public abstract String getName();

    /**
     * Gets the last message that this user has sent.
     * @return the last message this user has sent
     */
    public abstract String getLastMessage();
}
