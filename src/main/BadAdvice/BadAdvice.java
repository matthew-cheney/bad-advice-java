package main.BadAdvice;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.ArrayList;
import java.util.Scanner;

public class BadAdvice {

    public static void main(String[] args) {

        Tagger tagger = new Tagger();

        while (true) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Question:");

            String sent = scanner.nextLine();

            if (sent.equals("exit") || sent.equals("quit")) {
                break;
            }

            MaxentTagger tagger_m = new MaxentTagger("/home/matthew/IdeaProjects/BadAdvice/english-bidirectional-distsim.tagger");

            ArrayList<Word> tagged = tagger.tag(sent, tagger_m);

            Parser parser = new Parser();

            Question question = null;

            try {
                question = parser.parse(tagged);
            } catch (InvalidQuestionError e) {
                System.out.println(e.getMessage());
                continue;
            }

            Adviser adviser = new Adviser();
            String advice = adviser.getAdvice(question);
            System.out.println(advice);
        }
    }

    public String getAdvice(String sent, MaxentTagger m_tagger) {

        Tagger tagger = new Tagger();

        ArrayList<Word> tagged = tagger.tag(sent, m_tagger);

        Parser parser = new Parser();

        Question question = null;

        try {
            question = parser.parse(tagged);
        } catch (InvalidQuestionError e) {
            System.out.println(e.getMessage());
            return "I am sorry. I do not understand your question.";
        }

        Adviser adviser = new Adviser();
        String advice = adviser.getAdvice(question);
        return advice;
    }

}
