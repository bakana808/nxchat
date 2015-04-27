package com.hyperfresh.mchyperchat;

import org.junit.Assert;
import org.junit.Test;

public class StringProcessTest
{
    FieldList fields = HyperChat.getFields();

    @Test
    public void FieldProcessTest()
    {
        fields.clear();
        fields.addField(new String[]{"c"}, "RED");

        String str;

        str = HyperChat.processString("${c}${C}");
        Assert.assertEquals("REDRED", str);
    }
}
