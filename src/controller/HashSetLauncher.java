package controller;

import model.collection.HashSet;

/**
 * @author Vincent Velthuizen
 * Test de HashSet
 */
public class HashSetLauncher {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();

        set.add("]");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        set.add("F");
        set.add("G");
        set.add("H");
        set.add("I");
        set.add("Aap");
        set.add("Noot");
        set.add("Mies");
        set.add("Wim");
        set.add("Zus");
        set.add("Jet");
        set.add("Wiel");
        set.add("Roos");

        System.out.println(set.contains("Roos"));
        System.out.println(set.remove("Roos"));
        System.out.println(set.contains("Roos"));

    }

}
