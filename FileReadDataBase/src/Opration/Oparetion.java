package Opration;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import Employee.Employee;
import EmployeeDoa.EmpDoa;
import Util.Util;
public class Oparetion implements EmpDoa{
	Scanner sc=new Scanner(System.in);
	String fileName;
	Vector<Employee> emp;
	public Oparetion(String file) {
		this.fileName=file;
		emp=new Vector<>();
	}
	public void startProcess() {
		try {
			this.loadRecord(this.fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadRecord(String fileName) throws IOException {
		String str=Util.readfile(fileName);
		String[] dataArray=str.split("\n");
		for(int i=1; i<dataArray.length; i++) {
			//System.out.println(dataArray[i]);
			emp.add((Employee)this.getInstance(dataArray[i]));
		} 
	}
	private Employee getInstance(String str) {
		return new Employee(Integer.valueOf(str.split(",")[0]),str.split(",")[1],str.split(",")[2]);
	}
	@Override
	public void display() {
		for(Employee a:emp) {
			System.out.println(a);
		}

	}
	@Override
	public void add(String data) {
		System.out.println("dat "+data);
		emp.add(this.getInstance(data));
		System.out.println("data insert successfully ");
	}
	@Override
	public void update(int id) {
		boolean flag=false;
		for(Employee e:emp) {
			int empid=e.getEmpid();
			if(empid==id) {
				flag=true;
				System.out.println("enter Name :");
				String name=sc.next();
				e.setName(name);
				System.out.println("enter Address: ");
				String address=sc.next();
				e.setAddress(address);
			}
		}
	}
	@Override
	public void delete(int id) {
		for(int i=0; i<emp.size(); i++) {
			int empid=emp.get(i).getEmpid();
			if(empid==id) {
				emp.remove(i);
			}
		}
	}
	@Override
	public void commit() {
		System.out.println("Enter option : \n1-commit for file \n2-commit in Database");
		int opt=sc.nextInt();
		if(opt==1) {
		StringBuilder  sb=new StringBuilder("Empid,Name,Address\n");
		for(Employee emp:emp) {
			if(emp==null) {
				continue;
			}
			sb.append(emp.getEmpid()+",");
			sb.append(emp.getName()+",");
			sb.append(emp.getAddress()+"\n");
		}
		try {
			Util.writefile(fileName,sb.toString());
			System.out.println("Successfully commited in file ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("---------:Submit into database :----------");
			DatabaseConnection dcommit=new DatabaseConnection();
			try {
				dcommit.commit(emp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
