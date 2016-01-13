package br.ufjf.pgcc.plscience.bean.collaborationServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "chat")
@ViewScoped
public class Chat implements Serializable {

    String message,username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    
    //--------------------------//

    public void imprime() throws IOException{
        
        
        
        System.out.println("Mae to no print");

        //ta_chat.append("Server started...\n");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
     public void send() {                                       
        String nothing = "";
        if (message.equals(nothing)) {
          
        } else {
            try {
               writer.println(username + ":" + message + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                //ta_chat.append("Message was not sent. \n");
            }
            
        }

       
    }          
    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    if (data[2].equals(chat)) {
                        // ta_chat.append(data[0] + ": " + data[1] + "\n");
                        //ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                    } else if (data[2].equals(connect)) {
                        // ta_chat.removeAll();
                        //userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        //userRemove(data[0]);
                    } else if (data[2].equals(done)) {
                        //users.setText("");
                        // writeUsers();
                        users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    //--------------------------//
    public void connect() {
        //tf_username.setText("");
        if (isConnected == false) {
            String anon = "anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is = String.valueOf(i);
            anon = anon.concat(is);
            username = anon;

           // tf_username.setText(anon);
            //tf_username.setEditable(false);
            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                //  ta_chat.append("Cannot Connect! Try Again. \n");
                //tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true) {
            //ta_chat.append("You are already connected. \n");
        }
    }

}
