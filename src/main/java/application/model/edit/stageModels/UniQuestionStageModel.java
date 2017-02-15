package application.model.edit.stageModels;

import application.MainApp;
import application.model.Model;
import application.model.StageModel;
import application.model.edit.sceneModel.UniQuestionsCheckSceneModel;
import application.model.edit.sceneModel.UniQuestionsEditSceneModel;
import cardSystem.Question;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class UniQuestionStageModel extends StageModel {

    private UniQuestionsCheckSceneModel checkUniQuestion;
    private UniQuestionsEditSceneModel editUniQuestion;

    public UniQuestionStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
        init();
        getPrimaryStage().setMinHeight(465);
        getPrimaryStage().setMinWidth(600);
     //   showCheckUniQuestion();
    }

    private void init(){
        checkUniQuestion = new UniQuestionsCheckSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("/fxml/edit/SceneUniQuestionsCheck.fxml")
        );
        editUniQuestion = new UniQuestionsEditSceneModel(
                (BorderPane)getRootLayout(),
                this,
                MainApp.class.getResource("/fxml/edit/SceneUniQuestionsEdit.fxml")
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
