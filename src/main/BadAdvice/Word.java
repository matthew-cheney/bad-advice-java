package main.BadAdvice;

import java.util.Objects;

public class Word {

    private String original_word;
    private String lower_word;
    private String pos;

    Word(String word, String pos) {
        this.original_word = word;
        this.lower_word = word.toLowerCase();
        this.pos = pos;
    }

    public String getOriginal_word() {
        return original_word;
    }

    public void setOriginal_word(String original_word) {
        this.original_word = original_word;
    }

    public String getLower_word() {
        return lower_word;
    }

    public void setLower_word(String lower_word) {
        this.lower_word = lower_word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return original_word.equals(word.original_word) &&
                lower_word.equals(word.lower_word) &&
                pos.equals(word.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(original_word, lower_word, pos);
    }

    @Override
    public String toString() {
        return "Word{" +
                "original_word='" + original_word + '\'' +
                ", lower_word='" + lower_word + '\'' +
                ", pos='" + pos + '\'' +
                "}\n";
    }
}
