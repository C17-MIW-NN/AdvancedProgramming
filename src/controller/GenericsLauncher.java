package controller;

import model.generics.Box;
import model.generics.IdentifiableBox;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class GenericsLauncher {

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();

        stringBox.setContent("Hello");

        String text = stringBox.getContent();

        Box<Integer> intBox = new Box<>();
        intBox.setContent(42);

//        IdentifiableBox<Box<String>, Double> box = new IdentifiableBox<>(new Box<String>());
    }

}
