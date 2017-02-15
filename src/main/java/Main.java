import application.MainApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static MainApp mainApp = null;

    public void start(Stage primaryStage) throws Exception {
        mainApp.start(primaryStage);
    }

    public static void main(String[] args){
        System.out.println("hello!");
        mainApp = new MainApp();
        launch(args);
    }
}
