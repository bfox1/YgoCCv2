package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application
{
    private double x,y;

    /**
     * When using JavaFX, we will use the Start method to invoke the initial startup phase for the program
     * Not only is this loading our fxml file which is used to layout our GUI, but allow the screen to be dragged around.
     * @param primaryStage The Stage passed by the Application
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("YgoCCv2.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event ->
        {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event ->
        {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.show();

    }


    /**
     * Like many Programming languages, all applications require a Main Class.
     * When creating a program using JavaFX, we invoke {@link Application#launch(String...)} which down its stack trace
     * will invoke our {@link Main#start(Stage)} Method, check above.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
