package InteractiveCalculus.Main;

public class Forum_table {
	private int id = -1;
	private String name = "";
	private String price = "";
	
	public Forum_table(String name) {
		super();
		this.name = name;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Forum_table(int id, String name, String price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Forum_table(String name, String price) {
		super();
		this.name = name;
		this.price = price;
	}
	public Forum_table() {
		super();
	}

}
