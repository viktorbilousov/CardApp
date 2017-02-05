package cardSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ThemesCardPlayer implements Iterable<Question>{
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Theme> inputThemesList;
    private ArrayList<Theme> notAnsweredThemesList = new ArrayList<>();
    private int cursor = 0;

    public ThemesCardPlayer(ArrayList<Theme> inputThemesList, boolean needShuffleCards){
        this.inputThemesList = inputThemesList;

        for(int i = 0; i< inputThemesList.size(); i++){
            for(Question question : inputThemesList.get(i).getQuestionsList()){
                cards.add(new Card(question, i));
            }
        }
        if(needShuffleCards){
            Collections.shuffle(cards);
        }
        for(Theme theme : inputThemesList){
            notAnsweredThemesList.add(new Theme(theme.getThemeName()));
        }

    }

    public Integer size(){
        return cards.size();
    }

    public void setAnswerState(boolean isAnswered) {
        cards.get(cursor-1).setAnswered(isAnswered);
    }
    public ArrayList<Theme> stopAndGetNotAnsteredThemesList(){
        stopPlay();
        return notAnsweredThemesList;
    }
    public void stopPlay(){
        for(Card card : cards){
            if(!card.isAnswered()){
                notAnsweredThemesList.get(card.getIndexTheme()).addQuestion(card.getQuestion());
            }
        }
    }
    public void reset(){
        for(int i=0 ; i < notAnsweredThemesList.size() ; i++){
            notAnsweredThemesList.get(i).getQuestionsList().clear();
        }
        cursor = 0;
    }
    public void shuffle(){
        Collections.shuffle(cards);
    }
    public String getQuestionTheme(Question question){ // костыль
        for(Theme theme : this.inputThemesList){
            if(theme.getQuestionsList().contains(question))
                return theme.getThemeName();
        }
        throw new IllegalArgumentException("theme of Question '" + question.getQuestion() + "' not found");
    }
    @Override
    public Iterator<Question> iterator() {
        return new CardIterator();
    }
    private final class CardIterator implements Iterator<Question> {
        @Override
        public boolean hasNext() {
            return cursor < cards.size();
        }

        @Override
        public Question next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return cards.get(cursor++).getQuestion();
        }

    }

}

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
