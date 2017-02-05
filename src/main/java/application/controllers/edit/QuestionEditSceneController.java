package application.controllers.edit;

import application.cardSystemProperty.QuestionProperty;
import application.controllers.Controller;
import application.model.Model;
import application.model.edit.sceneModel.QuestionsEditSceneModel;
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

public class QuestionEditSceneController implements Controller {

    @FXML
    private TableView<QuestionProperty> questionTableView;
    @FXML
    private TableColumn<QuestionProperty, String> questionColumn;
    @FXML
    private TableColumn<QuestionProperty, String> answerColumn;
    @FXML
    private TableColumn<QuestionProperty, String> tipColumn;
    @FXML
    private TableColumn<QuestionProperty, String> deleteColumn;

    private ArrayList<Question> questionList;
    private QuestionsEditSceneModel myModel;

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public void initialize() {
        questionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        answerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tipColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        questionColumn.setCellValueFactory(param -> param.getValue().questionPropertyProperty());
        answerColumn.setCellValueFactory(param -> param.getValue().answerPropertyProperty());
        tipColumn.setCellValueFactory(param -> param.getValue().tipPropertyProperty());

        questionColumn.setOnEditCommit(event -> {
            QuestionProperty question = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newQuestion = event.getNewValue().toString();
            int index = questionList.indexOf(question.convertToQuestion());
            questionList.get(index).setQuestion(newQuestion);
            question.setQuestionProperty(newQuestion);

        });
        answerColumn.setOnEditCommit(event ->{
            QuestionProperty question = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newAnswer = event.getNewValue().toString();
            int index = questionList.indexOf(question.convertToQuestion());
            questionList.get(index).setAnswer(newAnswer);
            question.setAnswerProperty(newAnswer);
        });

        tipColumn.setOnEditCommit(event -> {
            QuestionProperty question = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newTip = event.getNewValue().toString();
            int index = questionList.indexOf(question.convertToQuestion());
            questionList.get(index).setTip(newTip);
            question.setTipProperty(newTip);
        });

        deleteColumn.setCellFactory( param -> {
            ButtonCell button = new ButtonCell<QuestionProperty, String>();
            button.getButton().setOnAction(event -> {
                QuestionProperty question = (QuestionProperty) button.getItem();
                questionList.remove(question.convertToQuestion());
                questionTableView.getItems().remove(question);
            });
            return button;
        });
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue()));
    }

    @Override
    public void updateElementsData() {
        ObservableList<QuestionProperty> questionProperties = FXCollections.observableArrayList();
        for(Question question : questionList){
            questionProperties.add(new QuestionProperty(question));
        }
        questionTableView.setItems(questionProperties);
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (QuestionsEditSceneModel) model;
    }

    @FXML
    private void okButton(){
        myModel.backToViewQuestionsScene();
    }
    @FXML
    private void addButton(){
        String nameQuestion = "Question";
        Question addingQuestion = new Question(nameQuestion, "Answer", "Tip");
        for(int i=1; ;i++) {
            if(!questionList.contains(addingQuestion)) {
                questionList.add(addingQuestion);
                break;
            }else {
                addingQuestion.setQuestion(nameQuestion + i);
            }
        }
        updateElementsData();

    }
}
