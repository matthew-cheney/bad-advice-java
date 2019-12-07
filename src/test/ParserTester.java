package test;

import main.BadAdvice.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTester {

    private class TestQuestion {
        String question;
        String auxiliary;
        String nounPhrase;
        String verbPhrase;

        public TestQuestion(String question, String auxiliary, String nounPhrase, String verbPhrase) {
            this.question = question;
            this.auxiliary = auxiliary;
            this.nounPhrase = nounPhrase;
            this.verbPhrase = verbPhrase;
        }
    }

    TestQuestion[] basicQuestions = {
            new TestQuestion("are you my mother", "are", "you", "my mother"),
            new TestQuestion("am i happy", "am", "i", "happy"),
            new TestQuestion("does the sun set in the west", "does", "the sun", "set in the west"),
            new TestQuestion("are you in my house", "are", "you", "in my house"),
            new TestQuestion("is the big yellow thing the sun", "is", "the big yellow thing", "the sun"),
            new TestQuestion("do you care about me", "do", "you", "care about me")
    };

    TestQuestion[] formattedQuestions = {
            new TestQuestion("Are you my mother?", "are", "you", "my mother"),
            new TestQuestion("ARE YOU MY MOTHER?", "are", "you", "my mother")
    };

    TestQuestion[] exceptionQuestions = {
            new TestQuestion("Is there a snake in my boot", "is" ,"there", "a snake in my boot")
    };

    Tagger tagger;
    Parser parser;

    @BeforeEach
    public void setup() {
        this.tagger = new Tagger();
        this.parser = new Parser();
    }

    @Test
    public void testBasicQuestions() {

        for (TestQuestion testQuestion : basicQuestions) {
            ArrayList<Word> taggedWords = this.tagger.tag(testQuestion.question);
            try {
                Question resultQuestion = this.parser.parse(taggedWords);
                assertEquals(testQuestion.auxiliary, resultQuestion.getAuxiliaryFormatted());
                assertEquals(testQuestion.nounPhrase, resultQuestion.getNounPhraseFormatted());
                assertEquals(testQuestion.verbPhrase, resultQuestion.getVerbPhraseFormatted());
            } catch (InvalidQuestionError e) {
                fail();
            }
        }
    }

    @Test
    public void testFormattedQuestions() {

        for (TestQuestion testQuestion : formattedQuestions) {
            ArrayList<Word> taggedWords = this.tagger.tag(testQuestion.question);
            try {
                Question resultQuestion = this.parser.parse(taggedWords);
                assertEquals(testQuestion.auxiliary, resultQuestion.getAuxiliaryFormatted());
                assertEquals(testQuestion.nounPhrase, resultQuestion.getNounPhraseFormatted());
                assertEquals(testQuestion.verbPhrase, resultQuestion.getVerbPhraseFormatted());
            } catch (InvalidQuestionError e) {
                fail();
            }
        }
    }

    @Test
    public void testExceptionQuestions() {

        for (TestQuestion testQuestion : exceptionQuestions) {
            ArrayList<Word> taggedWords = this.tagger.tag(testQuestion.question);
            try {
                Question resultQuestion = this.parser.parse(taggedWords);
                assertEquals(testQuestion.auxiliary, resultQuestion.getAuxiliaryFormatted());
                assertEquals(testQuestion.nounPhrase, resultQuestion.getNounPhraseFormatted());
                assertEquals(testQuestion.verbPhrase, resultQuestion.getVerbPhraseFormatted());
            } catch (InvalidQuestionError e) {
                fail();
            }
        }
    }


}
