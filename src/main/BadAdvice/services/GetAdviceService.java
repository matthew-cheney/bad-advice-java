package main.BadAdvice.services;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import main.BadAdvice.BadAdvice;
import main.BadAdvice.models.AdviceResult;

public class GetAdviceService {

    public AdviceResult getAdvice(String question, BadAdvice badAdvice, MaxentTagger tagger) {

        System.out.println("Question: " + question);

        String advice = badAdvice.getAdvice(question, tagger);

        System.out.println("Advice: " + advice);

        return new AdviceResult(advice);

    }

}
