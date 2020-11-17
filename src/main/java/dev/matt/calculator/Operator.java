package dev.matt.calculator;

enum Operator {
    PLUS('+', 0),
    MINUS('-', 0),
    MULT('x', 1),
    DIV('÷', 1),
    PERCENT('%', 1);


    public final Character value;
    public final int priority;

    Operator(Character value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public String getText() {
        return this.value.toString();
    }

    public Character getValue(){
        return this.value;
    }

    public static boolean isOperator(String s) { //funzione totale
        switch (s) {
            case "+":
            case "÷":
            case "-":
            case "x":
            case "%":
                return true;
            default:
                return false;
        }
    }

    public static Operator fromString(String s) {//funzione parziale
        switch (s) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            case "÷":
                return DIV;
            case "x":
                return MULT;
            case "%":
                return PERCENT;
            default:
                throw new RuntimeException("invalid operator");
        }
    }


    public static int getPriority(Operator op){
        return op.priority;
    }

//    public boolean isPlus(Operator o) {
//        return o == plus;
//    }
}