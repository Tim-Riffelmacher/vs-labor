package hska.iwi.eShopMaster.model.database.dataobjects;


public class MicroserviceProduct {

	private int id;
	
	private String name;

	private double price;

	private int category;

	private String details;

	public MicroserviceProduct() {
	}

	public MicroserviceProduct(String name, double price, int category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public MicroserviceProduct(String name, double price, int category, String details) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.details = details;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
