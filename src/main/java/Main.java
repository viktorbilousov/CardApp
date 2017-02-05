import application.MainApp;
import cardSystem.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.smartcardio.Card;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {
    static MainApp mainApp = null;

    public void start(Stage primaryStage) throws Exception {
        mainApp.start(primaryStage);
    }

    public static void main(String[] args) {
        mainApp = new MainApp();
        launch(args);

    }
}
