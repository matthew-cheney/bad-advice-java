package main.BadAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parser {

    Set<String> auxiliaries;
    ArrayList<String> singleWordNounPhrases;

    public Parser() {
        this.auxiliaries = new HashSet<>();
        this.auxiliaries.addAll(Arrays.asList(
                "is", "am", "are", "was", "were", "have", "has", "had", "do", "does", "did", "could", "would", "should", "may", "must", "might", "can", "will", "won't"
        ));

        this.singleWordNounPhrases = new ArrayList<>();
        this.singleWordNounPhrases.add("there");

    }

    public Question parse(ArrayList<Word> tagged_words) throws InvalidQuestionError {

        ArrayList<Word> auxiliary = findAuxiliary(tagged_words, 0);
        ArrayList<Word> nounPhrase = findNounPhrase(tagged_words, auxiliary.size());
        ArrayList<Word> verbPhrase = findVerbPhrase(tagged_words, auxiliary.size() + nounPhrase.size());

        return new Question(auxiliary, nounPhrase, verbPhrase);
    }

    private ArrayList<Word> findAuxiliary(ArrayList<Word> taggedWords, int index) throws InvalidQuestionError {
        if (this.auxiliaries.contains(taggedWords.get(index).getLower_word())) {
            ArrayList<Word> auxiliary = new ArrayList<>();
            auxiliary.add(taggedWords.get(index));
            return auxiliary;
        } else {
            throw new InvalidQuestionError();
        }
    }

    private ArrayList<Word> findNounPhrase(ArrayList<Word> taggedWords, int index) {

        ArrayList<Word> nounPhrase = new ArrayList<>();

        if (this.singleWordNounPhrases.contains(taggedWords.get(index).getLower_word())) {
            nounPhrase.add(taggedWords.get(index));
            return nounPhrase;
        }

        // Check if head noun in pronoun
        if (taggedWords.get(index).getPos().equals("PRP")) {
            nounPhrase.add(taggedWords.get(index));
            return nounPhrase;
        }

        // Check for (pre)determiner
        while (index < taggedWords.size() && taggedWords.get(index).getPos().endsWith("DT")) {
            nounPhrase.add(taggedWords.get(index));
            index++;
        }

        // Check for possessive pronouns
        while (index < taggedWords.size() && taggedWords.get(index).getPos().startsWith("PRP")) {
            nounPhrase.add(taggedWords.get(index));
            index++;
        }

        // Continue until the head noun
        while (index < taggedWords.size() && !taggedWords.get(index).getPos().startsWith("N")) {
            nounPhrase.add(taggedWords.get(index));
            index++;
        }

        // Add the head noun (and any connected nouns, such as full names)
        while (index < taggedWords.size() && taggedWords.get(index).getPos().startsWith("N")) {
            nounPhrase.add(taggedWords.get(index));
            index++;
        }

        return nounPhrase;
    }

    ArrayList<Word> findVerbPhrase(ArrayList<Word> taggedWords, int index) {
        ArrayList<Word> verbPhrase = new ArrayList<>();

        while (index < taggedWords.size()) {
            verbPhrase.add(taggedWords.get(index));
            index++;
        }

        return verbPhrase;
    }

}
