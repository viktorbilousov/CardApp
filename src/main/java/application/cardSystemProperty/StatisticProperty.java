package application.cardSystemProperty;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StatisticProperty {
    private SimpleStringProperty themeName;

    private SimpleStringProperty statistic;

    public StatisticProperty(SimpleStringProperty themeName, SimpleIntegerProperty allThemesCnt, SimpleIntegerProperty notAnswered) {
        this.themeName = themeName;
        statistic = new SimpleStringProperty(allThemesCnt.getValue() - notAnswered.getValue() + " / " + allThemesCnt);

    }

    public StatisticProperty(String themeName, Integer allThemesCnt, Integer notAnswered) {
        this.themeName = new SimpleStringProperty(themeName);
        statistic = new SimpleStringProperty(allThemesCnt - notAnswered + " / " + allThemesCnt);
    }

    public String getThemeName() {
        return themeName.get();
    }

    public SimpleStringProperty themeNameProperty() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName.set(themeName);
    }

    public String getStatistic() {
        return statistic.get();
    }

    public SimpleStringProperty statisticProperty() {
        return statistic;
    }

    public void setStatistic(String statistic) {
        this.statistic.set(statistic);
    }

}
