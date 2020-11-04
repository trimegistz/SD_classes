package Aula4.First;

import java.util.ArrayList;

public class ObjSharing {
    public static void main(String[] args) throws InterruptedException {
        int NThreads = 5;
        ArrayList asFrases = new ArrayList();

        for (int i = 0; i < NThreads; i++) {
            new Worker(asFrases, i).start();
        }

        Thread.sleep(3000);
//        for (int j = 0; j < 6; j++) {
            for(int k = 0; k < asFrases.size(); k++)
                System.out.println(asFrases.get(k));
//        }
    }
}