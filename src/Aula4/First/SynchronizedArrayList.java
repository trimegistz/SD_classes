package Aula4.First;

import java.util.ArrayList;

public class SynchronizedArrayList {
    private ArrayList<String> list;

    public SynchronizedArrayList(){
        list = new ArrayList<String>();
    }

    public synchronized void add(String o) {
        list.add(o);
    }

    public synchronized ArrayList<String> get() {
        return list;
    }

    public String get(int k) {
        return list.get(k);
    }

    public int size(){
        return list.size();
    }

}
