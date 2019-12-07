package main.BadAdvice;

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

            ArrayList<Word> tagged = tagger.tag(sent);

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

}
