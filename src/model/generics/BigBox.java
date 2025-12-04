package model.generics;

import java.util.ArrayList;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class BigBox<T> {
    private ArrayList<T> contents;

    public BigBox() {
        contents = new ArrayList<>();
    }

}
