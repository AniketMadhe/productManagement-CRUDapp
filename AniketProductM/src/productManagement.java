import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class productManagement {
	JFrame window;
	Container cont;
	JPanel textPanel,fieldPanel,buttonPanel;
	JLabel titleLabel;
	JLabel idLabel,nameLabel,priceLabel,quantityLabel;
	JTextField productIDField,productNameField,productPriceField,productQtyField;
	JButton addButton,updateButton,deleteButton,searchButton;
	
	Font titleFont=new Font("Times New Roman",Font.BOLD,40);
	Font textFont=new Font("Serif",Font.PLAIN,30);
	Font FieldFont=new Font("Times New Roman",Font.PLAIN,30);
	Font buttonFont=new Font("normal font",Font.PLAIN,30);
	
	
	
	
	
	

	public static void main(String[] args) {
		
		
		new productManagement();
		
	}
	
public productManagement() {
		window = new JFrame("Product Management Application"); 
		window.setSize(800,600);
		window.getContentPane().setBackground(null);
		cont=window.getContentPane();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		titleLabel=new JLabel("Product Management");
		titleLabel.setBounds(200,15,400,50);
		titleLabel.setForeground(Color.blue);
		titleLabel.setFont(titleFont);
		cont.add(titleLabel);
		
		//TextLabel panel
		
		textPanel=new JPanel();
		textPanel.setBounds(28,100,280,280);
		textPanel.setBackground(Color.LIGHT_GRAY);
		textPanel.setLayout(new GridLayout(4,1));
		cont.add(textPanel);
		
		//TextLabel
		
		idLabel=new JLabel("ID:");
		idLabel.setBackground(Color.LIGHT_GRAY);
		idLabel.setForeground(Color.black);
		idLabel.setFont(textFont);
		textPanel.add(idLabel);
		
		nameLabel=new JLabel("Name:");
		nameLabel.setBackground(Color.LIGHT_GRAY);
		nameLabel.setForeground(Color.black);
		nameLabel.setFont(textFont);
		textPanel.add(nameLabel);
		
		priceLabel=new JLabel("Price:");
		priceLabel.setBackground(Color.LIGHT_GRAY);
		priceLabel.setForeground(Color.black);
		priceLabel.setFont(textFont);
		textPanel.add(priceLabel);
		
		quantityLabel=new JLabel("Quantity:");
		quantityLabel.setBackground(Color.LIGHT_GRAY);
		quantityLabel.setForeground(Color.black);
		quantityLabel.setFont(textFont);
		textPanel.add(quantityLabel);
		
		
		
		fieldPanel=new JPanel();
		fieldPanel.setBounds(355,100,380,280);
		fieldPanel.setBackground(Color.white);
		fieldPanel.setLayout(new GridLayout(4,1));
		cont.add(fieldPanel);
		
		//JTextFields
		
		productIDField=new JTextField();
		productIDField.setBackground(Color.white);
		productIDField.setForeground(Color.black);
		productIDField.setFont(FieldFont);
		fieldPanel.add(productIDField);
		
		productNameField=new JTextField();
		productNameField.setBackground(Color.white);
		productNameField.setForeground(Color.black);
		productNameField.setFont(FieldFont);
		fieldPanel.add(productNameField);
		
		productPriceField=new JTextField();
		productPriceField.setBackground(Color.white);
		productPriceField.setForeground(Color.black);
		productPriceField.setFont(FieldFont);
		fieldPanel.add(productPriceField);
		
		productQtyField=new JTextField();
		productQtyField.setBackground(Color.white);
		productQtyField.setForeground(Color.black);
		productQtyField.setFont(FieldFont);
		fieldPanel.add(productQtyField);
		
		//ButtonPanel
		
		buttonPanel=new JPanel();
		buttonPanel.setBounds(370,415,355,110);
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new GridLayout(2,2));
		cont.add(buttonPanel);
		
		//JButtons
		
		addButton=new JButton("Add");
		addButton.setBackground(Color.blue);
		addButton.setForeground(Color.white);
		addButton.setFont(buttonFont);
		buttonPanel.add(addButton);
		
		updateButton=new JButton("Update");
		updateButton.setBackground(Color.blue);
		updateButton.setForeground(Color.white);
		updateButton.setFont(buttonFont);
		buttonPanel.add(updateButton);
		
		deleteButton=new JButton("Delete");
		deleteButton.setBackground(Color.blue);
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(buttonFont);
		buttonPanel.add(deleteButton);
		
		searchButton=new JButton("Search");
		searchButton.setBackground(Color.blue);
		searchButton.setForeground(Color.white);
		searchButton.setFont(buttonFont);
		buttonPanel.add(searchButton);
		
		
		ButtonHandler handleEvent = new ButtonHandler(productIDField, productNameField, productPriceField, productQtyField,
                addButton, updateButton, deleteButton, searchButton);
        addButton.addActionListener(handleEvent);
        updateButton.addActionListener(handleEvent);
        deleteButton.addActionListener(handleEvent);
        searchButton.addActionListener(handleEvent);

		window.setVisible(true);
		
		
		
	}
}
class ButtonHandler implements ActionListener{
	
