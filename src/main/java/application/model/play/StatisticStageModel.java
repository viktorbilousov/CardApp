package application.model.play;

import application.controllers.play.StatisticStageController;
import application.model.Model;
import application.model.StageModel;
import cardSystem.Theme;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by BellPC on 06.02.2017.
 */
public class StatisticStageModel extends StageModel {

    private StatisticStageController myController = (StatisticStageController)controller;
    private PlayStageModel playModel = (PlayStageModel)parent;

    public StatisticStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
    }
    public void setData(ArrayList<Theme> allQuestion, ArrayList<Theme> notAnswered){
        myController.setStaticData(allQuestion, notAnswered);
    }
    public void openStartPlayModelAndClose(){
        close();
        playModel.openStartMenu();
    }
    public void playAgain(){
        close();
        playModel.openWithOldSetting(myController.getAllThemes());
    }
    public void playNextGame(){
        close();
        playModel.openWithOldSetting(myController.getNotAnsweredThemes());
    }
}
