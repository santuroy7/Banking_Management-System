package net.codejava;
import java.sql.Connection;
import java.util.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class projectmyql {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		String url=null;
		ResultSet results= null;
		Statement stmt=null;
		Scanner sc=new Scanner(System.in);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	    System.out.print("\n\n\t\t\t\t\t\t\t\t Enter User Name: ");
	    String username=sc.next();
	    System.out.print("\n\t\t\t\t\t\t\t\t Enter Password: ");
	    String password=sc.next();
		System.out.print("\n\t\t\t\t\t\t\t\t Enter Your Database Name: ");
		String database=sc.next();
	    url ="jdbc:mysql://localhost:3306/"+database;
//		used for connection
		try {
			Connection connection = DriverManager.getConnection(url , username, password);
			System.out.println("\t\t\t\t\t\t\t\t Wait..Connecting To Database... ");
			System.out.println("\t\t\t\t\t\t\t\t Connected To The Database....");
			
		int choice;
		do
		{
		
			System.out.println("\n\n\t\t\t\t\t\t\t ********** Banking Management System **********");
			// Display the menu
			System.out.println("\n\n\t\t\t\t\t\t\t\t Press----\n\t\t\t\t\t\t\t\t 1. For Display Customer Records\n\t\t\t\t\t\t\t\t 2. For Add Customer Record. \n\t\t\t\t\t\t\t\t 3. For Delete Customer record \n\t\t\t\t\t\t\t\t 4. For Update Customer Record.\n\t\t\t\t\t\t\t\t 5. For Account Details.\n\t\t\t\t\t\t\t\t 6. For Display loan details.\n\t\t\t\t\t\t\t\t 7. For Deposite Money.\n\t\t\t\t\t\t\t\t 8. For Withdraw Money.\n\t\t\t\t\t\t\t\t 9. For EXIT!");
			System.out.print("\n\t\t\t\t\t\t\t\t Enter your choice(1-9): ");
			choice=sc.nextInt();
			// Accept user's choice
			switch(choice)
			{
			case 1:
			// Display customer records
				try {
				System.out.println("\n\n\t\t\t\t\t\t\t\t Displaying Customer Records...");
				System.out.println("");
				stmt=connection.createStatement();
			   String sql="SELECT CUST_NO,NAME,PHONE_NO,CITY FROM CUSTOMER";
					results=stmt.executeQuery(sql);
					boolean record=results.next();
					if(!record)
					{
						System.out.println("\t\t\t\t\t\t\t\t\t\tOops! There are no rows in the table!");
						break;
					}
					
					
					do {
						String name,cust_no,city, ph;
						cust_no=results.getString(1);
						name=results.getString(2);
						ph=results.getString(3);
						city=results.getString(4);
						System.out.print(" \t\t\t\t\t\t"+cust_no);
						System.out.print("\t"+name);
						System.out.print("\t"+ph);
						System.out.print("\t"+city);
						System.out.println(" ");
					
						
					}while(results.next());
					
				    stmt.close();
				}
				catch(SQLException e) {
					System.out.println("\t\t\t\t\t\t\t\t Oops Error In Displaying Table....");
				}
				
				
			break;
			case 2:
				
			// Add customer record
				System.out.println("\n\t\t\t\t\t\t\t\t Adding Customer Record...");
				try {
			// Accept input for each column from user
					int eg=1;
					while(eg==1) {
				String c_no, Name,city;
				long phone;
				System.out.print("\n\t\t\t\t\t\t\t Enter CUST_NO: ");
				c_no=br.readLine();
				
				System.out.print("\t\t\t\t\t\t\t Enter NAME: ");
				Name=br.readLine();
				
				System.out.print("\t\t\t\t\t\t\t Enter PHONE: ");
				
				phone=Long.parseLong(br.readLine());
				System.out.print("\t\t\t\t\t\t\t Enter CITY: ");
				city=br.readLine();
				stmt=connection.createStatement();
				String insertstmt="INSERT INTO CUSTOMER VALUES('"+c_no+"','"+Name+"','"+phone+"','"+city+"')";
			stmt.executeUpdate(insertstmt);
			System.out.print("\t\t\t\t\t\t\t Row Inserted Successfully");
		    stmt.close();
		    System.out.print("\n\t\t\t\t\t\t\t Want To Add More Customer Record(1/0): ");
		    eg=sc.nextInt();
					}
				}
				catch(SQLException e) {
					System.out.println("\t\t\t\t\t\t\t Oops Unable To Insert...");
					
				}
			
				
				
				break;
			case 3:
			// Delete customer record
			// Accept customer number from user
				try {
					System.out.println("\n\n\t\t\t\t\t\t\t\t Remove Customer Record...");
					int eg=1;
					while(eg==1) {
					System.out.print("\n\t\t\t\t\t\t\t Enter The Customer Number To Be Deleted: ");
					String c=br.readLine();
					stmt=connection.createStatement();
					String checkid="SELECT CUST_NO FROM CUSTOMER WHERE CUST_NO='"+c+"'";
					results=stmt.executeQuery(checkid);
					boolean record=results.next();
					if(!record)
					{
						System.out.println("\n\t\t\t\t\t\t\t Error! Invalid Customer No.");
						break;
					}
					String deletestmt="DELETE FROM CUSTOMER WHERE CUST_NO='"+c+"'";
					stmt.executeUpdate(deletestmt);
					System.out.print("\n\t\t\t\t\t\t\t Customer Record Deleted Successfully.");
				    stmt.close();
				    System.out.print("\n\t\t\t\t\t\t\t Want To Delete More Customer Record(1/0): ");
				    eg=sc.nextInt();
					}
				}
				catch(SQLException e) {
					System.out.println("\n\t\t\t\t\t\t\t Oops Unable To Delete...");
				}
			break;
			case 4:
			// Update customer record
			// Accept customer number from user
				System.out.println("\n\n\t\t\t\t\t\t\t Updateing.....");
				System.out.print("\t\t\t\t\t\t\t Enter Customer Number To be Updated: ");
				String c_no=br.readLine();
				System.out.println("\n\n\t\t\t\t\t\t\t Press----\n\t\t\t\t\t\t\t 1: For Name\n\t\t\t\t\t\t\t 2: For Phone no\n\t\t\t\t\t\t\t 3: For City");
			System.out.print("\n\t\t\t\t\t\t\t Which one you want to update, Enter your choice: ");
			// Accept user's choice
			 int choice2=sc.nextInt();
				    switch(choice2)
					{
					case 1:
					// Update customer's name
						System.out.print("\t\t\t\t\t\t\t Enter The Name To Be Updated: ");
						String nm=br.readLine();
						stmt=connection.createStatement();
						String nameupdate="UPDATE CUSTOMER SET NAME='"+nm+"'WHERE CUST_NO='"+c_no+"'";
						stmt.executeUpdate(nameupdate);
						System.out.print("\n\t\t\t\t\t\t\t Name Updated Successfully...");
						stmt.close();
						
					break;
					case 2:
					// Update customer's phone number
						System.out.print("\n\t\t\t\t\t\t\t Enter The Phone Number To Be Updated: ");
						long pno=Long.parseLong(br.readLine());
						stmt=connection.createStatement();
						String phoneupdate="UPDATE CUSTOMER SET PHONE_NO='"+pno+"'WHERE CUST_NO='"+c_no+"'";
						stmt.executeUpdate(phoneupdate);
						System.out.print("\t\t\t\t\t\t\t Phone Number Updated Successfully...");
						stmt.close();
						
					break;
					case 3:
					// Update customer's city
						System.out.print("\n\n\t\t\t\t\t\t\t Enter The City To Be Updated: ");
						String city=br.readLine();
						stmt=connection.createStatement();
						String cityupdate="UPDATE CUSTOMER SET CITY='"+city+"'WHERE CUST_NO='"+c_no+"'";
						stmt.executeUpdate(cityupdate);
						System.out.print("\t\t\t\t\t\t\t City Updated Successfully...");
						stmt.close();
					break;
					default:
						// Handle wrong choice of option
						System.out.println("\n\t\t\t\t\t\t\t Oops! Invalid Option!");
						break;
					}
			break;
			case 5:
			// Display account details
			// Accept customer number from user
				try {
					System.out.print("\n\n\t\t\t\t\t\t\t Displaying Account Details...");
				System.out.print("\n\t\t\t\t\t\t\t Enter Customer Number: ");
				String customer=br.readLine();
				stmt=connection.createStatement();
				String accdetails="SELECT * FROM ACCOUNT NATURAL JOIN DEPOSITOR NATURAL JOIN BRANCH NATURAL JOIN CUSTOMER WHERE CUST_NO='"+customer+"'";
				results=stmt.executeQuery(accdetails);
				boolean record=results.next();
				if(!record)
				{
					System.out.println("\t\t\t\t\t\t\t Oops! There are no Account for Customer.");
					break;
				}
				do {
//					System.out.println(" "+"CUST_NO"+"\t"+"BRANCH_CODE"+"\t"+"ACCOUNT_NO"+"\t"+"TYPE"+"\t"+"BALANCE"+"\t"+" BRANCH_CITY"+"\t"+"NAME"+"\t"+"PHONE_NO"+"\t"+" CITY");
//					
					System.out.print(" \t\t\t\t\t"+results.getString(1));
					System.out.print("\t"+results.getString(2));
					System.out.print("\t"+results.getString(3));
					System.out.print("\t"+results.getString(4));
					System.out.print("\t"+results.getString(5));
					System.out.print("\t"+results.getString(6));
					System.out.print("\t"+results.getString(7));
					System.out.print("\t"+results.getString(8));
					System.out.print("\t"+results.getString(9));
					System.out.print("\t"+results.getString(10));
					
					System.out.println(" ");
					
				
					
				}while(results.next());
				stmt.close();
				}
				catch(SQLException e) {
					System.out.println("\t\t\t\t\t\t\t Oops! Unable To Show Account Details.");
					
				}
				
			 break;
			case 6:
			// Display loan details
			// Accept customer number from user
				// Display the number of loans the customer has or
				// Congratulation if he customer has no loan
				try {
					System.out.print("\n\n\t\t\t\t\t\t\t Displaying Loan Details...");
					System.out.print("\n\t\t\t\t\t\t\t Enter Customer Number: ");
					String customer=br.readLine();
					stmt=connection.createStatement();
					String checkid="SELECT CUST_NO FROM CUSTOMER WHERE CUST_NO='"+customer+"'";
					results=stmt.executeQuery(checkid);
					boolean re=results.next();
					if(!re)
					{
						System.out.println("\n\t\t\t\t\t\t\t Error! Invalid Customer No.");
						break;
					}
					String loandetails="SELECT * FROM CUSTOMER NATURAL JOIN LOAN WHERE CUST_NO='"+customer+"'";
					results=stmt.executeQuery(loandetails);
					boolean record=results.next();
					if(!record)
					{
						System.out.println("\n\t\t\t\t\t\t\t Congratulation You Have No Loan.");
						break;
					}
					do {
//						System.out.println(" "+"CUST_NO"+"\t"+"BRANCH_CODE"+"\t"+"ACCOUNT_NO"+"\t"+"TYPE"+"\t"+"BALANCE"+"\t"+" BRANCH_CITY"+"\t"+"NAME"+"\t"+"PHONE_NO"+"\t"+" CITY");
						System.out.println(" ");
						System.out.print(" \t\t\t\t\t"+results.getString(1));
						System.out.print("\t"+results.getString(2));
						System.out.print("\t"+results.getString(3));
						System.out.print("\t"+results.getString(4));
						System.out.print("\t"+results.getString(5));
						System.out.print("\t"+results.getString(6));
						System.out.print("\t"+results.getString(7));
//						System.out.print("\t"+results.getString(8));
//						System.out.print("\t"+results.getString(9));
//						System.out.print("\t"+results.getString(10));
						
						System.out.println(" ");
						
					
						
					}while(results.next());
					stmt.close();
					}
					catch(SQLException e) {
						System.out.println("\n\t\t\t\t\t\t\t Oops! Unable To Show Loan Details.");
						
					}
				break;
				case 7:
				//Deposit money
				// Accept the account number to be deposited in
				// Message for transaction completion
					System.out.print("\n\n\t\t\t\t\t\t\t Enter The Account No To Be Deposited In: ");
					String acco=br.readLine();
					stmt=connection.createStatement();
					String checkbalance="SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO='"+acco+"'";
					results=stmt.executeQuery(checkbalance);
					boolean record=results.next();
					if(!record)
					{
						System.out.println("\n\t\t\t\t\t\t\t Error! Invalid Account Number.");
						break;
					}
					System.out.print("\t\t\t\t\t\t\t Enter The Amount To Be Deposited: ");
					long amount = Long.parseLong(br.readLine());
					System.out.print("\t\t\t\t\t\t\t Your Current Balance is: "+results.getLong(1));
					System.out.println("");
					try {
					String deposite="UPDATE ACCOUNT SET BALANCE=BALANCE+"+amount+" WHERE ACCOUNT_NO='"+acco+"'";
					stmt.executeUpdate(deposite);
					System.out.println("\t\t\t\t\t\t\t Amount Successfully Deposited.");
					String updatebalance="SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO='"+acco+"'";
					results = stmt.executeQuery(updatebalance);
					results.next();
					System.out.print("\t\t\t\t\t\t\t Your New Balance is: "+results.getLong(1));
					stmt.close();
					}
					catch(SQLException e) {
						System.out.println("\t\t\t\t\t\t\t Error! Try Again.");
					}
				break;
				case 8:
				//Withdraw money
				// Accept the account number to be withdrawn from
				// Handle appropriate withdraw check conditions
				// Message for transaction completion
					System.out.print("\n\n\t\t\t\t\t\t\t Enter The Account No To Be Withdraw From: ");
					String acc=br.readLine();
					System.out.print("\t\t\t\t\t\t\t Enter The Amount To Be Withdrawn: ");
					long amo = Long.parseLong(br.readLine());
					stmt=connection.createStatement();
					String currentbalance="SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO='"+acc+"'";
					results=stmt.executeQuery(currentbalance);
					boolean re=results.next();
					if(!re)
					{
						System.out.println("\n\t\t\t\t\t\t\t Error! Invalid Account Number.");
						break;
					}
					System.out.print("\t\t\t\t\t\t\t Your Current Balance is: "+results.getLong(1));
					System.out.println("");
					try {
						if(amo>=results.getLong(1)) {
							System.out.println("\n\t\t\t\t\t\t\t Insufficent Balance:(");
						}
						else {
					String withdraw="UPDATE ACCOUNT SET BALANCE=BALANCE-"+amo+" WHERE ACCOUNT_NO='"+acc+"'";
					stmt.executeUpdate(withdraw);
					System.out.println("\t\t\t\t\t\t\t Amount Withdrawn Successfully.");
					String newbalance="SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO='"+acc+"'";
					results = stmt.executeQuery(newbalance);
					results.next();
					System.out.print("\t\t\t\t\t\t\t Your New Balance is: "+results.getLong(1));
					stmt.close();
						}
					}
					catch(SQLException e) {
						System.out.println("\t\t\t\t\t\t\t Error! Try Again.");
					}
					
				break;
				case 9:
				// Exit the menu
					System.out.println("\n\t\t\t\t\t\t\t\t Exiting From The System...");
					System.out.println("\n\t\t\t\t\t\t\t\t Thank You ^_^");
				break;
				default:
					// Handle wrong choice of option
					System.out.println("\n\n\t\t\t\t\t\t\t\t Oops! Invalid Option! Try Again...");
					break;
				}
			}while(choice!=9);
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("\n\n\t\t\t\t\t\t\t\t Error! unable to connect to the Database. Invalid USER or PASSWORD or DATABASE.");
			System.out.println("");
			e.printStackTrace();
		}
		

	}

}
