package application.cardSystemProperty;

import cardSystem.Question;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class QuestionProperty {
    private StringProperty questionProperty;
    private StringProperty answerProperty;

    public QuestionProperty(Question question) {
        questionProperty = new SimpleStringProperty(question.getQuestion());
        answerProperty = new SimpleStringProperty(question.getAnswer());
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
