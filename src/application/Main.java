package application;
	
import java.io.FileInputStream;
import java.io.InputStream;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception
	{
		Group root=new Group();//group is as a panel or layout where we can add components, and can pass group into scene
		Scene scene=new Scene(root, 500, 500,  Color.RED);//here we can select color of frame 
		Image logo=new Image(new FileInputStream("E:\\mahesh\\images - Copy.jpeg"));
		//taking image from file and saving into image type
		stage.setScene(scene);
		stage.setTitle("Satish_App");//to set title of application
		stage.getIcons().add(logo);//setting icon of app, and passing that image
		stage.setHeight(400);//can set height from here
		stage.setWidth(400);//can set width from here
		stage.setX(50);//point at which pp up
		stage.setY(50);
		//stage.setFullScreen(false);//set full screen is closed,hence as a pop up it will open , a small window
		//stage.setResizable(false);//can not resize
		stage.setFullScreen(true);//direct full size window will open,with msg of press escape
		stage.setFullScreenExitHint("Press x to minimize");//when screen is full size, then this text will appear as a hint
		stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("x"));//here if i press x then screen will minimize
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