	JTextField id,name,price,quantity;
	JButton add,update,delete,search;
	
	public static Connection con;
	public static PreparedStatement statement;
	
	public ButtonHandler(JTextField id,JTextField name,JTextField price,JTextField quantity,JButton add,JButton update,JButton delete,JButton search) {
		
		this.id=id;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
		this.add=add;
		this.update=update;
		this.delete=delete;
		this.search=search;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
            connect();
            String productID = id.getText();
            String productName = name.getText();
            String productPrice = price.getText();
            String productQty = quantity.getText();

            try {
                statement = con.prepareStatement(
                        "insert into products(productID, productName, productPrice, productQty)values(?,?,?, ?)");
                int proID = Integer.parseInt(productID);
                statement.setInt(1, proID);
                statement.setString(2, productName);
                int proPrice = Integer.parseInt(productPrice);
                statement.setInt(3, proPrice);
                int proQuantity = Integer.parseInt(productQty);
                statement.setInt(4, proQuantity);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Successfully Added", "Record Added",
                        JOptionPane.INFORMATION_MESSAGE);

                id.setText(" ");
                name.setText(" ");
                price.setText(" ");
                quantity.setText(" ");

                con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		if (e.getSource() == update) {
            connect();
            String productID = id.getText();
            String ProductName = name.getText();
            String productPrice = price.getText();
            String productQty = quantity.getText();

            try {
                statement = con.prepareStatement(
                        "update products set productID = ?, productName = ?, productPrice = ?, productQty = ? where productID = ?");
                int proID = Integer.parseInt(productID);
                statement.setInt(1, proID);
                statement.setString(2, ProductName);
                int proPrice = Integer.parseInt(productPrice);
                statement.setInt(3, proPrice);
                int proQuantity = Integer.parseInt(productQty);
                statement.setInt(4, proQuantity);
                statement.setInt(5, proID);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Successfully Updated", "Record Updated",
                        JOptionPane.INFORMATION_MESSAGE);

                id.setText(" ");
                name.setText(" ");
                price.setText(" ");
                quantity.setText(" ");

                con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		if (e.getSource() == delete) {
            connect();
            String productID = id.getText();

            try {
                statement = con.prepareStatement("delete from products where productID = ?");
                int proID = Integer.parseInt(productID);
                statement.setInt(1, proID);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Successfully Deleted", "Record Deleted",
                        JOptionPane.INFORMATION_MESSAGE);

                id.setText(" ");
                name.setText(" ");
                price.setText(" ");
                quantity.setText(" ");

                con.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
		 if (e.getSource() == search) {
	            connect();
	            String productID = id.getText();

	            try {
	                statement = con.prepareStatement(
	                        "select productName, productPrice, productQty from products where productID = ?");
	                int proID = Integer.parseInt(productID);
	                statement.setInt(1, proID);
	                ResultSet rs = statement.executeQuery();

	                if (rs.next() == true) {
	                    String proName = rs.getString(1);
	                    String proPrice = rs.getString(2);
	                    String proQty = rs.getString(3);

	                    name.setText(proName);
	                    price.setText(proPrice);
	                    quantity.setText(proQty);
	                } else {
	                    name.setText(" ");
	                    price.setText(" ");
	                    quantity.setText(" ");
	                    JOptionPane.showMessageDialog(null, "Invalid Product ID", "Error", JOptionPane.ERROR_MESSAGE);
	                }

	                con.close();

	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	}
				
		
		public void connect() {
			
			String URL="jdbc:mysql://localhost:3306/aniket";
			String username="root";
			String password="@Aniket@113@";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(URL,username,password);
				
				System.out.println("Connected to Database");
				
			} catch(SQLException e) {
				e.printStackTrace();
			} catch(ClassNotFoundException e) {
				
				e.printStackTrace(); 
				
			}
		 }
	}
	
	


	
   






	
	




