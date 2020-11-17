package dev.matt.calculator;

public class Brackets {
    private int opened = 0;

    public int getOpened(){
        return this.opened;
    }
    public void increaseOpened(){
        this.opened++;
    }
    public void decreaseOpened(){
        this.opened--;
    }
    public void setDefault(){
        this.opened = 0;
    }
}
