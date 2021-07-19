package beans;

public class ProductCategoriesBean {

    public ProductCategoriesBean() {

    }

    private String categoryId;
    private String productId;

    public String getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(String categoryId) {
	this.categoryId = categoryId;
    }

    public String getProductId() {
	return productId;
    }

    public void setProductId(String productId) {
	this.productId = productId;
    }

}
