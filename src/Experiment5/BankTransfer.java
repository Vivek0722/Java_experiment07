package Experiment5;

import java.sql.*;
import java.util.Scanner;

public class BankTransfer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/bankdb";
        String user = "root";
        String pass = "root";
        

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

            con.setAutoCommit(false);
           
            System.out.print("Enter From Account Number: ");
            int fromAcc = sc.nextInt();

            System.out.print("Enter To Account Number: ");
            int toAcc = sc.nextInt();

            System.out.print("Enter Amount to Transfer: ");
            int amount = sc.nextInt();

            // Check From Account Balance
            PreparedStatement ps1 = con.prepareStatement(
                "SELECT balance FROM account WHERE acc_no = ?");
            ps1.setInt(1, fromAcc);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("From Account not found.");
                return;
            }

            double balance = rs.getDouble("balance");

            if (balance < amount) {
                System.out.println("Insufficient Balance.");
                con.rollback();
                return;
            }

            // Check To Account exists
            PreparedStatement ps2 = con.prepareStatement(
                "SELECT * FROM account WHERE acc_no = ?");
            ps2.setInt(1, toAcc);

            ResultSet rsCheck = ps2.executeQuery();

            if (!rsCheck.next()) {
                System.out.println("To Account not found.");
                con.rollback();
                return;
            }

            // Debit
            PreparedStatement debit = con.prepareStatement(
                "UPDATE account SET balance = balance - ? WHERE acc_no = ?");
            debit.setInt(1, amount);
            debit.setInt(2, fromAcc);
            debit.executeUpdate();

            System.out.println("Amount Debited from Account: " + fromAcc);

            // Credit
            PreparedStatement credit = con.prepareStatement(
                "UPDATE account SET balance = balance + ? WHERE acc_no = ?");
            credit.setInt(1, amount);
            credit.setInt(2, toAcc);
            credit.executeUpdate();

            System.out.println("Amount Credited to Account: " + toAcc);

            con.commit();

            System.out.println("Transaction Successful");

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback(); // IMPORTANT
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Transaction Failed");
            e.printStackTrace();

        } finally {
            try {
                if (con != null) con.close();
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
