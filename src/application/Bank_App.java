package application;

import java.io.FileInputStream;



import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.protocol.Resultset;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Bank_App extends Application
{
	Stage loginstage,userstage,adminstage;//here i declared some stages,that can show and close using some functions or buttons, inside this application extended class I create another subclass for different stages, where i create method with return type of stage, i can create object of this class and can use this stage anywhere
	@Override
	public void start(Stage stage) throws Exception {
		
		new loinpage().showlogin().show();//stage is loginstage which is return by showlogin() method, i develop this in class of loginpage and accessing here and showing this, when app will open, this page will show
	}
	
	//creating login page
class loinpage
{
	//here are some variables used in this class at many places, when user will login then that id will use as a uid at many place to retrive data of that user only.
	Integer uid; String name;
	public Stage showlogin() throws FileNotFoundException
	{
		
		loginstage=new Stage();//declaring login stage here
		StackPane loginpane=new StackPane();//pane to add all components of this page and then create scene of this pane
		Label login,id,password;
		Button userb,adminb;
		TextField idtf,passwordtf;
		
		Connection con=new jdbcc().contojdbc();//already i connected with sql in another class, retriving that connection here by creating new object and accessing method
		
		login=new Label("Log In here");//some labels
		id=new Label("ID");
		password=new Label("Password");
		
		//setting width so alignment will proper
		id.setPrefWidth(80);
		password.setPrefWidth(80);
		//style of labels
		Font labelf=new Font("Calibri", 15);
		Font loginfont=Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20);
		
		login.setFont(loginfont);
		login.setTextFill(Color.WHITE);
		id.setFont(labelf);
		id.setTextFill(Color.WHITE);
		password.setFont(labelf);
		password.setTextFill(Color.WHITE);
		
		//buttons used in login page
		userb=new Button("user login");//to login as user and its set as default
		userb.setDefaultButton(true);
		adminb=new Button("Admin login");//to login as a admin
		
		idtf=new TextField();//textfield it get id from user
		idtf.setPrefColumnCount(15);
		
		passwordtf=new TextField();//textfield to get password from user
		passwordtf.setPrefColumnCount(15);
		
		//used hbox and added required field,so it will align horizonatlly in one line
		HBox hb1=new HBox();
		hb1.getChildren().addAll(id,idtf);
		hb1.setSpacing(45);
		hb1.setAlignment(Pos.CENTER);
		hb1.setSpacing(10);
		
		
		HBox hb2=new HBox();
		hb2.getChildren().addAll(password,passwordtf);
		hb2.setAlignment(Pos.CENTER);
		hb2.setSpacing(10);
		
		HBox hb3=new HBox();
		hb3.getChildren().addAll(userb,adminb);
		hb3.setAlignment(Pos.CENTER);
		hb3.setSpacing(10);
		hb3.setSpacing(10);
		
		//backgroun of login page
		Image img = new Image(new FileInputStream("E:\\kerala\\IMG20230218172251.jpg"));
		BackgroundImage bim = new BackgroundImage(img, null, null, null, null);//setting background image
        Background bi = new Background(bim);//setting background and passing bg image
        
        ImageView imgview=new ImageView(img);//creating imageview of that image to apply addition custmization
        imgview.fitHeightProperty().bind(loginpane.heightProperty());
        imgview.fitWidthProperty().bind(loginpane.widthProperty());;//setting size of image as per size of pane,we can add stage too instead of pane.
		
		
		//creating vbox and adding all nodes
		VBox vb1=new VBox();
		vb1.setAlignment(Pos.CENTER);
		vb1.setMaxHeight(200);
		vb1.setMaxWidth(300);//to give proper size to vbox, otherwise it will take all stage size
		vb1.getChildren().addAll(login,hb1,hb2,hb3);//adding all components to vbox
		vb1.setSpacing(15);
		
		//border for vb1
		Border bd=new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, null,new BorderWidths(1)));
		vb1.setBorder(bd);
		  // Set background color
		Color bgcolor=Color.web("Black",0.5);// Use your preferred color and opacity
		//below code its applying to vb1 and for bg only
		vb1.setStyle("-fx-background-color: rgba("
                + (int) (bgcolor.getRed() * 255) + ","
                + (int) (bgcolor.getGreen() * 255) + ","
                + (int) (bgcolor.getBlue() * 255) + ","
                + bgcolor.getOpacity() + ");");//

        
		
		
		loginpane.getChildren().addAll(imgview,vb1);//adding imageview and vb1
		loginpane.setBackground(bi);//setting background of loginpane , imageview property will automatiaclly apply on image
		
		//action of userb button
		//idtf,passwordtf textfields
		
		//Credentials:
		//ID of user is name+id
		//eg Satish3 . satish first name . 3 is id
		//password is first name , eg satish
		
		//for admin credentials
		//id and password is admin
		
		userb.setOnAction(Event->{
			String tempname=idtf.getText();
			String temppass=passwordtf.getText();
			System.out.println(temppass);
			
			  //for extracting id (extracting number from String) consider satish3 is tempname or id provided by user in id field of login page
			Pattern pattern=Pattern.compile("\\d+");  //creating patteren and add condition, here "\\d+" is checking for digits ,+ is for multiple digits
			Matcher matcher=pattern.matcher(tempname); //matcher is used to match this condition with tempname string
	
			while(matcher.find()) //used matcher to get digits
			{
				String number=matcher.group(); //whenever matcher will match with pattern , a group of digits will save as string in number
				 uid=Integer.parseInt(number); //convert into Integer and save into uid. now this is our id of user which is 3 from satish3 tempname
				 System.out.println(uid);
				
			}
			  //to extract first name from tempname (satish3)
			Pattern pattern2=Pattern.compile("[a-zA-Z]+"); //will check char from a toz or A to Z + sign is for multiple char
			Matcher matcher2=pattern2.matcher(tempname);
			while(matcher2.find())
			{
				 name=(matcher2.group()).toLowerCase();//converting to lowrcase to match with name from database . o/p will satish if tempname is satish3
				 System.out.println(name);
			}
			try {
			Statement checkname=con.createStatement();
			ResultSet rs=checkname.executeQuery("SELECT name,user_password FROM userdata WHERE id="+uid);
			if(rs.next())
			{
				String sqlname=(rs.getString(1)).toLowerCase();//getting name from sql db and converting to lowercase to match both name and this sqlname
				String sqlpassword=rs.getString(2);//getting password from DB
				System.out.println(sqlname);
				if(sqlname.startsWith(name) && sqlpassword.equals(temppass) )
				{
					new userpage().upage().show();
					loginstage.close();
					//if condition matched then open userpage and close loginpage
				}
				else
				{
					Alert nouser=new Alert(AlertType.ERROR,"User not found");
					nouser.show();
					//if condition not matched then give error message
				}
			}
			}
			catch (SQLException e) {
				// TODO: handle exception
			}
			
			
		});
		//action of adminb button
		adminb.setOnAction(Even->{
			
			if(idtf.getText().equals("admin") && passwordtf.getText().equals("admin")) {
			new adminpage().adpage().show();
			loginstage.close();
			//cecking for admin credentials
			}
			else {
				Alert nouser=new Alert(AlertType.ERROR,"Admin details are not matched");
				nouser.show();
			}
			
			
		});
		Scene scene=new Scene(loginpane,500,500);//creating scene adding loginpane with size
		loginstage.setScene(scene);//setting scene withstage
		return loginstage; //returning stage
	}
	
	class userpage 
	{
		public Stage upage()
		{
			userstage =new Stage();
			StackPane pane1=new StackPane();
			StackPane pane2=new StackPane();
			StackPane pane3=new StackPane();
			Connection con=new jdbcc().contojdbc();//jdbc connection
			
			Label welcome,address,id,balance,lastcredit,lastdebit,creditdate,debitdate;//declaring fixed labels
			Label uname,uaddress,uuid,ubalance,ulastcredit,ulastdebit,ucreditdate,udebitdate;//declaring dynamic labels
			
			TabPane tp=new TabPane();//creating tabpane, where i can multiple tabs and can switch as buttons
			
			Tab acc=new Tab("Account ");
			Tab credit=new Tab("Credit Money");
			Tab debit=new Tab("Debit Money");//these are 3 tabs
			
			Label creditlabel;
			TextField credittf;
			Button creditb;
			
			Label debitlabel;
			TextField debittf;
			Button debitb;
			
			//main page with details
			
			//fix labels
			welcome=new Label("Welcome "+name+" to Bank of Satish");//welcome message with user name
			welcome.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
			welcome.setTextFill(Color.BLUE);
			
			Font labelfont=Font.font("calibri", FontWeight.BOLD, 15);//font for all lables of user main page
			Label usname=new Label("Name :");
			usname.setFont(labelfont);
			id=new Label("Bank ID: ");
			id.setFont(labelfont);
			address=new Label("Address: ");
			address.setFont(labelfont);
			balance=new Label("Balance: ");
			balance.setFont(labelfont);
			lastcredit=new Label("Last credited Ammount: ");
			lastcredit.setFont(labelfont);
			lastdebit=new Label("Last debited Ammount: ");
			lastdebit.setFont(labelfont);
			creditdate=new Label("Credited On: ");
			creditdate.setFont(labelfont);
			debitdate=new Label("Debited On:");
			debitdate.setFont(labelfont);
			
			//dynamic labels
			
			uname=new Label();
			
			uuid=new Label();
			
			uaddress=new Label();
			
			ubalance=new Label();
			
			ulastcredit=new Label();
			
			ulastdebit=new Label();
			ucreditdate=new Label();
			udebitdate=new Label();
			
			
			HBox hb1=new HBox();
			hb1.getChildren().addAll(usname,uname);
			HBox hb2=new HBox();
			hb2.getChildren().addAll(id,uuid);
			HBox hb3=new HBox();
			hb3.getChildren().addAll(address,uaddress);
			HBox hb4=new HBox();
			hb4.getChildren().addAll(balance,ubalance);
			HBox hb5=new HBox();
			hb5.getChildren().addAll(lastcredit,ulastcredit);
			HBox hb6=new HBox();
			hb6.getChildren().addAll(lastdebit,ulastdebit);
			HBox hb7=new HBox();
			hb7.getChildren().addAll(creditdate,ucreditdate);
			HBox hb8=new HBox();
			hb8.getChildren().addAll(debitdate,udebitdate);
			
			//creating log out button
			Button lo=new Button("Log out");
		     lo.setOnAction(Event->{
		    	 try {
					new loinpage().showlogin().show();
					userstage.close();
					//if log out button pressed then lohinpane stage will open and current page stage will close
				} catch (FileNotFoundException e) {
					
					System.out.println(e+" while log out");
				}
		     });
			
		     //creating change password button and fields
		     Button cp=new Button("Change Password");
		     Button setpass=new Button("Set password");
		     CheckBox togglepassword=new CheckBox();//checkbox to show/hide password
		     togglepassword.setSelected(false);
		     
		     Label setp=new Label("Set New Password");
		     Label verifyp=new Label("Enter again password");
		     Label togglep=new Label("Show Password");
		     Label enteredpd=new Label("Entered Password");
		     
		     PasswordField setnewp=new PasswordField();
		     setnewp.setPrefColumnCount(15);
		     
		     
		     PasswordField verpassf=new PasswordField();//passwordfield is used to hide text, 
		     verpassf.setPrefColumnCount(15);
		     
		     //visible password
		     TextField showpd=new TextField();
		     showpd.setPrefColumnCount(10);
		     
		     Label showpd2=new Label();//msg of passowrd matching or not
		     
		   
		     
		     
		     //hbox for checkbox and label
		     HBox checkb=new HBox();
		     checkb.setAlignment(Pos.CENTER);
		     checkb.getChildren().addAll(togglep,togglepassword);
		     checkb.setSpacing(15);
		     
		     //hbox for set new password with label
		     HBox entpass=new HBox();
		     entpass.getChildren().addAll(setp,setnewp);
		     entpass.setSpacing(15);

		     //hbox for verify new password with label
		     HBox verpass=new HBox();
		     verpass.getChildren().addAll(verifyp,verpassf,showpd2);
		     verpass.setSpacing(15);
		     
		     //entered password
		     HBox enteredpassword=new HBox();
		     enteredpassword.getChildren().addAll(enteredpd,showpd);
		     enteredpassword.setSpacing(15);
		     
		     //default making this hboxes and set password button as a invisible
		     checkb.setVisible(false);
		     entpass.setVisible(false);
    		 verpass.setVisible(false);
    		 setpass.setVisible(false);
    		 enteredpassword.setVisible(false);
    		 
    		 //when we will type something for each typed key event will generate and will check below condition
    		 verpassf.setOnKeyTyped(even->{
		    		 if((verpassf.getText()).equals(setnewp.getText()) && !verpassf.getText().equals(""))
		    		
		    		 {
		    			 showpd2.setText("Password is matching");
						 showpd2.setTextFill(Color.GREEN);
						 checkb.setVisible(true);
						 setpass.setVisible(true);
						//when condn will match then it will visible , label will set as above in green text
		    		 }
		    	 else
    					 {
    						 showpd2.setText("Password is not matching");
    						 showpd2.setTextFill(Color.RED);
    						 checkb.setVisible(false);
    						 setpass.setVisible(false);
    					 }
    		 });
    		 
    		 
		     //action of checkbox to hide and show password
		     togglepassword.setOnAction(event->{
		    	 if(togglepassword.isSelected())
		    	 {
		    		 if((verpassf.getText()).equals(setnewp.getText()))
		    		
		    		 {
		    			 showpd.setText(verpassf.getText());
		    		 }
		    		
		    		 
		    		enteredpassword.setVisible(true);
		    		 togglep.setText("Password showing");
		    	 }
		    	 else
		    	 {
		    		 
		    		 enteredpassword.setVisible(false);
		    		 showpd.clear();
		    		 togglep.setText("Password Hiding");
		    		 
		    	 }
		    	 
		     });
		     
		     //making hbox of chnage password visible or invisible, based on changed password button
		     cp.setOnAction(event->{
		    	 if(entpass.isVisible() && verpass.isVisible())
		    	 {
		    		 
		    		 entpass.setVisible(false);
		    		 verpass.setVisible(false);
		    		 
		    		 cp.setText("Change Password");
		    	 }
		    	 else
		    	 {
		    		
		    		 entpass.setVisible(true);
		    		 verpass.setVisible(true);
		    		 
		    		 cp.setText("Cancle change Password");
		    	 }
		     });
		     
		     //
		     
		     //adding change password and logout in one hbox
		     HBox securityops=new HBox();
		     securityops.getChildren().addAll(cp,lo);
		     securityops.setSpacing(15);
		     
		     
		     //jdbc to save changed password
		     setpass.setOnAction(event->{
		    	 if((verpassf.getText()).equals(setnewp.getText()))
			    		
	    		 {
	    			 String localpass=verpassf.getText();
	    			 try {
						PreparedStatement setnewpass=con.prepareStatement("UPDATE userdata SET user_password=? WHERE id=?;");//query to update password
						setnewpass.setString(1, localpass);
						setnewpass.setInt(2, uid);
						int status=0;
						status=setnewpass.executeUpdate();
						if(status>0)//checking password changed or not
						{
							Alert pdsaved=new Alert(AlertType.INFORMATION,"Password Changed Scuccesfully");
							pdsaved.show();
						}
						else
						{
							Alert pdsaved=new Alert(AlertType.ERROR,"facing problem to change password");
							pdsaved.show();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		 }
		     });
		     
			VBox vb1=new VBox();
			vb1.getChildren().addAll(welcome,hb1,hb2,hb3,hb4,hb5,hb6,hb7,hb8,securityops,checkb,entpass,verpass,enteredpassword,setpass);//adding all hboxes and log out button
			vb1.setAlignment(Pos.CENTER);
			vb1.setSpacing(10);
			
			pane1.getChildren().add(vb1);
			
			
			
			
			//credit tab
			creditlabel=new Label("Enter money to credit");
			credittf=new TextField();
			credittf.setPrefColumnCount(15);
			creditb=new Button("Enter to credit");
			HBox chb1=new HBox();
			chb1.setAlignment(Pos.CENTER);
			chb1.getChildren().addAll(creditlabel,credittf);
			chb1.setSpacing(10);
			
			VBox cvb1=new VBox();
			cvb1.getChildren().addAll(chb1,creditb);
			cvb1.setAlignment(Pos.CENTER);
			cvb1.setSpacing(10);
			
			pane2.getChildren().add(cvb1);
			
			//credit money user page jdbc
			
			creditb.setOnAction(Event->{try {
				
				int tempbalance=0;
				int credmoney=Integer.parseInt(credittf.getText());//converted text of credit field into integer
				Statement getbalance=con.createStatement();
				ResultSet rs=getbalance.executeQuery("SELECT balance FROM userdata WHERE id="+uid+";");
				if(rs.next()) {tempbalance=rs.getInt(1);}else {Alert balerror=new Alert(AlertType.ERROR,"Facing problem in operation"); balerror.show();}
				tempbalance=tempbalance+credmoney;
				Statement cmny=con.createStatement();
				int status=cmny.executeUpdate("UPDATE userdata SET lastcredit="+credmoney+", creditdate=CURRENT_DATE,balance="+tempbalance+" WHERE id="+uid+";");//updating credit ammount and credit date
				if(status>0) {creditb.setTextFill(Color.GREEN);}else {creditb.setTextFill(Color.RED);}//if ops successful then button text will green else red
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQL Exception in user page while crediting money");
				creditb.setTextFill(Color.RED);//if ops failed button text will red
			}});
			
			
			
			//Debit tab
			debitlabel=new Label("Enter money to Debit");
			debittf=new TextField();
			debittf.setPrefColumnCount(15);
			debitb=new Button("Enter to Debit");
			HBox chb2=new HBox();
			chb2.getChildren().addAll(debitlabel,debittf);
			chb2.setSpacing(10);
			
			VBox cvb2=new VBox();
			cvb2.getChildren().addAll(chb2,debitb);
			cvb2.setAlignment(Pos.CENTER);
			cvb2.setSpacing(10);
			
			pane3.getChildren().add(cvb2);
			
           //debit money user page jdbc
			
			debitb.setOnAction(Event->{try {
				
				int tempbalance=0;
				int debmoney=Integer.parseInt(debittf.getText());//converted text of debit field into integer
				Statement getbalance=con.createStatement();
				ResultSet rs=getbalance.executeQuery("SELECT balance FROM userdata WHERE id="+uid+";");
				if(rs.next()) {tempbalance=rs.getInt(1);}else {Alert balerror=new Alert(AlertType.ERROR,"Facing problem in operation"); balerror.show();}
				tempbalance=tempbalance-debmoney;
				Statement cmny=con.createStatement();
				int status=cmny.executeUpdate("UPDATE userdata SET lastdebit="+debmoney+", debitdate=CURRENT_DATE,balance="+tempbalance+" WHERE id="+uid+";");//updating credit ammount and credit date
				if(status>0) {debitb.setTextFill(Color.GREEN);}else {debitb.setTextFill(Color.RED);}//if ops successful then button text will green else red
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQL Exception in user page while debiting money");
				creditb.setTextFill(Color.RED);//if ops failed button text will red
			}});
			
			
			//adding credit debit panel to tab and setting tab
			credit.setContent(pane2);
			debit.setContent(pane3);
			acc.setContent(pane1);
			tp.getTabs().addAll(acc,credit,debit);
			
			BorderPane bp=new BorderPane();
			bp.setTop(tp);
			
			//userpage jdbc
			//used labels uname,uaddress,uid,ubalance,ulastcredit,ulastdebit,ucreditdate,udebitdate;
			try {
				Statement upage=con.createStatement();
				ResultSet rs=upage.executeQuery("SELECT * FROM userdata WHERE id="+uid+";");
				while(rs.next())
				{
				
					uuid.setText(Integer.toString(rs.getInt(1)));
					uname.setText(rs.getString(2));
					uaddress.setText(rs.getString(3));
					ubalance.setText(Integer.toString(rs.getInt(4)));
					ulastcredit.setText(Integer.toString(rs.getInt(5)));
					ulastdebit.setText(Integer.toString(rs.getInt(6)));
					ucreditdate.setText(rs.getString(7));
					udebitdate.setText(rs.getString(8));
					//showing all data to user
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Scene scene=new Scene(bp,500,500);
			userstage.setScene(scene);
			
			return userstage;
		}
	}
	
	class adminpage
	{
		int countasid=0;
		
		 int idtoedit=0;//used in edit details section
		 String field;//used in edit details section
		 String editvalue;//in edit details section to use to pass value
		 int preindex=0;//it used in search feature of udata
		public Stage adpage()
		{
			adminstage=new Stage();
			BorderPane bp=new BorderPane();
			Connection con=new jdbcc().contojdbc();//connection 
			
			jdbcc data=new jdbcc();
			TabPane tb=new TabPane();
			Tab users,adduser,deleteuser,editdetails;
			
			//all userdata
			TextArea udata=new TextArea();
			
			ScrollPane scudata=new ScrollPane(udata);
			 // Bind TextArea's size to the Scene's size
	        udata.prefHeightProperty().bind(adminstage.heightProperty());
	        udata.prefWidthProperty().bind(adminstage.widthProperty());

	        // Bind ScrollPane's size to the Scene's size
	        scudata.prefHeightProperty().bind(adminstage.heightProperty());
	        scudata.prefWidthProperty().bind(adminstage.widthProperty());
	        
	        TextField searcht=new TextField();
	        Button searchb=new Button("Search");
	        Button Refresh=new Button("Refresh List");
	        Button lgot=new Button("Log Out");

	         //for admin , to show all users data at one place
	        //below is a heading
			String alluserdataheading=" ID "+" Name                            "+" Address                          "+" Balance                "+"LCA                 "+ " LDA                     "+" Date LCA                          "+" Date LDA                                    "+"Password   "+"\n\n";
			udata.setText(alluserdataheading);  
			try {
				
				Statement getdata=con.createStatement();
				ResultSet usdata=getdata.executeQuery("SELECT * FROM userdata");
				while(usdata.next())
				{
					//appending all user information to this textarea
					String newdata=(usdata.getInt(1)+"        "+usdata.getString(2)+"             "+usdata.getString(3)+"                  "+usdata.getInt(4)+"                 "+usdata.getInt(5)+"               "+usdata.getInt(6)+"                        "+usdata.getString(7)+"                           "+usdata.getString(8)+"                                     "+usdata.getString(9)+"\n");
					udata.appendText(newdata);
					
				}
				
				
				
			} catch (SQLException e) {
				
				System.out.println("SQL Exception "+e);
			}
			
			//load data with refresh button
			
			Refresh.setOnAction(event2->{
				udata.setText(alluserdataheading);//this text is a above heading
				try {Statement loaddata=con.createStatement();
				ResultSet setdata=loaddata.executeQuery("SELECT * FROM userdata");
				while(setdata.next())
				{
					String newdata=(setdata.getInt(1)+"        "+setdata.getString(2)+"             "+setdata.getString(3)+"                  "+setdata.getInt(4)+"                 "+setdata.getInt(5)+"               "+setdata.getInt(6)+"                        "+setdata.getString(7)+"                           "+setdata.getString(8)+"                                     "+setdata.getString(9)+"\n");
					
						udata.appendText(newdata);
						
				}
				}
			
			catch (Exception e) {
				System.out.println(e);
			}
			}
			);
			
			//log out button of Admin
			lgot.setOnAction(event->{
				try {
					new loinpage().showlogin().show();
					adminstage.close();
				} catch (FileNotFoundException e) {
					System.out.println("Error while log out of admin");
				}
				
			});
			
			//borderpane to add all users textarea and to add search and refresh button
			BorderPane bpalluser=new BorderPane();
			bpalluser.setCenter(scudata);
			HBox hboxau=new HBox();
			hboxau.setAlignment(Pos.CENTER);
			hboxau.getChildren().addAll(searcht,searchb,Refresh,lgot);
			hboxau.setSpacing(10);
			
			bpalluser.setBottom(hboxau);
			users=new Tab("All Users");
			users.setContent(bpalluser);
			
			//adding user
			Label name,address,id,setpassword;
			TextField tname,taddress,passwordsetting;
			Button au;
			name=new Label("Name of new user: ");
			address=new Label("Address of user: ");
			setpassword=new Label("Set New Password");
			tname=new TextField();
			tname.setPrefColumnCount(15);
			taddress=new TextField();
			taddress.setPrefColumnCount(15);
			passwordsetting=new TextField();
			passwordsetting.setPrefColumnCount(15);
			id=new Label("");
			
			au=new Button("Add user");
			
			HBox hb1=new HBox();
			hb1.getChildren().addAll(name,tname);
			HBox hb2=new HBox();
			hb2.getChildren().addAll(address,taddress);
			HBox hb3=new HBox();
			hb3.setAlignment(Pos.CENTER);
			hb3.getChildren().addAll(setpassword,passwordsetting);
			
			VBox vb1=new VBox();
			vb1.getChildren().addAll(hb1,hb2,hb3,au,id);
			
			hb1.setAlignment(Pos.CENTER);
			hb2.setAlignment(Pos.CENTER);
			vb1.setAlignment(Pos.CENTER);
			
			hb1.setSpacing(10);
			hb2.setSpacing(10);
			vb1.setSpacing(10);
			  
			
			
			adduser=new Tab("Add user");
			adduser.setContent(vb1);
			
			 //jdbc ops
			
			
			au.setOnAction(Event ->{
				try {
					java.sql.Statement getid=con.createStatement();
					ResultSet rs=getid.executeQuery("SELECT MAX(id) FROM userdata");//it will select max Id number, so when new user will create then id for new user will next number of previous number
					if(rs.next()) {countasid=rs.getInt(1);}
					PreparedStatement stat=con.prepareStatement("INSERT INTO userdata (id,name,address,user_password) VALUES (?,?,?,?)");
					
					stat.setInt(1, ++countasid);
					stat.setString(2, tname.getText());
					stat.setString(3, taddress.getText());
					stat.setString(4, passwordsetting.getText());
					//System.out.println(countasid+ " "+tname.getText()+" "+taddress.getText());
					int check=stat.executeUpdate();
					if(check>0) {
					id.setText("id for "+tname.getText()+" is: "+countasid);
					id.setTextFill(Color.GREEN);}
					
					else {id.setText("Error while creatig ID");
					id.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
			});
			
			//search button of udata
			searchb.setOnAction(event->{
				String tarea=udata.getText(); //taking all data from textarea into this string
				String searchvalue=searcht.getText();//from search textfield
				int index=tarea.indexOf(searchvalue,preindex);//taking start index of word in that textarea
				if(index>0) {udata.selectRange(index, index + (searchvalue.length()));} //selectrange will select word from start index to end index , end index is a start index + length of word
				preindex=index+1;//preindex delcared outside of block, it will take index number+1, hence when i will press search button, it will search for word next from previous index, so we can search all available words
			});
			
			//remove user
			Label userid=new Label("User Id to remove");
			//setting formatted text for integer
			TextField idtoremove=new TextField();
			
			//below defineing format 
			UnaryOperator<TextFormatter.Change> filter=change->{  
				String newtext=change.getControlNewText();
				if(newtext.matches("-?\\d*"))  //here it will allow - sign at first
				{
					return change;
				}
				return null;
			};
			TextFormatter<String> format=new TextFormatter<String>(filter);//adding format to textformatter
			idtoremove.setTextFormatter(format);//setting field with that textformatter
			idtoremove.setPrefColumnCount(10);
			HBox idtremv=new HBox();
			idtremv.getChildren().add(idtoremove);
			idtremv.setAlignment(Pos.CENTER);
			idtremv.setSpacing(10);
			
			Label username=new Label("Name is:" );
			Button getname=new Button("Click to get Name");
			Button removename=new Button("Click to remove account");
			VBox vb2=new VBox();
			vb2.getChildren().addAll(userid,idtremv, getname,username,removename);
			vb2.setSpacing(10);
			vb2.setAlignment(Pos.CENTER);
			deleteuser=new Tab("Remove Users");
			deleteuser.setContent(vb2);
			
			//jdbc ops to remove
			getname.setOnAction(Event->{
				try {
					Statement togetname=con.createStatement();
					ResultSet rs=togetname.executeQuery("SELECT name FROM userdata WHERE id="+idtoremove.getText());
					if(rs.next()) {username.setText("User name is: "+rs.getString(1));
					username.setTextFill(Color.GREEN);}
					else {username.setText("User is not Found");
					username.setTextFill(Color.RED);
					}
				} catch (SQLException e) {
					System.out.println("SQL Exception "+e);
				}
				removename.setOnAction(Event1->{
					Statement toremove;
					try {
						toremove = con.createStatement();
					
					int result=toremove.executeUpdate("DELETE FROM userdata WHERE id="+idtoremove.getText());
					if(result>0) {
						Alert udel=new Alert(AlertType.INFORMATION,"USer deleted Succesfully");
						udel.show();
					}
					else
					{
						Alert udel=new Alert(AlertType.ERROR,"Operation Unsuccessful");
						udel.show();
					}
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			});
			
			//Edit details

			Label selops, selcol,acc_name,set_cond,New_value;
			TextField value,id_user;
			Button makechange,getacc;
			
			selops=new Label();
			selcol=new Label();
			acc_name=new Label();
			set_cond=new Label("Set condition ");
			New_value=new Label();
			
			value=new TextField();
			id_user=new TextField();
			
			value.setPrefColumnCount(10);
			id_user.setPrefColumnCount(10);
			
			makechange=new Button("Make changes");
			getacc=new Button("Get user Name");
			getacc.setDefaultButton(true);
			
			editdetails=new Tab("Edit details");
			
			ChoiceBox<String> ops=new ChoiceBox<>();//basic operations l
			ops.getItems().addAll("Update","Detete");//oprations name
			
			ChoiceBox<String> columnname=new ChoiceBox<>();//available column
			columnname.getItems().addAll("name","address","balance","user_password","credit ammount","debit ammount","credit date","debit date");//oprations name
			HBox choicefields=new HBox();
			choicefields.setAlignment(Pos.CENTER);
			choicefields.getChildren().addAll(set_cond,ops,columnname);
			choicefields.setSpacing(10);
			
			
			
			
			HBox getdetails=new HBox();
			getdetails.setAlignment(Pos.CENTER);
			getdetails.getChildren().addAll(id_user,getacc,acc_name);
			getdetails.setSpacing(10);
			
			HBox cond=new HBox();
			cond.setAlignment(Pos.CENTER);
			cond.getChildren().addAll(selops,selcol);
			cond.setSpacing(10);
			
			
			HBox setvalue=new HBox();
			setvalue.setAlignment(Pos.CENTER);
			setvalue.getChildren().addAll(New_value,value);
			setvalue.setSpacing(10);
			
			VBox editpage=new VBox();
			editpage.setAlignment(Pos.CENTER);
			editpage.getChildren().addAll(getdetails,choicefields,cond,setvalue,makechange);
			editpage.setSpacing(10);
			
			//for ops choice, when value of these choicbox will change then below event will trigger
			ops.valueProperty().addListener(e->{
				
				String selectedops=ops.getValue();
				switch (selectedops) {
				
                case "Update": {
					
					selops.setText("Update ");
					
					break;
				}
                case "Detete": {
					
					selops.setText("Delete from ");
					Alert al=new Alert(AlertType.INFORMATION,"Please use Update option and enter 0 to update");
					al.show();
					break;
				}
				
				}
			});
			
			//for column choice
			//"name","address","balance","user_password","credit ammount","debit ammount","credit date","debit date"
			columnname.valueProperty().addListener(e->{
				
				String selectedcol=columnname.getValue();
				switch (selectedcol) {
				case "name": {
					
					selcol.setText(" name ");
					field="name";
					break;
				}
                case "address": {
					
                	selcol.setText("address ");
                	field="address";
					break;
				}
                case "balance": {
					
                	selcol.setText("balance ");
                	field="balance";
					break;
				}
               case "user_password": {
					
            	   selcol.setText("user password ");
            	   field="user_password";
					break;
				}
               case "credit ammount": {
					
            	   selcol.setText("last credit ammount ");
            	   field="lastcredit";
					break;
				}
               case "debit ammount": {
					
            	   selcol.setText("last debit ammount ");
            	   field="lastdebit";
					break;
				}
               case "credit date": {
					
            	   selcol.setText("last credit date ");
            	   field="creditdate";
					break;
				}
               case "debit date": {
					
            	   selcol.setText("last debit date ");
            	   field="debitdate";
					break;
				}
				
				}
			});
			
			//jdbc for edit details
			getacc.setOnAction(event2->{
				
				try {
					Statement getuname=con.createStatement();
					idtoedit=Integer.parseInt(id_user.getText());
					ResultSet rs=getuname.executeQuery("SELECT name FROM userdata WHERE id="+idtoedit+";");
					if(rs.next()) {acc_name.setText(rs.getString(1));}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
				}
			});
			
			makechange.setOnAction(event->{
				
				try {
					PreparedStatement editdata=con.prepareStatement("UPDATE userdata SET "+field+"=? WHERE id=?;");
					System.out.println("field "+field);
					
					editdata.setInt(2, idtoedit);
					System.out.println("id "+idtoedit);
					
					if(value.getText().matches("\\d+"))  //checking for digits
					{
						int val=Integer.parseInt(value.getText());
						editdata.setInt(1,val );
						System.out.println("number");
						System.out.println("value "+val);
					}
					else
					{
						editdata.setString(1,value.getText() );
						System.out.println("string");
						System.out.println("value "+value.getText());
					}
					
					int state=editdata.executeUpdate();
					if(state>0)
					{
						makechange.setTextFill(Color.GREEN);
					}else {makechange.setTextFill(Color.RED);}
					
				} catch (SQLException e1) {
					makechange.setTextFill(Color.RED);
					e1.printStackTrace();
				}
				
			});
			
			
			editdetails.setContent(editpage);
			
			tb.getTabs().addAll(users,adduser,deleteuser,editdetails);
			
			bp.setCenter(tb);
			
			Scene scene=new Scene(bp,500,500);
			adminstage.setScene(scene);
			
			return adminstage;
		}
	}
	
	class jdbcc
	{
		Connection con;
		public Connection contojdbc() 
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank_of_satish", "root", "9604177455");
			
			} catch (ClassNotFoundException e) {
				
				System.out.println("Class not found");
			} catch (SQLException e) {
				System.out.println("SQL Exception");
			}
			return con;
		}
	}
}
	
	public static void main(String args[])
	{
		launch(args);
	}

	
}