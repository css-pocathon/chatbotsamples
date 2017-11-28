import org.junit.Test

import org.junit.Assert.assertEquals

class KClassifierTest {
    internal var testee = KClassifier()

    @Test
    @Throws(Exception::class)
    fun addCategory() {

        val categoryGood = KCategory("theGood", KClassification.GOOD)
        val categoryBad = KCategory("theBad", KClassification.BAD)
        val categoryUgly = KCategory("theUgly", KClassification.UGLY)
        testee.addCategory("one", categoryGood)
        testee.addCategory("two", categoryBad)
        testee.addCategory("three", categoryUgly)

        assertEquals("theGood", testee.getCategory("one")!!.categoryName)
        assertEquals("notfound", testee.getCategory("ohne")!!.categoryName)
    }

    @Test
    @Throws(Exception::class)
    fun getCategory() {
    }

}