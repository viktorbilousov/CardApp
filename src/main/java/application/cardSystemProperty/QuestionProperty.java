package application.cardSystemProperty;

import cardSystem.Question;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionProperty {
    private StringProperty questionProperty;
    private StringProperty answerProperty;
    private StringProperty tipProperty;

    public QuestionProperty(Question question) {
        questionProperty = new SimpleStringProperty(question.getQuestion());
        answerProperty = new SimpleStringProperty(question.getAnswer());
        tipProperty = new SimpleStringProperty(question.getTip());
    }


    public String getTipProperty() {
        return tipProperty.get();
    }

    public StringProperty tipPropertyProperty() {
        return tipProperty;
    }

    public void setTipProperty(String tipProperty) {
        this.tipProperty.set(tipProperty);
    }

    public String getQuestionProperty() {
        return questionProperty.get();
    }

    public StringProperty questionPropertyProperty() {
        return questionProperty;
    }

    public void setQuestionProperty(String questionProperty) {
        this.questionProperty.set(questionProperty);
    }

    public String getAnswerProperty() {
        return answerProperty.get();
    }

    public StringProperty answerPropertyProperty() {
        return answerProperty;
    }

    public void setAnswerProperty(String answerProperty) {
        this.answerProperty.set(answerProperty);
    }
}
