package com.hyperfresh.mchyperchat;

import org.junit.Assert;
import org.junit.Test;

public class StringProcessTest
{
    ChatFieldList fieldList = ChatFieldList.getInstance();

    @Test
    public void FieldProcessTest()
    {
        fieldList.clear();
        fieldList.addField(new String[]{"c"}, "RED");

        String str;

        str = HyperChat.INSTANCE.processString("${c}${C}");
        Assert.assertEquals("REDRED", str);
    }
}
