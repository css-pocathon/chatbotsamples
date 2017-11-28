import java.util.HashMap

class KClassifier {
    private val categories: MutableMap<String, KCategory>

    fun addCategory(item: String, category: KCategory) {

        categories.put(item, category)
    }

    fun getCategory(item: String): KCategory? {
        var myCategory = KCategory("notfound", KClassification.BAD)
        if (categories[item] != null) {
            var myFoundCategory= categories[item]
           return myFoundCategory
        }
        return myCategory
    }

    init {
        categories = HashMap()

    }
}
