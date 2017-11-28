import java.util.HashMap;
import java.util.Map;

public class Classifier {
    private Map<String, Category> categories;

    public void addCategory(String item, Category category) {

        categories.put(item, category);
    }

    public Category getCategory(String item){
        Category myCategory = new Category("notfound", Classification.BAD);
        if (categories.get(item)!=null){
            myCategory = categories.get(item);
        }
        return myCategory;
    }

    public Classifier() {
        categories = new HashMap<String, Category>();

    }
}
