package bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateAccount {

	private static Scanner sc = new Scanner(System.in);
	static Connection conn = GetConnection.getConnection();

	public static void toCreateAccount() throws SQLException, ClassNotFoundException {

		PreparedStatement pst = conn.prepareStatement("select accountno from account where accountno = ?");

		System.out.print("\nEnter Account No. : ");
		long accountNumber = sc.nextLong();
		sc.nextLine();
		System.out.println();

		pst.setLong(1, accountNumber);
		pst.execute();
		ResultSet rs = pst.getResultSet();

		if (!rs.next()) {
			System.out.print("Enter AccountHolder Name : ");
			String accountHolderName = sc.nextLine().toUpperCase();

			CallableStatement cst = conn.prepareCall("call db1.toCreateAccount(?,?,?)");
			cst.setString(1, accountHolderName);
			cst.setLong(2, accountNumber);
			cst.setDouble(3, 0.0);
			cst.execute();

			System.out.println();
			System.out.println("Account Created Succesfully....");
			System.out.println();
		} 
		else {
			System.out.println("This Account Number is Already Exists....!");
			System.out.println("Please Create Different Account....!\n");
		}
	}
}