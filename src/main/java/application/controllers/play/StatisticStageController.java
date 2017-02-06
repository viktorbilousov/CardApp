package application.controllers.play;

import application.cardSystemProperty.StatisticProperty;
import application.controllers.Controller;
import application.model.Model;
import application.model.play.StatisticStageModel;
import cardSystem.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class StatisticStageController implements Controller {

    @FXML
    private Button playNextBnt;
    @FXML
    private TableColumn<StatisticProperty, String> statisticCol;
    @FXML
    private TableColumn<StatisticProperty, String> themesCol;
    @FXML
    private TableView<StatisticProperty> tableView;

    private StatisticStageModel myModel;
    private ArrayList<Theme> allThemes;
    private ArrayList<Theme> notAnsweredThemes;
    private ObservableList<StatisticProperty> statisticProperties = FXCollections.observableArrayList();;

    private static final Color goodColor = Color.GREEN;
    private static final Color normalColor = Color.ORANGE;
    private static final Color badColor = Color.RED;

    private static final double minGood = 0.8;
    private static final double maxBad = 0.3;

    private boolean  isAllQuestionAnswered = false;

    public ArrayList<Theme> getAllThemes() {
        return allThemes;
    }

    public ArrayList<Theme> getNotAnsweredThemes() {
        return notAnsweredThemes;
    }

    public void setStaticData(ArrayList<Theme> allThemes, ArrayList<Theme> notAnsweredThemes) {
        this.allThemes = allThemes;
        this.notAnsweredThemes = notAnsweredThemes;
        updateElementsData();
    }

    @Override
    public void initialize() {
        themesCol.setCellValueFactory(param -> param.getValue().themeNameProperty());
        statisticCol.setCellValueFactory(param -> param.getValue().statisticProperty());
        statisticCol.setCellFactory(param -> new TableCell<StatisticProperty, String>(){
             @Override
             protected void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if(item == null) {
                     setText(null);
                     return;
                 }
                 setText(item);
                 String[] line = item.replaceAll(" ", "").split("/");
                 double answeredQuestionSize = Integer.parseInt(line[0]);
                 double allQuestionSize = Integer.parseInt(line[1]);
                 double persent = answeredQuestionSize / allQuestionSize;

                 if(persent <= maxBad)  setTextFill(badColor);
                 else if(persent <= minGood) setTextFill(normalColor);
                 else setTextFill(goodColor);
             }
         });
    }

    @Override
    public void updateElementsData() {
         statisticProperties.clear();

         int sumCntNotAnns = 0;
         for(int i=0 ; i< allThemes.size() ; i++){
             int cntQuestionAll = allThemes.get(i).getQuestionsList().size();
             int cntNotAnswQuestion = notAnsweredThemes.get(i).getQuestionsList().size();
             statisticProperties.add(new StatisticProperty(
                     allThemes.get(i).getThemeName(),
                     cntQuestionAll,
                     cntNotAnswQuestion
                     )
             );
             sumCntNotAnns += cntNotAnswQuestion;
         }
         tableView.setItems(statisticProperties);

        if(sumCntNotAnns == 0) {
            isAllQuestionAnswered = true;
            playNextBnt.setDisable(true);
        }else {
            playNextBnt.setDisable(false);
            isAllQuestionAnswered = false;
        }

    }

    @Override
    public void setMyModel(Model model) {
        myModel = (StatisticStageModel) model;
    }

    @FXML
    private void playAgainHandler() {
        myModel.playAgain();
    }

    @FXML
    private void finishHandler() {
        myModel.openStartPlayModelAndClose();
    }

    @FXML
    private void playHandler() {
        myModel.playNextGame();
    }
}
