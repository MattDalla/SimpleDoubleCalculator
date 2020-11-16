package dev.matt.calculator;

public enum Number {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    ZERO("0");

    public final String value;

    Number(String value){
        this.value = value;
    }

    public static Character toChar(Number num){
        switch(num) {
            case (ONE):
                return '1';
            case (TWO):
                return '2';
            case (THREE):
                return '3';
            case (FOUR):
                return '4';
            case (FIVE):
                return '5';
            case (SIX):
                return '6';
            case (SEVEN):
                return '7';
            case (EIGHT):
                return '8';
            case (NINE):
                return '9';
            case (ZERO):
                return '0';
            default:
                throw new RuntimeException("ERROR");
        }
    }

}
