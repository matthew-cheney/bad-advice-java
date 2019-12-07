package main.BadAdvice;

import java.util.Objects;

public class InvalidQuestionError extends Throwable {

    String message;

    public InvalidQuestionError(String message) {
        this.message = message;
    }

    // Default constructor
    public InvalidQuestionError() {
        this.message = "Error: Invalid question";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvalidQuestionError)) return false;
        InvalidQuestionError that = (InvalidQuestionError) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "InvalidQuestionError{" +
                "message='" + message + '\'' +
                '}';
    }
}
