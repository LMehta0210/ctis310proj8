package edu.guilford;

public class Word implements Comparable<Word> {
    private String word;
    private int count;
    public static boolean sortCount = false;

    public Word(String word) {
        this.word = word;
        this.count = 1;
    }

    public String getWord() {
        return this.word;
    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public int compareTo(Word other) {
        if (sortCount) {
            if (this.count > other.count) {
                return -1;
            } else if (this.count < other.count) {
                return 1;
            } else {
                return 0;
            }
        } else{
            return this.word.compareTo(other.word);
        }
    }

    public String toString() {
        return this.word + " " + this.count;
    }
    
}
