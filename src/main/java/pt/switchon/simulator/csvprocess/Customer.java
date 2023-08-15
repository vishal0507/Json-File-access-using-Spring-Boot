package pt.switchon.simulator.csvprocess;

public class Customer {

	private String id;
	private String name;
	private String age;
	private String address;

	public Customer(String id, String name, String age, String address) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
