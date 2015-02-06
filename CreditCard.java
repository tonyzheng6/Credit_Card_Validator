/*******************************************************************************************************
 * Title:		CreditCard.java
 * Author:		Tony Zheng
 * Created On:	January 11, 2015
 * Modified on: January 17, 2015
 * Description:	Checks if a credit card number is valid or not using GUI and logging
 * Build with:	Eclipse or using the following commands on the glab machines 
 * 				To compile: javac *.java
 * 				To run: 	java CreditCard
 *******************************************************************************************************/
import javax.swing.*;

public class CreditCard 
{
	public static void main(String[] args)
	{
		Validation CheckCC = new Validation();
		
		CheckCC.setTitle("Credit Card Validator");
		CheckCC.setSize(300, 225);
		CheckCC.setLocationRelativeTo(null);
		CheckCC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckCC.setVisible(true);
		CheckCC.setResizable(false);
	}
}