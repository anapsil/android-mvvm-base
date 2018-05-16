package net.anapsil.mvvmbase.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by ana.silva on 19/01/18.
 */

class StringUtilsTest {

    @Test
    @Throws(Exception::class)
    fun generateMd5Test() {
        val hash = "03804dbc76253f2cbc4541134cb9fc37"
        val value = "ILoveAndroid"

        assertEquals(hash, StringUtils.generateMd5(value))
    }

    @Test
    @Throws(Exception::class)
    fun capitalizeFirstLetterTest() {

        assertEquals("Android", StringUtils.capitalizeFirstLetter("android"))

        assertEquals("", StringUtils.capitalizeFirstLetter(""))

        assertEquals(null, StringUtils.capitalizeFirstLetter(null))
    }
}
