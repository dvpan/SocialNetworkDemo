package model;


public class Word{
    private String value;
    private Integer count;

    public Word(String value, Integer count) {
        this.value = value;
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object obj) {
        if(value.equals(((Word)obj).value) && count.equals(((Word)obj).count)) return true;
        else return false;
    }

    public String toString() {
        return count+" : "+value;
    }
}
