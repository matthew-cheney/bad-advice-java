package main.BadAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Adviser {

    Map<String, String> pronounsToFlip;

    public Adviser() {
        this.pronounsToFlip = new HashMap<>();
        this.pronounsToFlip.put("your", "my");
        this.pronounsToFlip.put("my", "your");
        this.pronounsToFlip.put("yours", "mine");
        this.pronounsToFlip.put("mine", "yours");
        this.pronounsToFlip.put("there", "here");
        this.pronounsToFlip.put("here", "there");
        this.pronounsToFlip.put("me", "you");
        this.pronounsToFlip.put("you", "me");
    }

    public String getAdvice(Question question) {

        ArrayList<Word> auxiliary = getAdviceAuxiliary(question);
        ArrayList<Word> nounPhrase = getAdviceNounPhrase(question);
        ArrayList<Word> verbPhrase = getAdviceVerbPhrase(question);

        Advice advice = new Advice(auxiliary, nounPhrase, verbPhrase);

        Random rand = new Random();

        if (rand.nextBoolean()) {
            return getNegativeAdvice(advice);
        } else {
            return getPositiveAdvice(advice);
        }
    }

    private ArrayList<Word> getAdviceAuxiliary(Question question) {
        ArrayList<Word> auxiliary = new ArrayList<>();
        Word auxWord = question.getAuxiliary().get(0);
        auxiliary.add(new Word(flipAuxiliary(auxWord.getLower_word(), question.getNounPhrase()), auxWord.getPos()));
        return auxiliary;
    }

    private ArrayList<Word> getAdviceNounPhrase(Question question) {
        return flipPronouns(question.getNounPhrase());
    }

    private ArrayList<Word> getAdviceVerbPhrase(Question question) {
        return flipPronouns(question.getVerbPhrase());
    }

    private ArrayList<Word> flipPronouns(ArrayList<Word> words) {
        ArrayList<Word> flippedWords = new ArrayList<>();
        boolean firstYouFound = false;
        for (Word word : words) {
            if (word.getLower_word().equals("you") && !firstYouFound) {
                flippedWords.add(new Word("I", "PRP"));
                firstYouFound = true;
            } else if (word.getLower_word().equals("i")) {
                flippedWords.add(new Word("you", "PRP"));
            }
            else if (this.pronounsToFlip.containsKey(word.getLower_word())) {
                flippedWords.add(new Word(this.pronounsToFlip.get(word.getLower_word()), "PRP$"));
            }
            else {
                flippedWords.add(word);
            }
        }
        return flippedWords;
    }

    private String getNegativeAdvice(Advice advice) {
        String advice_string = String.format("No, %s %s not %s", advice.getNounPhraseFormatted(), advice.getAuxiliaryFormatted(), advice.getVerbPhraseFormatted());
        return advice_string;
    }

    private String getPositiveAdvice(Advice advice) {
        String advice_string = String.format("Yes, %s %s %s", advice.getNounPhraseFormatted(), advice.getAuxiliaryFormatted(), advice.getVerbPhraseFormatted());
        return advice_string;
    }

    String flipAuxiliary(String auxWord, ArrayList<Word> nounPhrase) {
        if (nounPhrase.get(0).getLower_word().equals("you") ||
            nounPhrase.get(0).getLower_word().equals("I")) {
            if (auxWord.equals("am")) {
                return "you";
            } else if (auxWord.equals("are")) {
                return "am";
            } else {
                return auxWord;
            }
        }
        return auxWord;
    }
}
