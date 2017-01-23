package application.controllers;


import application.cardSystemProperty.QuestionProperty;
import application.model.Model;
import application.model.SceneModel;
import application.model.sceneModel.ViewQuestionSceneModel;
import cardSystem.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class SceneViewQuestionsController implements Controller{


    private ArrayList<Question> questions;
    private ObservableList<QuestionProperty> questionProperties = FXCollections.observableArrayList();
    private ViewQuestionSceneModel myModel;

    @FXML
    private TableView<QuestionProperty> questionTableView;
    @FXML
    private TableColumn<QuestionProperty, String> questionColumn;
    @FXML
    private TableColumn<QuestionProperty, String> answerColumn;

    @FXML
    public void initialize() {
        questionColumn.setCellValueFactory(data -> data.getValue().questionPropertyProperty());
        answerColumn.setCellValueFactory(data -> data.getValue().answerPropertyProperty());
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

    @Override
    public void setInputData(Object data) {
        if(data == null)
            return;

        if(!(data instanceof ArrayList)){
            System.out.println("error input data in " + getClass().getSimpleName());
            return;
        }
        questions = (ArrayList<Question>)data;
    }

    @Override
    public ArrayList<Question> getInputData() {
        return questions;
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (ViewQuestionSceneModel) model;
    }

    @FXML
    public void newButton(){
        myModel.showAddQuestionStage();
    }
    @FXML
    public void editButton(){
        if(questionTableView.getSelectionModel().getSelectedItem() == null){
            myModel.openErrorSelectWindow();
            return;
        }
        int index  =  questionTableView.getSelectionModel().getSelectedIndex();
        myModel.showEditQuestion(questions.get(index));
    }

    @FXML void deleteButton(){
        int index =  questionTableView.getSelectionModel().getFocusedIndex();
        questions.remove(index);
        this.updateElementsData();
    }

    @FXML void backButton(){
        myModel.goToViewThemes();
    }
}
