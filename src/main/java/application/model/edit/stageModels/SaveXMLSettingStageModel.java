package application.model.edit.stageModels;

import application.controllers.edit.SaveXMLSettingStageController;
import application.model.Model;
import application.model.StageModel;
import application.util.StageUtil;
import cardSystem.CardSystem;
import cardSystem.CardSystemStream;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by BellPC on 07.02.2017.
 */
public class SaveXMLSettingStageModel extends StageModel {

    SaveXMLSettingStageController myController = (SaveXMLSettingStageController)controller;
    RootStageModel rootStageModel= (RootStageModel)parent;
    CardSystemStream systemStream;

    public SaveXMLSettingStageModel(Stage primaryStage, Model parent, URL FXMLLocation, CardSystemStream systemStream) {
        super(primaryStage, parent, FXMLLocation);
    }

    public void saveToXmlFile(boolean writeAnswer, boolean writeTip) {
        rootStageModel.showSaveToExcel(writeAnswer, writeTip);
    }
}
