import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

class Demo{
	public static void main(String[] args){
		iBurgerShop b=new iBurgerShop();	
		b.homepage();				
	}
}

class Customer{
	private String customerId;
	private String customerName;
	
	Customer(String customerId, String customerName){	
		this.customerId=customerId;
		this.customerName=customerName;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public String getCustomerName(){
		return customerName;
	}	
}

class Order{
	private String orderId;
	private String customerId;
	private int quantity;
	private int status;	
	
	Order(String orderId, String customerId, int quantity, int status){
		this.orderId=orderId;
		this.customerId=customerId;
		this.quantity=quantity;
		this.status=status;
	}
	
	public String getOrderId(){
		return orderId;
	}
	
	public String getCustomerId(){
		return customerId;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public int getStatus(){
		return status;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	
	public void setStatus(int status){
		this.status=status;
	}			
}
	
class CustomerList{
	private Customer[] customerArray;
	private int nextIndex;
	
	CustomerList(){
		customerArray=new Customer[5];
		nextIndex=0;
	}	
	public void add(Customer customer){
		if(nextIndex==customerArray.length){
			extendsArray();
		}
		customerArray[nextIndex++]=customer;	
	}
	public void extendsArray(){
		Customer[] temp=new Customer[customerArray.length+5];
		for(int i=0; i<nextIndex; i++){
			temp[i]=customerArray[i];
		}
		customerArray=temp;	
	}
	public boolean contains(String customerId){
		for(int i=0; i<nextIndex; i++){
			if(customerArray[i].getCustomerId().equals(customerId)){
				return true;
			}	
		}	
		return false;
	}
	public int size(){
		return nextIndex;
	}	
	public String getCustomerId(int index){
		return customerArray[index].getCustomerId();
	}	
	public String getCustomerName(int index){
		return customerArray[index].getCustomerName();
	}					
}
	
class OrderList{
	private Order[] orderArray;
	private int nextIndex;
	
	OrderList(){
		orderArray=new Order[5];
		nextIndex=0;
	}	
	public void add(Order order){
		if(nextIndex==orderArray.length){
			extendsArray();
		}
		orderArray[nextIndex++]=order;	
	}
	public void extendsArray(){
		Order[] temp=new Order[orderArray.length+5];
		for(int i=0; i<nextIndex; i++){
			temp[i]=orderArray[i];
		}
		orderArray=temp;	
	}
	public String getOrderId(int index){
			return orderArray[index].getOrderId();	
	}	
	public int size(){
		return nextIndex;
	}
	public int getStatus(int index){
		return orderArray[index].getStatus();
	}
	public int getQuantity(int index){
		return orderArray[index].getQuantity();
	}
	public String getCustomerId(int index){
		return orderArray[index].getCustomerId();
	}
	public void setQuantity(int index, int qty){
		orderArray[index].setQuantity(qty);
	}
	public void setStatus(int index, int status){
		orderArray[index].setStatus(status);
	}				
}		

class iBurgerShop extends JFrame{
	private static final int CANCEL=0;
	private static final int PREPARING=1;
	private static final int DELIVERED=2;
	private static final int BURGERPRICE=500;
	private int order=1;	
	CustomerList customerList=new CustomerList();
	OrderList orderList=new OrderList();	
	
	public void homepage(){		
		setSize(1050,700);
		setTitle("iHungry Burgers");	
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
			
		JLabel heading=new JLabel("Home Page");
		heading.setFont(new Font("",1,45));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		
		JButton addNewCustomerBtn=new JButton("Add New Customer");
		addNewCustomerBtn.setFont(new Font("",0,20));
		addNewCustomerBtn.setBounds(75,100,350,35);
		
		JButton placeOrderBtn=new JButton("Place Order");
		placeOrderBtn.setFont(new Font("",0,20));
		placeOrderBtn.setBounds(75,180,350,35);
		
		JButton updateOrderBtn=new JButton("Update Order");
		updateOrderBtn.setFont(new Font("",0,20));
		updateOrderBtn.setBounds(75,260,350,35);
		
		JButton viewOrdersBtn=new JButton("View Orders");
		viewOrdersBtn.setFont(new Font("",0,20));
		viewOrdersBtn.setBounds(75,340,350,35);
		
		JButton exitBtn=new JButton("Exit");
		exitBtn.setFont(new Font("",0,20));
		exitBtn.setBounds(350,500,120,30);
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(new Color(153, 204, 255));
		centerPanel.add(addNewCustomerBtn);
		centerPanel.add(placeOrderBtn);
		centerPanel.add(updateOrderBtn);
		centerPanel.add(viewOrdersBtn);
		centerPanel.add(exitBtn);
		
		JPanel textPanel=new JPanel(new BorderLayout());
		textPanel.add("North",northPanel);
		textPanel.add(centerPanel);
		add(textPanel);
		
		JLabel imgPanel = new JLabel(new ImageIcon("homepage.png"));
		add("West",imgPanel);	
			
	
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){			
				System.exit(0);
			}
		});	
		
		addNewCustomerBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(textPanel);
				remove(imgPanel);
				repaint();				
				addNewCustomer();		
			}
		});
		
		placeOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){	
				remove(textPanel);
				remove(imgPanel);
				repaint();			
				placeOrder();		
			}
		});
		
		updateOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){	
				remove(textPanel);
				remove(imgPanel);
				repaint();			
				updateOrder();		
			}
		});
		
		viewOrdersBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){	
				remove(textPanel);
				remove(imgPanel);
				repaint();			
				viewOrders();		
			}
		});
	
	}
	
	public boolean isValidCustomerId(String id){
		if(id.charAt(0)!='0'||id.length()!=10){
			return false;
		}
		for(int i=1; i<id.length(); i++){
			if(id.charAt(i)>57||id.charAt(i)<48){
				return false;
			}
		}
		return true;
	}
	
	public String orderId(){
		String orderID="";
		if(order<10){
			orderID="B000"+Integer.toString(order);
		}else if(order<100){
			orderID="B00"+Integer.toString(order);
		}else if(order<1000){
			orderID="B0"+Integer.toString(order);	
		}else if(order<10000){
			orderID="B"+Integer.toString(order);	
		}
		return orderID;
	}
	
	public void custIdErrorDialog1(){
		JDialog dialog=new JDialog(new JFrame(),"error",true);
		dialog.setSize(380,130);
		JLabel errorLabel=new JLabel("Customer ID is not in correct format!!!");
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setFont(new Font("",0,18));
		JPanel centerPanel=new JPanel();
		centerPanel.add(errorLabel);
		centerPanel.setBackground(Color.WHITE);
		dialog.add(centerPanel);
		
		JButton okBtn=new JButton("Ok");
		okBtn.setFont(new Font("",1,18));
		
		JPanel panel=new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.add(okBtn);
		dialog.add("South",panel);
		
		ImageIcon img=new ImageIcon("error.png");	
		img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
		JLabel imgLabel = new JLabel(img);
		dialog.add("West",imgLabel);
		
		okBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dialog.dispose();
			}
		});
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}	
	
	public void custIdErrorDialog2(){		
		JDialog dialog=new JDialog(new JFrame(),"error",true);
		dialog.setSize(340,130);
		JLabel errorLabel=new JLabel("This Customer ID already exists!!!");
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setFont(new Font("",0,18));
		JPanel centerPanel=new JPanel();
		centerPanel.add(errorLabel);
		centerPanel.setBackground(Color.WHITE);
		dialog.add(centerPanel);		
		
		JButton okBtn=new JButton("Ok");
		okBtn.setFont(new Font("",1,18));
		
		JPanel panel=new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.add(okBtn);
		dialog.add("South",panel);
		
		ImageIcon img=new ImageIcon("error.png");	
		img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
		JLabel imgLabel = new JLabel(img);
		dialog.add("West",imgLabel);
		
		okBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dialog.dispose();
			}
		});
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);	
	}	
	
	public void custIdErrorDialog3(){
		JDialog dialog=new JDialog(new JFrame(),"error",true);
		dialog.setSize(400,130);
		JLabel errorLabel=new JLabel("This Customer ID has not registered yet!!!");
		errorLabel.setHorizontalAlignment(JLabel.CENTER);
		errorLabel.setFont(new Font("",0,18));
		JPanel centerPanel=new JPanel();
		centerPanel.add(errorLabel);
		centerPanel.setBackground(Color.WHITE);
		dialog.add(centerPanel);
		
		JButton okBtn=new JButton("Ok");
		okBtn.setFont(new Font("",1,18));
		
		ImageIcon img=new ImageIcon("error.png");	
		img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
		JLabel imgLabel = new JLabel(img);
		dialog.add("West",imgLabel);
		
		JPanel panel=new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.add(okBtn);
		dialog.add("South",panel);
		
		okBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dialog.dispose();
			}
		});
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	public void orderSuccessDialog(){
		JDialog dialog=new JDialog(new JFrame(),"Success",true);
		dialog.setSize(440,130);
		JLabel successLabel=new JLabel("Order has entered to the system successfully.");
		successLabel.setHorizontalAlignment(JLabel.CENTER);
		successLabel.setFont(new Font("",0,18));
		JPanel centerPanel=new JPanel();
		centerPanel.add(successLabel);
		centerPanel.setBackground(Color.WHITE);
		dialog.add(centerPanel);
		
		JButton okBtn=new JButton("Ok");
		okBtn.setFont(new Font("",1,18));
		
		ImageIcon img=new ImageIcon("success.jpeg");	
		img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
		JLabel imgLabel = new JLabel(img);
		dialog.add("West",imgLabel);
		
		JPanel panel=new JPanel(new FlowLayout());
		panel.setBackground(Color.WHITE);
		panel.add(okBtn);
		dialog.add("South",panel);
		
		okBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dialog.dispose();
			}
		});
		
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);	
	}			
	
	public void addNewCustomer(){
		setSize(500,510);
		setTitle("Add New Customer");
		setLocationRelativeTo(null);	
		
		JLabel heading=new JLabel("Add New Customer");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		JLabel custIdLabel=new JLabel("Customer ID");
		custIdLabel.setFont(new Font("",1,18));
		custIdLabel.setBounds(40,40,150,25);
		
		JLabel custNameLabel=new JLabel("Customer Name");
		custNameLabel.setFont(new Font("",1,18));
		custNameLabel.setBounds(40,82,150,25);
		
		JLabel orderIdLabel1=new JLabel("Order ID - ");
		orderIdLabel1.setFont(new Font("",1,25));
		orderIdLabel1.setBounds(20,150,150,25);
		
		JLabel orderIdLabel2=new JLabel(orderId());
		orderIdLabel2.setFont(new Font("",1,25));
		orderIdLabel2.setBounds(140,150,150,25);
		
		JLabel qtyLabel=new JLabel("Burgur Quantity");
		qtyLabel.setFont(new Font("",1,18));
		qtyLabel.setBounds(40,210,150,25);
		
		JLabel totalIdLabel=new JLabel("Bill Value");
		totalIdLabel.setFont(new Font("",1,18));
		totalIdLabel.setBounds(40,252,150,25);
		
		JTextField custIdText=new JTextField();
		custIdText.setBounds(240,40,100,25);
		custIdText.setFont(new Font("",0,17));
		
		JTextField custNameText=new JTextField();
		custNameText.setBounds(240,80,180,25);
		custNameText.setFont(new Font("",0,17));
		custNameText.setBackground(Color.WHITE);
		custNameText.setEditable(false);
		
		JTextField qtyText=new JTextField();
		qtyText.setBounds(240,210,70,25);
		qtyText.setFont(new Font("",0,17));
		qtyText.setBackground(Color.WHITE);
		qtyText.setEditable(false);
		
		JTextField totalText=new JTextField();
		totalText.setBounds(240,250,130,25);
		totalText.setFont(new Font("",0,17));
		totalText.setBackground(Color.WHITE);
		totalText.setEditable(false);
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(custIdLabel);
		centerPanel.add(custNameLabel);
		centerPanel.add(orderIdLabel1);
		centerPanel.add(orderIdLabel2);
		centerPanel.add(qtyLabel);
		centerPanel.add(totalIdLabel);
		centerPanel.add(custIdText);
		centerPanel.add(custNameText);
		centerPanel.add(qtyText);
		centerPanel.add(totalText);
		add(centerPanel);	
		
		JButton placeOrderBtn=new JButton("Place Order");
		placeOrderBtn.setEnabled(false);
		placeOrderBtn.setFont(new Font("",1,20));
		
		JButton cancelBtn=new JButton("Cancel");
		cancelBtn.setFont(new Font("",1,20));
		
		JButton backBtn=new JButton("Back to Main Menu");
		backBtn.setFont(new Font("",1,20));
		backBtn.setPreferredSize(new Dimension(250,40));
		
		JPanel btnPanel1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel1.setBackground(Color.WHITE);
		btnPanel1.add(placeOrderBtn);
		btnPanel1.add(cancelBtn);
		
		JPanel btnPanel2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel2.setBackground(Color.WHITE);
		btnPanel2.add(backBtn);
		
		JPanel southPanel=new JPanel(new GridLayout(2,1));
		southPanel.setBackground(Color.WHITE);
		southPanel.add(btnPanel1);
		southPanel.add(btnPanel2);
		add("South",southPanel);
		
		custIdText.requestFocus();
		custIdText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!isValidCustomerId(custIdText.getText())){
					custIdErrorDialog1();			
					custIdText.setText(null);
					return;
				}	
				if(customerList.contains(custIdText.getText())){
					custIdErrorDialog2();			
					custIdText.setText(null);
					return;
				}
				custNameText.setEditable(true);
				custNameText.requestFocus();
			}
		});	
		
		custNameText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				qtyText.setEditable(true);
				qtyText.requestFocus();
			}
		});
		
		qtyText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				totalText.setText(Integer.toString(BURGERPRICE*Integer.parseInt(qtyText.getText()))+".00");
				placeOrderBtn.setEnabled(true);
			}
		});
		
		placeOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				customerList.add(new Customer(custIdText.getText(),custNameText.getText()));
				orderList.add(new Order(orderId(),custIdText.getText(),Integer.parseInt(qtyText.getText()),PREPARING));			
				
				orderSuccessDialog();
							
				custIdText.setText(null);
				custNameText.setText(null);
				qtyText.setText(null);
				totalText.setText(null);
				custNameText.setEditable(false);
				qtyText.setEditable(false);
				placeOrderBtn.setEnabled(false);
				
				order++;
				orderIdLabel2.setText(orderId());
				custIdText.requestFocus();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				custIdText.setText(null);
				custNameText.setText(null);
				qtyText.setText(null);
				totalText.setText(null);
				custNameText.setEditable(false);
				qtyText.setEditable(false);
				placeOrderBtn.setEnabled(false);
				custIdText.requestFocus();
			}
		});
				
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				remove(southPanel);
				repaint();
				homepage();
			}
		});
					
	}
	
	public void placeOrder(){
		setTitle("Place Order");
		setSize(475,450);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("Place Order");
		heading.setFont(new Font("",1,35));
		
		JLabel orderIdLabel1=new JLabel(" ORDER ID - ");
		orderIdLabel1.setFont(new Font("",1,22));
		
		JLabel orderIdLabel2=new JLabel(orderId());
		orderIdLabel2.setFont(new Font("",1,22));
		
		JPanel headingPanel=new JPanel(new FlowLayout());
		headingPanel.setBackground(new Color(153, 204, 255));
		headingPanel.add(heading);
		
		JPanel orderIdPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		orderIdPanel.setBackground(Color.WHITE);
		orderIdPanel.add(orderIdLabel1);
		orderIdPanel.add(orderIdLabel2);
		
		JPanel northPanel=new JPanel(new GridLayout(2,1,0,10));
		northPanel.setBackground(Color.WHITE);
		northPanel.add(headingPanel);
		northPanel.add(orderIdPanel);	
		add("North",northPanel);
		
		JLabel custIdLabel=new JLabel("Customer ID");
		custIdLabel.setFont(new Font("",1,18));
		custIdLabel.setBounds(40,3,200,25);
		
		JLabel custNameLabel=new JLabel("Customer Name");
		custNameLabel.setFont(new Font("",1,18));
		custNameLabel.setBounds(40,43,200,25);
		
		JLabel qtyLabel=new JLabel("Burger Quantity");
		qtyLabel.setFont(new Font("",1,18));
		qtyLabel.setBounds(40,83,200,25);
		
		JLabel totalLabel=new JLabel("Bill Value");
		totalLabel.setFont(new Font("",1,18));
		totalLabel.setBounds(40,123,200,25);
		
		JTextField custIdText=new JTextField();
		custIdText.setFont(new Font("",0,17));
		custIdText.setBounds(220,0,110,27);
		
		JTextField custNameText=new JTextField(12);
		custNameText.setEditable(false);
		custNameText.setBackground(Color.WHITE);
		custNameText.setFont(new Font("",0,17));
		custNameText.setBounds(220,40,170,27);
		
		JTextField qtyText=new JTextField(8);
		qtyText.setEditable(false);
		qtyText.setBackground(Color.WHITE);
		qtyText.setFont(new Font("",0,17));
		qtyText.setBounds(220,80,70,27);
		
		JTextField totalText=new JTextField(8);
		totalText.setEditable(false);
		totalText.setBackground(Color.WHITE);
		totalText.setFont(new Font("",0,17));
		totalText.setBounds(220,120,140,27);
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(custIdLabel);
		centerPanel.add(custNameLabel);
		centerPanel.add(qtyLabel);
		centerPanel.add(totalLabel);
		centerPanel.add(custIdText);
		centerPanel.add(custNameText);
		centerPanel.add(qtyText);
		centerPanel.add(totalText);
		add(centerPanel);
		
		JButton placeOrderBtn=new JButton("Place Order");
		placeOrderBtn.setEnabled(false);
		placeOrderBtn.setFont(new Font("",1,20));
			
		JButton cancelBtn=new JButton("Cancel");
		cancelBtn.setFont(new Font("",1,20));
		
		JButton backBtn=new JButton("Back to Main Menu");
		backBtn.setFont(new Font("",1,20));
		backBtn.setPreferredSize(new Dimension(250,40));
		
		JPanel btnPanel1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel1.setBackground(Color.WHITE);
		btnPanel1.add(placeOrderBtn);
		btnPanel1.add(cancelBtn);
		
		JPanel btnPanel2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel2.setBackground(Color.WHITE);
		btnPanel2.add(backBtn);
		
		JPanel southPanel=new JPanel(new GridLayout(2,1));
		southPanel.add(btnPanel1);
		southPanel.add(btnPanel2);
		add("South",southPanel);
		
		custIdText.requestFocus();
		custIdText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!isValidCustomerId(custIdText.getText())){
					custIdErrorDialog1();			
					custIdText.setText(null);
					return;
				}	
				for(int i=0;i<customerList.size();i++){ 
					if(custIdText.getText().equals(customerList.getCustomerId(i))){ 
						custNameText.setText(customerList.getCustomerName(i));
						qtyText.setEditable(true); 
						qtyText.requestFocus(); 
						return; 
					} 
				}	
				
				custIdErrorDialog3();				
			}
		});	
		
		qtyText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				totalText.setText(Integer.toString(BURGERPRICE*Integer.parseInt(qtyText.getText()))+".00");
				placeOrderBtn.setEnabled(true);			
			}
		});	
		
		placeOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				orderList.add(new Order(orderIdLabel2.getText(),custIdText.getText(),Integer.parseInt(qtyText.getText()),PREPARING));										
				order++;
				
				orderSuccessDialog();
				
				custIdText.setText(null);
				custNameText.setText(null);
				qtyText.setText(null);
				totalText.setText(null);
				custNameText.setEditable(false);
				qtyText.setEditable(false);
				placeOrderBtn.setEnabled(false);
				orderIdLabel2.setText(orderId());
				custIdText.requestFocus();
																	
			}
		});
		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				custIdText.setText(null);
				custNameText.setText(null);
				qtyText.setText(null);
				totalText.setText(null);
				custNameText.setEditable(false);
				qtyText.setEditable(false);
				placeOrderBtn.setEnabled(false);
				custIdText.requestFocus();
			}
		});
		
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				remove(southPanel);
				repaint();
				homepage();	
			}
		});
		
	}
	
	public void updateOrder(){
		setTitle("Update Order");
		setSize(470,500);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("Update Order");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		JLabel orderIdLabel=new JLabel("Order ID");
		orderIdLabel.setFont(new Font("",1,18));
		orderIdLabel.setBounds(40,30,200,25);
		
		JLabel custIdLabel=new JLabel("Customer ID");
		custIdLabel.setFont(new Font("",1,18));
		custIdLabel.setBounds(40,70,200,25);
		
		JLabel custNameLabel=new JLabel("Customer Name");
		custNameLabel.setFont(new Font("",1,18));
		custNameLabel.setBounds(40,110,200,25);
		
		JLabel qtyLabel=new JLabel("Burger Quantity");
		qtyLabel.setFont(new Font("",1,18));
		qtyLabel.setBounds(40,150,200,25);
		
		JLabel totalLabel=new JLabel("Bill Value");
		totalLabel.setFont(new Font("",1,18));
		totalLabel.setBounds(40,190,200,25);
		
		JLabel statusLabel=new JLabel("Order Status");
		statusLabel.setFont(new Font("",1,18));
		statusLabel.setBounds(40,230,200,25);	
		
		JComboBox orderIdBox=new JComboBox();
		for(int i=0; i<orderList.size(); i++){
			orderIdBox.addItem(orderList.getOrderId(i));
		}
		orderIdBox.setFont(new Font("",1,18));
		orderIdBox.setBounds(230,28,90,25);	
		orderIdBox.setBackground(Color.WHITE);
		
		JTextField custIdText=new JTextField();
		custIdText.setFont(new Font("",0,17));
		custIdText.setBounds(230,69,110,25);	
		custIdText.setEditable(false);
		custIdText.setBackground(Color.WHITE);
		
		JTextField custNameText=new JTextField();
		custNameText.setFont(new Font("",0,17));
		custNameText.setBounds(230,110,180,25);	
		custNameText.setEditable(false);
		custNameText.setBackground(Color.WHITE);
		
		JTextField qtyText=new JTextField();
		qtyText.setFont(new Font("",0,17));
		qtyText.setBounds(230,152,70,25);	
		qtyText.setEditable(false);
		qtyText.setBackground(Color.WHITE);
		
		JTextField totalText=new JTextField();
		totalText.setFont(new Font("",0,17));
		totalText.setBounds(230,191,140,25);
		totalText.setEditable(false);
		totalText.setBackground(Color.WHITE);
			
		String[] orderStatusBoxItems={"Preparing","Cancel","Delivered"};
		JComboBox orderStatusBox=new JComboBox(orderStatusBoxItems);
		orderStatusBox.setFont(new Font("",1,17));	
		orderStatusBox.setBounds(230,230,110,25);
		orderStatusBox.setEnabled(false);	
		orderStatusBox.setBackground(Color.WHITE);	
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(orderIdLabel);
		centerPanel.add(custIdLabel);
		centerPanel.add(custNameLabel);
		centerPanel.add(qtyLabel);
		centerPanel.add(totalLabel);
		centerPanel.add(statusLabel);
		centerPanel.add(orderIdBox);
		centerPanel.add(custIdText);
		centerPanel.add(custNameText);
		centerPanel.add(qtyText);
		centerPanel.add(totalText);
		centerPanel.add(orderStatusBox);
		add(centerPanel);	
		
		JButton updateOrderBtn=new JButton("Update Order");
		updateOrderBtn.setEnabled(false);
		updateOrderBtn.setFont(new Font("",1,20));
			
		JButton cancelBtn=new JButton("Cancel");
		cancelBtn.setFont(new Font("",1,20));
		
		JButton backBtn=new JButton("Back to Main Menu");
		backBtn.setFont(new Font("",1,20));
		backBtn.setPreferredSize(new Dimension(265,40));
		
		JPanel btnPanel1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel1.setBackground(Color.WHITE);
		btnPanel1.add(updateOrderBtn);
		btnPanel1.add(cancelBtn);
		
		JPanel btnPanel2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel2.setBackground(Color.WHITE);
		btnPanel2.add(backBtn);
		
		JPanel southPanel=new JPanel(new GridLayout(2,1));
		southPanel.add(btnPanel1);
		southPanel.add(btnPanel2);
		add("South",southPanel);
		
		orderIdBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				for(int i=0;i<orderList.size();i++){
					if(orderIdBox.getSelectedItem().equals(orderList.getOrderId(i))){
						custIdText.setText(null);
						custNameText.setText(null);
						qtyText.setText(null);
						totalText.setText(null);
						qtyText.setEditable(false);
						orderStatusBox.setEnabled(false);
						updateOrderBtn.setEnabled(false);
						
						if(orderList.getStatus(i)==0){
							JDialog dialog=new JDialog(new JFrame(),"Message",true);
							dialog.setSize(350,130);
							JLabel message=new JLabel("This order has already cancelled.");
							message.setHorizontalAlignment(JLabel.CENTER);
							message.setFont(new Font("",0,18));
							JPanel centerPanel=new JPanel();
							centerPanel.add(message);
							centerPanel.setBackground(Color.WHITE);
							dialog.add(centerPanel);
							
							JButton okBtn=new JButton("Ok");
							okBtn.setFont(new Font("",1,18));
							
							ImageIcon img=new ImageIcon("success.jpeg");	
							img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
							JLabel imgLabel = new JLabel(img);
							dialog.add("West",imgLabel);
		
							JPanel panel=new JPanel(new FlowLayout());
							panel.setBackground(Color.WHITE);
							panel.add(okBtn);
							dialog.add("South",panel);
		
							okBtn.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent evt){
									dialog.dispose();
									return;
								}
							});
							
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);	

						}else if(orderList.getStatus(i)==2){
							JDialog dialog=new JDialog(new JFrame(),"Message",true);
							dialog.setSize(350,130);
							JLabel message=new JLabel("This order has already delivered.");
							message.setHorizontalAlignment(JLabel.CENTER);
							message.setFont(new Font("",0,18));
							JPanel centerPanel=new JPanel();
							centerPanel.add(message);
							centerPanel.setBackground(Color.WHITE);
							dialog.add(centerPanel);;
							
							JButton okBtn=new JButton("Ok");
							okBtn.setFont(new Font("",1,18));
							
							ImageIcon img=new ImageIcon("success.jpeg");	
							img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
							JLabel imgLabel = new JLabel(img);
							dialog.add("West",imgLabel);
		
							JPanel panel=new JPanel(new FlowLayout());
							panel.setBackground(Color.WHITE);
							panel.add(okBtn);
							dialog.add("South",panel);
							
							okBtn.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent evt){
									dialog.dispose();
									return;
								}
							});
							
							dialog.setLocationRelativeTo(null);
							dialog.setVisible(true);
							return;
						}else{	
							custIdText.setText(orderList.getCustomerId(i));	
							
							for(int j=0;j<customerList.size();j++){
								if(orderList.getCustomerId(i).equals(customerList.getCustomerId(j))){
									custNameText.setText(customerList.getCustomerName(j));
									break;
								}
							}
							
							qtyText.setText(Integer.toString(orderList.getQuantity(i)));
							qtyText.setEditable(true);
							qtyText.requestFocus();
							totalText.setText(Integer.toString(orderList.getQuantity(i)*BURGERPRICE)+".00");
							orderStatusBox.setEnabled(true);
							updateOrderBtn.setEnabled(true);
							return;
						}	
				
					}	
				}	
			}
		});
		
		qtyText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){		
				totalText.setText(Integer.toString(Integer.parseInt(qtyText.getText())*BURGERPRICE));
			}
		});
		
		updateOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				for(int i=0;i<orderList.size();i++){
					if(orderIdBox.getSelectedItem().equals(orderList.getOrderId(i))){
						orderList.setQuantity(i,Integer.parseInt(qtyText.getText()));
						
						int status;			
						if(orderStatusBox.getSelectedItem().equals("Preparing")){
							status=1;
						}else if(orderStatusBox.getSelectedItem().equals("Cancel")){
							status=0;
						}else{
							status=2;
						}		
						orderList.setStatus(i,status);	
						
						JDialog dialog=new JDialog(new JFrame(),"success",true);
						dialog.setSize(350,130);
						JLabel message=new JLabel("Order has updated successfully.");
						message.setHorizontalAlignment(JLabel.CENTER);
						message.setFont(new Font("",0,18));
						JPanel centerPanel=new JPanel();
						centerPanel.add(message);
						centerPanel.setBackground(Color.WHITE);
						dialog.add(centerPanel);;
						
						JButton okBtn=new JButton("Ok");
						okBtn.setFont(new Font("",1,18));
						
						ImageIcon img=new ImageIcon("success.jpeg");	
						img.setImage(img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));	
						JLabel imgLabel = new JLabel(img);
						dialog.add("West",imgLabel);
		
						JPanel panel=new JPanel(new FlowLayout());
						panel.setBackground(Color.WHITE);
						panel.add(okBtn);
						dialog.add("South",panel);
						
						okBtn.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent evt){
								dialog.dispose();
								return;
							}
						});
						
						dialog.setLocationRelativeTo(null);
						dialog.setVisible(true);
						
						custIdText.setText(null);
						custNameText.setText(null);
						qtyText.setText(null);
						totalText.setText(null);
						qtyText.setEditable(false);
						orderStatusBox.setEnabled(false);
						updateOrderBtn.setEnabled(false);
						return;
					}
				}			
			}
		});
			
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){		
				custIdText.setText(null);
				custNameText.setText(null);
				qtyText.setText(null);
				totalText.setText(null);
				qtyText.setEditable(false);
				orderStatusBox.setEnabled(false);
				updateOrderBtn.setEnabled(false);
			}
		});
			
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				remove(southPanel);
				repaint();
				homepage();	
			}
		});
		
	}	
	
	public void viewOrders(){
		setTitle("View Orders");
		setSize(510,450);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("View Orders");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		JButton viewAllOrdersBtn=new JButton("View All Orders");
		viewAllOrdersBtn.setFont(new Font("",0,20));
		viewAllOrdersBtn.setBounds(70,60,350,45);
		
		JButton viewAllCustomersBtn=new JButton("View All Customers");
		viewAllCustomersBtn.setFont(new Font("",0,20));
		viewAllCustomersBtn.setBounds(70,130,350,45);
		
		JButton viewOrderBtn=new JButton("View Order");
		viewOrderBtn.setFont(new Font("",0,20));
		viewOrderBtn.setBounds(70,200,350,45);
		
		JButton cancelBtn=new JButton("Cancel");
		cancelBtn.setFont(new Font("",1,20));
		cancelBtn.setBounds(320,290,130,35);
			
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(viewAllOrdersBtn);
		centerPanel.add(viewAllCustomersBtn);
		centerPanel.add(viewOrderBtn);
		centerPanel.add(cancelBtn);
		add(centerPanel);
			
		viewAllOrdersBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){		
				remove(northPanel);
				remove(centerPanel);
				repaint();
				viewAllOrders();
			}
		});
		
		viewOrderBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){		
				remove(northPanel);
				remove(centerPanel);
				repaint();
				viewOrder();
			}
		});
		
		viewAllCustomersBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){		
				remove(northPanel);
				remove(centerPanel);
				repaint();
				viewAllCustomers();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				repaint();
				homepage();	
			}
		});
				
	}
	
	public void viewAllOrders(){
		setTitle("View All Orders");
		setSize(610,450);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("View All Orders");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		String[] columnNames={"Order ID","Customer ID","Quantity","Order Status"};
		DefaultTableModel dtm=new DefaultTableModel(columnNames,0);
		
		for(int i=0; i<orderList.size(); i++){
			String status;
			if(orderList.getStatus(i)==0){
				status="Cancelled";
			}else if(orderList.getStatus(i)==1){
				status="Preparing";
			}else{
				status="Delivered";
			}					
			Object[] rowData={orderList.getOrderId(i),orderList.getCustomerId(i),orderList.getQuantity(i),status};
			dtm.addRow(rowData);  		
		}	
		  
		JTable orderTablel=new JTable(dtm);
		orderTablel.setFont(new Font("",0,17));
		orderTablel.setRowHeight(30);
		
		JScrollPane tablePane=new JScrollPane(orderTablel);
		tablePane.setBackground(Color.WHITE);
		tablePane.setBounds(40,40,520,250);
		
		JTableHeader header=orderTablel.getTableHeader();
		header.setBackground(Color.GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("",1,19));	
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(tablePane);
		add(centerPanel);
		
		JButton backBtn=new JButton("Back To Home");
		backBtn.setFont(new Font("",1,20));
		
		JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(Color.WHITE);
		southPanel.add(backBtn);
		add("South",southPanel);
		
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				remove(southPanel);
				repaint();
				homepage();	
			}
		});
		
	}	
	
	public void viewAllCustomers(){
		setTitle("View All Customers");
		setSize(610,450);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("View All Customers");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		String[] columnNames={"Customer ID","Name","Total Value"};
		DefaultTableModel dtm=new DefaultTableModel(columnNames,0);
		
		for(int i=0; i<customerList.size(); i++){
			int total=0;
			for(int j=0; j<orderList.size(); j++){
				if(customerList.getCustomerId(i).equals(orderList.getCustomerId(j)) && orderList.getStatus(j)!=0){
						total+=orderList.getQuantity(j)*BURGERPRICE;
				}
			}
			Object[] rowData={customerList.getCustomerId(i),customerList.getCustomerName(i),total+".00"};	
			dtm.addRow(rowData);		
		}	
			
		JTable customerTablel=new JTable(dtm);
		customerTablel.setFont(new Font("",0,17));
		customerTablel.setRowHeight(30);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		customerTablel.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		
		JScrollPane tablePane=new JScrollPane(customerTablel);
		tablePane.setBackground(Color.WHITE);
		tablePane.setBounds(40,40,520,250);
		
		JTableHeader header=customerTablel.getTableHeader();
		header.setBackground(Color.GRAY);
		header.setForeground(Color.WHITE);
		header.setFont(new Font("",1,19));	
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(tablePane);
		add(centerPanel);
		
		JButton backBtn=new JButton("Back To Home");
		backBtn.setFont(new Font("",1,20));
		
		JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.setBackground(Color.WHITE);
		southPanel.add(backBtn);
		add("South",southPanel);
		
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				remove(southPanel);
				repaint();
				homepage();	
			}
		});	
	
	}
	
	public void viewOrder(){
		setTitle("View Order Details");
		setSize(480,450);
		setLocationRelativeTo(null);
		
		JLabel heading=new JLabel("View Order Details");
		heading.setFont(new Font("",1,35));
		
		JPanel northPanel=new JPanel(new FlowLayout());
		northPanel.add(heading);
		northPanel.setBackground(new Color(153, 204, 255));
		add("North",northPanel);
		
		JLabel orderIdLabel=new JLabel("Order ID");
		orderIdLabel.setFont(new Font("",1,18));
		orderIdLabel.setBounds(40,30,200,25);
		
		JLabel custIdLabel=new JLabel("Customer ID");
		custIdLabel.setFont(new Font("",1,18));
		custIdLabel.setBounds(40,70,200,25);
		
		JLabel custNameLabel=new JLabel("Customer Name");
		custNameLabel.setFont(new Font("",1,18));
		custNameLabel.setBounds(40,110,200,25);
		
		JLabel qtyLabel=new JLabel("Burger Quantity");
		qtyLabel.setFont(new Font("",1,18));
		qtyLabel.setBounds(40,150,200,25);
		
		JLabel totalLabel=new JLabel("Bill Value");
		totalLabel.setFont(new Font("",1,18));
		totalLabel.setBounds(40,190,200,25);
		
		JLabel statusLabel=new JLabel("Order Status");
		statusLabel.setFont(new Font("",1,18));
		statusLabel.setBounds(40,230,200,25);	
		
		JComboBox orderIdBox=new JComboBox();
		for(int i=0; i<orderList.size(); i++){
			orderIdBox.addItem(orderList.getOrderId(i));
		}
		orderIdBox.setFont(new Font("",1,18));
		orderIdBox.setBounds(230,28,90,25);	
		orderIdBox.setBackground(Color.WHITE);
		
		JTextField custIdText=new JTextField();
		custIdText.setFont(new Font("",0,17));
		custIdText.setBounds(230,69,110,25);	
		custIdText.setEditable(false);
		custIdText.setBackground(Color.WHITE);
		
		JTextField custNameText=new JTextField();
		custNameText.setFont(new Font("",0,17));
		custNameText.setBounds(230,110,180,25);	
		custNameText.setEditable(false);
		custNameText.setBackground(Color.WHITE);
		
		JTextField qtyText=new JTextField();
		qtyText.setFont(new Font("",0,17));
		qtyText.setBounds(230,152,70,25);	
		qtyText.setEditable(false);
		qtyText.setBackground(Color.WHITE);
		
		JTextField totalText=new JTextField();
		totalText.setFont(new Font("",0,17));
		totalText.setBounds(230,191,100,25);
		totalText.setEditable(false);
		totalText.setBackground(Color.WHITE);
			
		JTextField statusText=new JTextField();
		statusText.setFont(new Font("",0,17));
		statusText.setBounds(230,230,80,25);
		statusText.setEditable(false);
		statusText.setBackground(Color.WHITE);
		
		JButton backBtn=new JButton("Back To Home");
		backBtn.setFont(new Font("",1,18));
		backBtn.setBounds(270,290,170,25);	
		
		JPanel centerPanel=new JPanel(null);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.add(orderIdLabel);
		centerPanel.add(custIdLabel);
		centerPanel.add(custNameLabel);
		centerPanel.add(qtyLabel);
		centerPanel.add(totalLabel);
		centerPanel.add(statusLabel);
		centerPanel.add(orderIdBox);
		centerPanel.add(custIdText);
		centerPanel.add(custNameText);
		centerPanel.add(qtyText);
		centerPanel.add(totalText);
		centerPanel.add(statusText);
		centerPanel.add(backBtn);
		add(centerPanel);
		
		orderIdBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				for(int i=0;i<orderList.size();i++){
					if(orderIdBox.getSelectedItem().equals(orderList.getOrderId(i))){
						custIdText.setText(orderList.getCustomerId(i));	
			
						for(int j=0;j<customerList.size();j++){
							if(customerList.getCustomerId(j).equals(orderList.getCustomerId(i))){
								custNameText.setText(customerList.getCustomerName(j));
								break;
							}
						}
						
						qtyText.setText(Integer.toString(orderList.getQuantity(i)));
						totalText.setText(Integer.toString(orderList.getQuantity(i)*BURGERPRICE)+".00");
							
						if(orderList.getStatus(i)==0){
							statusText.setText("Cancelled");
						}else if(orderList.getStatus(i)==1){	
							statusText.setText("Preparing");
						}else{
							statusText.setText("Delivered");
						}
						return;
					}	
				}	
			}
		});
		
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				remove(northPanel);
				remove(centerPanel);
				repaint();
				homepage();	
			}
		});
		
	}	
	
		
}
