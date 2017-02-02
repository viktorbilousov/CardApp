package application.controllers;

import application.cardSystemProperty.QuestionProperty;
import application.model.Model;
import application.model.sceneModel.UniQuestionsCheckSceneModel;
import cardSystem.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.util.ArrayList;

/**
 * Created by BellPC on 26.01.2017.
 */
public class UniQuestionCheckController implements Controller {


    ObservableList<QuestionProperty> list = FXCollections.observableArrayList();

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button backButton;

    @FXML
    private TableView<QuestionProperty> tableView;
    @FXML
    private TableColumn<QuestionProperty, String>  questionTable;
    @FXML
    private TableColumn<QuestionProperty, Boolean> checkTable;

    private ArrayList<Question> uniQuestion;
    private ArrayList<Question> question;
    private UniQuestionsCheckSceneModel myModel;


    @Override
    public void initialize() {
        questionTable.setCellValueFactory(param -> param.getValue().questionPropertyProperty());
        checkTable.setCellFactory(param ->  new CheckBoxTableCell());
        checkTable.setCellValueFactory(param -> param.getValue().checkProperty());

    }

    @Override
    public void updateElementsData() {
        list.clear();
        for(Question question : uniQuestion){
            QuestionProperty questionProperty = new QuestionProperty(question);
           // questionProperty.setCheck(true);
            list.add(questionProperty);
        }
        tableView.setItems(list);
    }



    public void setUniQuestion(ArrayList<Question> uniQuestion) {
        this.uniQuestion = uniQuestion;
    }

    public void setQuestion(ArrayList<Question> question) {
        this.question = question;
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (UniQuestionsCheckSceneModel) model;
    }


    @FXML
    private void addButton(){
        System.out.println("add event");

        ObservableList<QuestionProperty> list = tableView.getItems();

        for(QuestionProperty questionProperty : list){
            if(questionProperty.isCheck()){
                if(!question.contains(questionProperty.convertToQuestion())) {
                    question.add(questionProperty.convertToQuestion());
                }else {
                    System.out.println("error add: list contain question");
                }
            }
        }
        myModel.updateDataParent();
        myModel.closeParentStage();
    }
    @FXML
    private void editButton(){
        myModel.showEditQuestionScene();
    }
    @FXML
    private void closeButton(){
        myModel.closeParentStage();
    }
    public void sayHi(){
        System.out.println("hi");
    }
}
