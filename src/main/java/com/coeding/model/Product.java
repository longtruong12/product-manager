package com.coeding.model;

public class Product {
	protected int id;
    protected String name;
    protected String description;
    protected Float price;
    protected String category;

    public Product() {}

    public Product(String name, String description, Float price, String category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}
    
    public Product(int id, String name, String description, Float price, String category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public int getId() {
        return id;
    }
    public void setId(int id) {
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
