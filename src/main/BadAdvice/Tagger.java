package main.BadAdvice;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.ArrayList;

public class Tagger {


    public ArrayList<Word> tag(String sent, MaxentTagger tagger) {

        sent = capitalizeI(sent);

        String tagged = tagger.tagString(sent);

        String[] tagged_split_by_spaces = tagged.split(" ");

        ArrayList<Word> tagged_words = new ArrayList<Word>();

        for (String word : tagged_split_by_spaces) {
            String[] parts = word.split("_");
            tagged_words.add(new Word(parts[0], parts[1]));
        }

        if (tagged_words.get(tagged_words.size() - 1).getPos().equals(".")) {
            tagged_words.remove(tagged_words.size() - 1);
        }

        return tagged_words;

    }

    public ArrayList<Word> tag(String sent) {

        MaxentTagger tagger = new MaxentTagger("/home/matthew/IdeaProjects/BadAdvice/english-bidirectional-distsim.tagger");

        sent = capitalizeI(sent);

        String tagged = tagger.tagString(sent);

        String[] tagged_split_by_spaces = tagged.split(" ");

        ArrayList<Word> tagged_words = new ArrayList<Word>();

        for (String word : tagged_split_by_spaces) {
            String[] parts = word.split("_");
            tagged_words.add(new Word(parts[0], parts[1]));
        }

        if (tagged_words.get(tagged_words.size() - 1).getPos().equals(".")) {
            tagged_words.remove(tagged_words.size() - 1);
        }

        return tagged_words;

    }

    private String capitalizeI(String sent) {
        return sent.replaceAll("\\bi\\b", "I");
    }

}
