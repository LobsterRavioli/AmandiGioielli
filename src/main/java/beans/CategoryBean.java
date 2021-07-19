package beans;

public class CategoryBean {
	
    public CategoryBean() {
	// TODO Auto-generated constructor stub
    }

    public CategoryBean(int id, String name, String description) {
	this.name = name;
	this.id = id;
	this.description = description;
    }

    public CategoryBean(int id, String name) {
	this.name = name;
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
	return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
	CategoryBean category = (CategoryBean) obj;
	return this.name.equals(category.getName());
    }

    private String name;
    private int id;
    private String description;

}
