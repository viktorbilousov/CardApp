package application.cardSystemProperty;

import cardSystem.Question;
import cardSystem.Theme;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

public class ThemeProperty{

    private StringProperty themeName;
    private ObservableList<QuestionProperty> questionProperiesList;

    public ThemeProperty(Theme theme) {
        themeName = new SimpleStringProperty(theme.getThemeName());
        questionProperiesList = FXCollections.observableArrayList();

        for(Question question : theme.getQuestionsList()){
            questionProperiesList.add(new QuestionProperty(question));
        }
    }

    public String getThemeName() {
        return themeName.get();
    }

    public StringProperty themeNameProperty() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName.set(themeName);
    }

    public ObservableList<QuestionProperty> getQuestionProperiesList() {
        return questionProperiesList;
    }

    public void setQuestionProperiesList(ObservableList<QuestionProperty> questionProperiesList) {
        this.questionProperiesList = questionProperiesList;
    }

    public static StringConverter getConverter(){
        return new StringConverter() {
            @Override
            public String toString(Object object) {
                return object.toString();
            }

            @Override
            public ThemeProperty fromString(String string) {
                return new ThemeProperty(new Theme(string));
            }
        };
    }

    public Theme convertToTheme(){
        return new Theme( themeName.get() );
    }

}
