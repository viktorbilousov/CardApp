package application.controllers;


import application.MainApp;
import application.cardSystemProperty.QuestionProperty;
import cardSystem.Question;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class SceneViewQuestionsController implements Controller{


    private ArrayList<Question> questions;
    private MainApp mainApp;
    private ObservableList<QuestionProperty> questionProperties = FXCollections.observableArrayList();

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


    @Override
    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
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

    public void setInputData(Object data) {
        if(data == null)
            return;

        if(!(data instanceof ArrayList)){
            System.out.println("error input data in " + getClass().getSimpleName());
            return;
        }
        questions = (ArrayList<Question>)data;
    }



    @FXML
    public void newButton(){
        try {
            System.out.println("new event");
            mainApp.getAddQuestionStageModel().setDataToController(this.questions);
            mainApp.getAddQuestionStageModel().updateData();
            mainApp.showAddQuestionStage();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    public void editButton(){
        System.out.println("edit event");
    }

    @FXML void deleteButton(){
        int index =  questionTableView.getSelectionModel().getFocusedIndex();
        questions.remove(index);
        this.updateElementsData();
    }

    @FXML void backButton(){
        mainApp.showViewThemes();
    }
}
