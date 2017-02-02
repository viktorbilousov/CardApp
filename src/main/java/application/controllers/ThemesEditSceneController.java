package application.controllers;

import application.cardSystemProperty.QuestionProperty;
import application.cardSystemProperty.ThemeProperty;
import application.model.Model;
import application.model.sceneModel.ThemesEditSceneModel;
import application.util.ButtonCell;
import cardSystem.Theme;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.table.TableCellEditor;
import java.util.ArrayList;

public class ThemesEditSceneController implements Controller{

    @FXML
    private TableView<ThemeProperty> themeTableView;
    @FXML
    private TableColumn<ThemeProperty, String> themesColumn;
    @FXML
    private TableColumn<ThemeProperty, String> deleteColumn;

    private ThemesEditSceneModel myModel = null;
    private ArrayList<Theme> themeArrayList;
    private ObservableList<ThemeProperty> observableList = FXCollections.observableArrayList();

    public ArrayList<Theme> getThemeArrayList() {
        return themeArrayList;
    }

    public void setThemeArrayList(ArrayList<Theme> themeArrayList) {
        this.themeArrayList = themeArrayList;
        updateElementsData();
    }

    @Override
    public void initialize() {
       /* themesColumn.setCellFactory(param ->
        {
            TextFieldTableCell tableCell = new TextFieldTableCell<ThemeProperty, String>();
            tableCell.setConverter(ThemeProperty.getConverter());
            return tableCell;
        });*/
        themesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        themesColumn.setCellValueFactory(param -> param.getValue().themeNameProperty());

        themesColumn.setOnEditCommit(event -> {
            ThemeProperty theme = event.getTableView().getItems().get(event.getTablePosition().getRow());
            String newThemeName = event.getNewValue().toString();
            int index = themeArrayList.indexOf(theme.convertToTheme());
            themeArrayList.get(index).setThemeName(newThemeName);
            theme.setThemeName(newThemeName);
        });

        deleteColumn.setCellFactory( param -> {
            ButtonCell button = new ButtonCell<QuestionProperty, String>();
            button.getButton().setOnAction(event -> {
                ThemeProperty themeProperty = (ThemeProperty) button.getItem();
                themeArrayList.remove(themeProperty.convertToTheme());
                themeTableView.getItems().remove(themeProperty);
            });
            return button;
        });
        deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue()));
    }

    @Override
    public void updateElementsData() {
        observableList.clear();
        for(Theme theme : themeArrayList){
            observableList.add(new ThemeProperty(theme));
        }
        themeTableView.setItems(observableList);
    }

    @Override
    public void setMyModel(Model model) {
        myModel = (ThemesEditSceneModel) model;
    }

    @FXML
    private void okButton(){
        myModel.backToViewThemes();
    }
    @FXML
    private void addButton(){
        String name = "Theme";
        Theme addingTheme = new Theme(name);
        for(int i=1; ;i++ ) {
            if(!themeArrayList.contains(addingTheme)) {
                this.getThemeArrayList().add(addingTheme);
                break;
            }else {
                addingTheme.setThemeName(name + i);
            }
        }
        updateElementsData();
    }
}
