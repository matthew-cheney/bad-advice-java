package main.BadAdvice;

import java.util.ArrayList;
import java.util.Objects;

public class Advice {

    private ArrayList<Word> auxiliary;
    private ArrayList<Word> nounPhrase;
    private ArrayList<Word> verbPhrase;

    public Advice(ArrayList<Word> auxiliary, ArrayList<Word> nounPhrase, ArrayList<Word> verbPhrase) {
        this.auxiliary = auxiliary;
        this.nounPhrase = nounPhrase;
        this.verbPhrase = verbPhrase;
    }

    public ArrayList<Word> getAuxiliary() {
        return auxiliary;
    }

    public void setAuxiliary(ArrayList<Word> auxiliary) {
        this.auxiliary = auxiliary;
    }

    public ArrayList<Word> getNounPhrase() {
        return nounPhrase;
    }

    public void setNounPhrase(ArrayList<Word> nounPhrase) {
        this.nounPhrase = nounPhrase;
    }

    public ArrayList<Word> getVerbPhrase() {
        return verbPhrase;
    }

    public void setVerbPhrase(ArrayList<Word> verbPhrase) {
        this.verbPhrase = verbPhrase;
    }

    public String getAuxiliaryFormatted() {
        return formatString(this.auxiliary).toLowerCase();
    }

    public String getNounPhraseFormatted() {
        return formatString(this.nounPhrase);
    }

    public String getVerbPhraseFormatted() {
        return formatString(this.verbPhrase);
    }

    private String formatString(ArrayList<Word> wordList) {
        StringBuilder formattedString = new StringBuilder();

        for (Word word : wordList) {
            formattedString.append(word.getOriginal_word());
            formattedString.append(" ");
        }

        return formattedString.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advice)) return false;
        Advice question = (Advice) o;
        return auxiliary.equals(question.auxiliary) &&
                nounPhrase.equals(question.nounPhrase) &&
                verbPhrase.equals(question.verbPhrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auxiliary, nounPhrase, verbPhrase);
    }

    @Override
    public String toString() {
        return "Question{" +
                "auxiliary=" + auxiliary +
                ", nounPhrase=" + nounPhrase +
                ", verbPhrase=" + verbPhrase +
                '}';
    }
}
