package application.model.stageModels;

import application.MainApp;
import application.model.Model;
import application.model.StageModel;
import application.model.sceneModel.CheckUniQuestionModel;
import application.model.sceneModel.EditUniQuestionModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class UniQuestionModel extends StageModel {

    private CheckUniQuestionModel checkUniQuestion;
    private EditUniQuestionModel editUniQuestion;

    public UniQuestionModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
        init();
     //   showCheckUniQuestion();
    }

    private void init(){
        checkUniQuestion = new CheckUniQuestionModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneUniQuestionCheck.fxml")
        );
        editUniQuestion = new EditUniQuestionModel(
                (BorderPane)getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneUniQuestionEdit.fxml")
        );

    }

    public void setDataToController(ArrayList<Question> uniQuestions, ArrayList<Question> themeQuestion ) {
        checkUniQuestion.setDataToController(uniQuestions, themeQuestion);
        editUniQuestion.setQuestionList(uniQuestions);
    }

    public void showCheckUniQuestion(){
        checkUniQuestion.show();
        checkUniQuestion.updateData();
        show();
    }
    public void showEditUniQuestion(){
        editUniQuestion.show();
        show();
    }

}
