package dev.matt.calculator;

public enum Number {

    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ZERO('0');

    public final Character value;

    Number(Character value){
        this.value = value;
    }

    public Character getValue() {
        return this.value;
    }

    public String getText(){
        return this.value.toString();
    }

}

