public class Category {
    private String categoryName;
    private Classification classifaction;

    public Category(String categoryName, Classification classifaction) {
        this.categoryName = categoryName;
        this.classifaction = classifaction;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
