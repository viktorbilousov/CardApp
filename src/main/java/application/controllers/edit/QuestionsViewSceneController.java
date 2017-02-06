package application.controllers.edit;


import application.cardSystemProperty.QuestionProperty;
import application.controllers.Controller;
import application.model.Model;
import application.model.edit.sceneModel.QuestionsViewSceneModel;
import cardSystem.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class QuestionsViewSceneController implements Controller {



    private ArrayList<Question> questions;
    private ObservableList<QuestionProperty> questionProperties = FXCollections.observableArrayList();
    private QuestionsViewSceneModel myModel;

    @FXML
    private Button editBtn;
    @FXML
    private TableView<QuestionProperty> questionTableView;
    @FXML
    private TableColumn<QuestionProperty, String> questionColumn;
    @FXML
    private TableColumn<QuestionProperty, String> answerColumn;
    @FXML
    private TableColumn<QuestionProperty, String> tipColumn;
    @FXML
    private Label nameThemeLabel;

    @FXML
    public void initialize() {
        questionColumn.setCellValueFactory(data -> data.getValue().questionPropertyProperty());
        answerColumn.setCellValueFactory(data -> data.getValue().answerPropertyProperty());
        tipColumn.setCellValueFactory(data -> data.getValue().tipPropertyProperty());

    }

    public ArrayList<Question> getQuestionsList() {
        return questions;
    }

    public void setQuestionsList(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void updateElementsData() {
        if(questions == null)
            return;

        questionProperties.clear();
        for(Question question : questions){
            questionProperties.add(new QuestionProperty(question));
        }
        questionTableView.setItems(questionProperties);

    }

    public void setNameThemeLabel(String themeName){
        nameThemeLabel.setText(themeName);
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (QuestionsViewSceneModel) model;
    }

    @FXML
    public void newButton(){
        myModel.showAddQuestionStage();
    }
    @FXML
    public void editButton(){
        myModel.showEditQuestion();
    }

    @FXML void deleteButton(){
        int index =  questionTableView.getSelectionModel().getFocusedIndex();
        questions.remove(index);
        this.updateElementsData();
    }

    @FXML void backButton(){
        myModel.goToViewThemes();
    }

    @FXML
    private void uniButton(){
        myModel.showUniQuestionStage();
    }
}
