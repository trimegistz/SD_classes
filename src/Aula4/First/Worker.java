package Aula4.First;

import java.util.ArrayList;

public class Worker extends Thread{
    ArrayList frases;
    int numero;

    public Worker(ArrayList f,int n) {
        super("Worker");
        this.frases = f;
        this.numero = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
                frases.add("Frase "+ i +" da thread " + numero);
        }
    }
}