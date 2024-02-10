package bank;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		SavingsAccount sa = new SavingsAccount();
		Scanner scanner = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");

		byte choice = 0;
		boolean loop = true;

		while (loop) {
			System.out.println("---------------------------------------------");
			System.out.println("               PROCESS LIST");
			System.out.println("---------------------------------------------");
			System.out.println(
					" 1.Create Account.\n 2.Deposite.\n 3.Withdraw.\n 4.Check Account Balance.\n 5.Account Details.\n 6.Exit.");
			System.out.println("---------------------------------------------");

			try {
				System.out.print("Enter Your Choice Here : ");
				choice = scanner.nextByte();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.print("Please Enter Proper Input....!");
				scanner.nextLine();
				choice = 7;
			}

			switch (choice) {
			case 1:
				CreateAccount.toCreateAccount();
				break;

			case 2:
				sa.deposite();
				break;

			case 3:
				sa.withdraw();
				break;

			case 4:
				sa.checkBalance();
				break;

			case 5:
				sa.display();
				break;

			case 6: {
				loop = false;
				System.out.println("\nExit Successfully....");
				System.out.println("---------------------------------------------");

				scanner.close();
				CreateAccount.conn.close();

				break;
			}
			default: {
				System.out.println("\nInvalid Choice....!");
			}
			}
		}
	}
}
