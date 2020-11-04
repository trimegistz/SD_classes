package Aula4.Second;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaMensagens {

    ArrayList<String> messageList;

    public ListaMensagens() {
        messageList = new ArrayList<>();
    }

    public synchronized void add(String message){
        messageList.add(message);
    }

    public synchronized ArrayList<String> sendAll(){
        return messageList;
    }

    public synchronized void clear(){
        messageList.clear();
    }

    @Override
    public String toString(){
        Iterator<String> iterator = messageList.iterator();
        String string = "";
        while(iterator.hasNext()){
            string += iterator.next();
        }
        return string;
    }
}
