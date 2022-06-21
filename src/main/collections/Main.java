package main.collections;

import main.collections.list.arraylist.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            arrayList.add(i);
        }
        arrayList.remove(4);
    }
}
