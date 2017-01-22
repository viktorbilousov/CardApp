package application;

import cardSystem.CardSystem;
import cardSystem.Question;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by BellPC on 20.01.2017.
 */
public class MainApp extends Application {

    private CardSystem cardSystem;

    private Stage primaryStage = null;
    private StageModel stageRoot;
    private SceneModel viewThemes;
    private SceneModel viewQuestion;

    public MainApp(CardSystem cardSystem) {
        this.cardSystem = cardSystem;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Card System");
        initialize();
        stageRoot.show();

        cardSystem.getThemeList().get(0).addQuestion(new Question("new question", "new answer"));
        cardSystem.getThemeList().get(0).addQuestion(new Question("new question", "new answer"));
        cardSystem.getThemeList().get(0).addQuestion(new Question("new question", "new answer"));
        cardSystem.getThemeList().get(0).addQuestion(new Question("new question", "new answer"));
        cardSystem.displayAll();

        viewQuestion.updateData();
 //       viewQuestion.show();
        viewThemes.show();
    }
    private void initialize(){
        stageRoot = new StageModel( primaryStage, "../fxml/StageRoot.fxml");
        stageRoot.init();

        viewThemes = new SceneModel(this,  (BorderPane) stageRoot.getRootLayout(),"../fxml/SceneViewThemes.fxml" );
        viewQuestion = new SceneModel(this,(BorderPane) stageRoot.getRootLayout(), "../fxml/SceneViewQuestions.fxml");


        viewQuestion.setDataToController(cardSystem.getThemeList().get(0).getQuestionsList());
        viewQuestion.init();


        viewThemes.setDataToController(cardSystem.getThemeList());
        viewThemes.init();
    }


    public void showViewThemes(){
        viewThemes.show();
    }
    public void showViewQuestionOfTheme(int idTheme){
        ArrayList<Question> list = (ArrayList<Question>) cardSystem.getThemeList().get(idTheme).getQuestionsList();

        viewQuestion.setDataToController(cardSystem.getThemeList()
                .get(idTheme).getQuestionsList());

        viewQuestion.updateData();
        viewQuestion.show();
    }

}
