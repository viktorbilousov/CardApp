package cardSystem.Play;

import cardSystem.Question;

class Card
{
    private Question question;
    private int indexTheme;
    private boolean isAnswered = false;

    public Card(Question question, int indexTheme) {
        this.question = question;
        this.indexTheme = indexTheme;
    }
    //region setters/getters
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getIndexTheme() {
        return indexTheme;
    }

    public void setIndexTheme(int indexTheme) {
        this.indexTheme = indexTheme;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }
    //endregion
}
