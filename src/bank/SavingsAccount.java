package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SavingsAccount {

	static Connection conn = GetConnection.getConnection();

	Scanner sc = new Scanner(System.in);

	public void display() throws SQLException {

		boolean con = true;

		PreparedStatement pst = conn.prepareStatement("select * from account where accountno = ? and cname = ?");

		System.out.print("\nEnter Your AccountNumber : ");
		long accno = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter Your Name : ");
		String name = sc.nextLine();

		pst.setLong(1, accno);
		pst.setString(2, name);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {

			System.out.println("---------------------------------------------");
			System.out.println("     Your Account Details is Given Below");
			System.out.println("---------------------------------------------");
			System.out.println("Account Number     : " + rs.getLong(2));
			System.out.println("AccountHolder Name : Mr." + rs.getString(1));
			System.out.println("Account Balance    : " + rs.getDouble(3));
			System.out.println();
			
			con = false;
		}

		if (con)
			System.out.println("\nAccount Not Found....! \nInvalid AccountNumber or Name...!\n");
	}

	public void deposite() throws SQLException {

		boolean con = true;

		PreparedStatement pst = conn.prepareStatement("select accountno,balance from account where accountno = ?");

		System.out.print("\nEnter Your AccountNumber : ");
		long accno = sc.nextLong();
		sc.nextLine();

		pst.setLong(1, accno);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {

			System.out.print("\nEnter Deposite Amount : ");
			double depositeAmount = sc.nextDouble();
			System.out.println();

			double accountBalance = rs.getDouble(2);

			if (depositeAmount >= 0) {
				accountBalance += depositeAmount;
				System.out.print("Amount Credited Successfully....\n");
				System.out.println("Credited Amount = " + depositeAmount);
				System.out.print("Your Current Account Balance = " + accountBalance);

				pst = conn.prepareStatement("update account set balance = ? where accountno = ?");
				pst.setDouble(1, accountBalance);
				pst.setLong(2, accno);
				pst.execute();

				System.out.println("\n");
			} 
			else
				System.out.println("Invalid Amount....\n");

			con = false;
		}

		if (con)
			System.out.println("\nInvalid Account Number....!\n");
	}

	public void withdraw() throws SQLException {

		boolean con = true;

		PreparedStatement pst = conn
				.prepareStatement("select cname,accountno,balance from account where accountno = ? and cname = ?");

		System.out.print("\nEnter Your AccountNumber : ");
		long accno = sc.nextLong();
		sc.nextLine();
		System.out.print("Enter Your Name : ");
		String name = sc.nextLine();

		pst.setLong(1, accno);
		pst.setString(2, name);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {

			System.out.print("\nEnter Withdraw Amount : ");
			double withdrawAmount = sc.nextDouble();
			System.out.println();

			Double accountBalance = rs.getDouble(3);

			if ((accountBalance >= withdrawAmount) && (withdrawAmount >= 0)) {
				accountBalance -= withdrawAmount;
				System.out.println("Amount Withdrawed Successfully....\n");
				System.out.println("Withdraw Amount = " + withdrawAmount);
				System.out.println("Your Current Account Balance = " + accountBalance);
				System.out.println();

				pst = conn.prepareStatement("update account set balance = ? where accountno = ?");
				pst.setDouble(1, accountBalance);
				pst.setLong(2, accno);
				pst.execute();

			} 
			else
				System.out.println("Insufficient Balance....\n");

			con = false;
		}

		if (con)
			System.out.println("\nInvalid Account Number (or) Name....!\n");
	}

	public void checkBalance() throws SQLException {

		boolean con = true;

		PreparedStatement pst = conn.prepareStatement("select balance from account where accountno = ?");

		System.out.print("\nEnter Your AccountNumber : ");
		long accno = sc.nextLong();
		sc.nextLine();

		pst.setLong(1, accno);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			System.out.println("Your Account Balance = " + rs.getDouble(1));
			System.out.println();
			con = false;
		}

		if (con)
			System.out.println("\nInvalid Account Number....!\n");
	}
}