package com.hyperfresh.mchyperchat;

import com.hyperfresh.mchyperchat.field.FieldManager;
import org.junit.Assert;
import org.junit.Test;

public class StringProcessTest
{
    FieldManager fields = HyperChat.getFields();

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
