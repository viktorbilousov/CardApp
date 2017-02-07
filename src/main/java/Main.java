import application.MainApp;
import cardSystem.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.smartcardio.Card;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends Application {
    static MainApp mainApp = null;

    public void start(Stage primaryStage) throws Exception {
        mainApp.start(primaryStage);
    }

    public static void main(String[] args) {
      /*  CardSystem system = new CardSystem();
        system.setThemeList(CardGenerator.getThemesList(4,4));
       system.displayAll();
        CardSystemStream systemStream = new CardSystemStream(system);
        try {
            systemStream.saveCardsToXLSXFile("C:\\Users\\BellPC\\Desktop\\test\\cards.xlsx", true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        mainApp = new MainApp();
        launch(args);

    }
}
