import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ClassifierTest {
    Classifier testee = new Classifier();

    @Test
    public void addCategory() throws Exception {

        Category categoryGood = new Category("theGood", Classification.GOOD );
        Category categoryBad = new Category("theBad", Classification.BAD );
        Category categoryUgly = new Category("theUgly", Classification.UGLY );
        testee.addCategory("one", categoryGood);
        testee.addCategory("two", categoryBad);
        testee.addCategory("three", categoryUgly);

        assertEquals("theGood",testee.getCategory("one").getCategoryName());
        assertEquals("notfound",testee.getCategory("ohne").getCategoryName());
    }

    @Test
    public void getCategory() throws Exception {
    }

}