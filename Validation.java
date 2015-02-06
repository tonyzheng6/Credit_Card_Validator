/*******************************************************************************************************
 * Title:		Validation.java
 * Author:		Tony Zheng
 * Created On:	January 11, 2015
 * Modified on: January 17, 2015
 * Description:	Checks if a credit card number is valid or not using GUI and logging
 * Build with:	Eclipse or using the following commands on the glab machines 
 * 				To compile: javac *.java
 * 				To run: 	java CreditCard
 *******************************************************************************************************/
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Validation extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel inputPanel;
	private JTextField creditCardInput;
	private JLabel label;
	private JButton input1, input2, input3, input4, input5, input6, input7, input8, input9, enter, clear;

	public Validation()
	{
		super();			
		setLayout(new BorderLayout());
		inputPane();
		getContentPane().add(inputPanel, BorderLayout.CENTER);
	}
	
	public void inputPane()
	{
		inputPanel = new JPanel();

		label = new JLabel("Enter a credit card number as a long integer: ");
		creditCardInput = new JTextField(20);
		input1 = new JButton("1");
		input2 = new JButton("2");
		input3 = new JButton("3");
		input4 = new JButton("4");
		input5 = new JButton("5");
		input6 = new JButton("6");
		input7 = new JButton("7");
		input8 = new JButton("8");
		input9 = new JButton("9");
		enter = new JButton("Enter");
		clear = new JButton("Clear");
		
		inputPanel.add(label);
		inputPanel.add(creditCardInput);
		inputPanel.add(input1);
		inputPanel.add(input2);
		inputPanel.add(input3);
		inputPanel.add(input4);
		inputPanel.add(input5);
		inputPanel.add(input6);
		inputPanel.add(input7);
		inputPanel.add(input8);
		inputPanel.add(input9);
		inputPanel.add(enter);
		inputPanel.add(clear);

		ActionHandler Handler = new ActionHandler();
		
		creditCardInput.addActionListener(Handler);
		input1.addActionListener(Handler);
		input2.addActionListener(Handler);
		input3.addActionListener(Handler);
		input4.addActionListener(Handler);
		input5.addActionListener(Handler);
		input6.addActionListener(Handler);
		input7.addActionListener(Handler);
		input8.addActionListener(Handler);
		input9.addActionListener(Handler);
		enter.addActionListener(Handler);
		clear.addActionListener(Handler);
	}

	private class ActionHandler implements ActionListener
	{
		private String ccNumber = "", ccType = "", textField = "";
		private Date time;
		
		public void actionPerformed(ActionEvent event)
		{
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

			if(event.getSource() == creditCardInput)
			{
				textField = creditCardInput.getText();
				
				if(isNumeric(textField) && checkDigits(textField) && checkType(textField) && luhnCheck(textField))
				{
					JOptionPane.showMessageDialog(null, textField + " is valid");
					ccNumber = textField;
					time = new Date();
					System.out.println("Credit card number: " + ccNumber + " is a valid " + ccType  + " card; logged at: " + df.format(time));
				}	
				else
				{
					ccNumber = textField;
					time = new Date();
					System.out.println("Credit card number: " + ccNumber + " is invalid; logged at: " + df.format(time));
				}
			}
			else if(event.getSource() == enter)
			{
				textField = creditCardInput.getText();

				if(isNumeric(textField) && checkDigits(textField) && checkType(textField) && luhnCheck(textField))
				{
					JOptionPane.showMessageDialog(null, textField + " is valid");
					ccNumber = textField;
					time = new Date();
					System.out.println("Credit card number: " + ccNumber + " is a valid " + ccType  + " card; logged at: " + df.format(time));
				}
				else
				{
					ccNumber = textField;
					time = new Date();
					System.out.println("Credit card number: " + ccNumber + " is invalid; logged at: " + df.format(time));
				}
			}
			else if(event.getSource() == clear)
			{
				creditCardInput.setText("");
			}
			else
			{
				String userInput = ((JButton)event.getSource()).getText();
				creditCardInput.setText(creditCardInput.getText() + userInput);
			}
		}
		
		public boolean isNumeric(String string)
		{
			for(int i = 0; i < string.length(); i++)
			{
				if(!Character.isDigit(string.charAt(i)))
				{
					JOptionPane.showMessageDialog(null, "Only enter numeric values");
					return false;
				}
			}
			
			return true;
		}
		
		public boolean checkDigits(String string)
		{
			if(string.length() < 13 || string.length() > 16)
			{
				JOptionPane.showMessageDialog(null, "Credit card number has incorrect number of digits: " + string.length() + ". Must be between 13 and 16 digits (inclusive)");
				return false;
			}
			
			ccNumber = string;
			return true;
		}
		
		public boolean checkType(String string)
		{			
			switch(string.charAt(0))
			{
				case '4': 
					ccType = "Visa"; 
					break;
				case '5': 
					ccType = "Master"; 
					break;
				case '3': 
					if(string.charAt(1) == '7')
					{
						ccType = "American Express"; 
						break;
					}
					JOptionPane.showMessageDialog(null, "Credit card number must start with digits corresponding a company");
					return false;
				case '6': 
					ccType = "Discover"; 
					break;
				default:
					JOptionPane.showMessageDialog(null, "Credit card number must start with digits corresponding a company");
					return false;
			}
			
			return true;
		}
		
		public boolean luhnCheck(String string)
		{
			String twoDigit;
			int number = 0, stringSize = string.length(), sum = 0, multiplied = 0;
			char first, second, digit;
			
			int[] myArr = new int[stringSize];
			
			for(int i = 0; i < string.length(); i++)
			{
				digit = string.charAt(i);
				number = Character.getNumericValue(digit);
				myArr[i] = number;
			}
			
			for(int i = stringSize-2; i >= 0; i-=2)
			{
				multiplied = myArr[i] * 2;
				
				if(multiplied >= 10)
				{
					twoDigit = String.valueOf(multiplied);
					first = twoDigit.charAt(0);
					second = twoDigit.charAt(1);
					multiplied = Character.getNumericValue(first) + Character.getNumericValue(second);
					sum += multiplied;
				}
				else
				{
					sum += multiplied;
				}
			}
			
			for(int i = stringSize-1; i >= 0; i-=2)
			{
				sum += myArr[i];
			}
					
			if(sum % 10 == 0)
			{
				return true;
			}
			
			JOptionPane.showMessageDialog(null, "Credit card number has failed the validity check");
			return false;
		}
	}
}