package main.BadAdvice.models;

import java.util.Objects;

public class AdviceResult {

    String advice;

    public AdviceResult(String advice) {
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdviceResult)) return false;
        AdviceResult that = (AdviceResult) o;
        return advice.equals(that.advice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(advice);
    }
}
