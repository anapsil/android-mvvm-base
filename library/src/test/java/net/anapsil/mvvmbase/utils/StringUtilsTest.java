package net.anapsil.mvvmbase.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ana.silva on 19/01/18.
 */

public class StringUtilsTest {

    @Test
    public void generateMd5Test() throws Exception {
        String hash = "03804dbc76253f2cbc4541134cb9fc37";
        String value = "ILoveAndroid";

        assertEquals(hash, StringUtils.generateMd5(value));
    }

    @Test
    public void capitalizeFirstLetterTest() throws Exception {
        String value = "android";
        String expectedValue = "Android";

        assertEquals(expectedValue, StringUtils.capitalizeFirstLetter(value));

        value = "";
        expectedValue = "";

        assertEquals(expectedValue, StringUtils.capitalizeFirstLetter(value));

        assertEquals(null, StringUtils.capitalizeFirstLetter(null));
    }
}
