package Employee;
public class Employee {
	int empid;
	String name;
	String address;
	public Employee(int id,String name,String add) {
		this.empid=id;
		this.name=name;
		this.address=add;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * public String toString() { return "  "+empid+" "+name+" "+"  "+address; }
	 */
	@Override
	public String toString() {
		return "empid=" + empid + ", name=" + name + ", address=" + address;
	}

}
