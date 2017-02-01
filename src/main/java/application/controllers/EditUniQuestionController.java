package application.controllers;

import application.cardSystemProperty.QuestionProperty;
import application.model.Model;
import application.model.sceneModel.EditUniQuestionModel;
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
public class EditUniQuestionController implements Controller {

    private EditUniQuestionModel myModel;
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

        questionColumn.setCellFactory(param ->
        {
            TextFieldTableCell tableCell = new TextFieldTableCell<QuestionProperty, String>();
            tableCell.setConverter(QuestionProperty.getConverter());
            return tableCell;
        });
        questionColumn.setCellValueFactory(param -> param.getValue().questionPropertyProperty());

        questionColumn.setOnEditCommit(event -> {
            QuestionProperty question = event.getTableView().getItems().get(event.getTablePosition().getRow());
            Object newQuestionObject = event.getNewValue(); // not cast directly to QuestionProperty
            QuestionProperty newQuestionProperty = (QuestionProperty)newQuestionObject;

            question.setQuestionProperty("null");
            question.setQuestionProperty(newQuestionProperty.getQuestionProperty());
        });

        deleteColumn.setCellFactory( param -> {
            ButtonCell button = new ButtonCell<QuestionProperty, String>();
            button.getButton().setOnAction(event ->  tableView.getItems().remove(button.getItem()));
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
        myModel = (EditUniQuestionModel) model;
    }


    @FXML
    private void addButton(){
        questionsList.add(new Question("question"));
        updateElementsData();
    }
    @FXML
    private void backButton(){
        myModel.showCheckQuestionScene();
    }

    private void sayHi(){
        System.out.println("hi!");
    }
}
