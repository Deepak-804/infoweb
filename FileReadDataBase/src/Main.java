import java.util.Scanner;
import Opration.Oparetion;
public class Main {
	public static void main(String[] args) {
		Oparetion op=new Oparetion("Employee.csv");
		op.startProcess();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("1-Display \n2-Add \n3-Update \n4-delete \n5-commit"
					+ "\n6-sort data\n7-exit");
			System.out.println("enter option do want to perform ");
			int opt=sc.nextInt();
			switch(opt) {
			case 1:{System.out.println("*******Dispaly*******");
			op.display();
			break;}
			case 2:{System.out.println("*** Welcome to add new Data ******");
			System.out.println("enter id,name,address");
			String data=sc.next();
			op.add(data);
			break;
			}
			case 3:{System.out.println("*** Welcome to Update ******");
			System.out.println("enter id : ");
			int data=sc.nextInt();
			op.update(data);
			break;
			}
			case 4:{System.out.println("*** Welcome to Delete ******");
			System.out.println("enter id : ");
			int data=sc.nextInt();
			op.delete(data);
			break;
			}
			case 5:{System.out.println("*** Welcome to commit ******");
			op.commit();
			break;
			}
			}
		}
	}
}
