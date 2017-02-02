package application.controllers;

import application.cardSystemProperty.QuestionProperty;
import application.model.Model;
import application.model.sceneModel.UniQuestionsEditSceneModel;
import application.util.ButtonCell;
import cardSystem.Question;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;

/**
 * Created by BellPC on 27.01.2017.
 */
public class UniQuestionEditController implements Controller {

    private UniQuestionsEditSceneModel myModel;
    private ArrayList<Question> questionsList;

    @FXML
    private TableView<QuestionProperty> tableView;
    @FXML
    private TableColumn<QuestionProperty, String> questionColumn;
    @FXML
    private TableColumn<QuestionProperty, String> deleteColumn;

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
        updateElementsData();
    }


    @Override
    public void initialize() {

        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        questionColumn.setCellValueFactory(param -> param.getValue().questionPropertyProperty());

        questionColumn.setOnEditCommit(event -> {
            QuestionProperty question = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newQuestion = event.getNewValue().toString();
            int index = questionsList.indexOf(question.convertToQuestion());
            questionsList.get(index).setQuestion(newQuestion);
            question.setQuestionProperty(newQuestion);
        });

        deleteColumn.setCellFactory( param -> {
            ButtonCell button = new ButtonCell<QuestionProperty, String>();
            button.getButton().setOnAction(event -> {
                QuestionProperty question = (QuestionProperty) button.getItem();
                questionsList.remove(question.convertToQuestion());
                tableView.getItems().remove(question);
            });
            return button;
        });
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue()));
    }

    @Override
    public void updateElementsData() {
        ObservableList<QuestionProperty> questionProperties = FXCollections.observableArrayList();
        for(Question question : questionsList){
            questionProperties.add(new QuestionProperty(question));
        }
        tableView.setItems(questionProperties);
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (UniQuestionsEditSceneModel) model;
    }


    @FXML
    private void addButton(){
        String nameQuestion = "Question";
        Question addingQuestion = new Question(nameQuestion);
        for(int i=1; ;i++) {
            if(!questionsList.contains(addingQuestion)) {
                questionsList.add(addingQuestion);
                break;
            }else {
                addingQuestion.setQuestion(nameQuestion + i);
            }
        }
        updateElementsData();
    }
    @FXML
    private void okButton(){
        myModel.showCheckQuestionScene();
    }

    private void sayHi(){
        System.out.println("hi!");
    }
}
