import application.MainApp;
import cardSystem.CardSystem;
import cardSystem.Theme;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    static MainApp mainApp = null;

    public void start(Stage primaryStage) throws Exception {
        mainApp.start(primaryStage);
    }

    public static void main(String[] args) {
        CardSystem system = new CardSystem();
        system.loadDefPar();
       // system.displayAll();
        mainApp = new MainApp(system);
        launch(args);

    }
}
