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
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

@ManagedBean(name = "listUserB")
//@ManagedBean(name = "chat")
@ViewScoped
public class Chat implements Serializable {

    String  recieveMessage="",sendMessage,username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
     FacesContext instance;
            RequestContext instance2;
             UIViewRoot viewRoot; 
            UIComponent component;
    
    
    
    
    //--------------------------//

    public void imprime() throws IOException{
        
        
        
        System.out.println("Mae to no print");

        //ta_chat.append("Server started...\n");
    }

    public String getRecieveMessage() {
        return recieveMessage;
    }

    public void setRecieveMessage(String recieveMessage) {
        this.recieveMessage = recieveMessage;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }
    
    public void updateConversation(){
            PushContext pushContext = PushContextFactory.getDefault().getPushContext();
            pushContext.push("/update", "Pressed"); 
           instance.getPartialViewContext().getRenderIds().add(component.getClientId());
            instance2.update(component.getClientId());
            instance2.getAttributes();
          // RequestContext.getCurrentInstance().update("j_idt49:conversation");
                      
            
            
           
            
//context.update("inputTextarea:conversation");
        
    }
    
    

    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }
     public void send() {                                       
        
         
         String nothing = "";
        if (sendMessage.equals(nothing)) {
          
        } else {
            try {
               writer.println(username + ":" + sendMessage + ":" + "Chat");
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
                        
                        recieveMessage = recieveMessage+ data[0] + ": " + data[1] + "\n";
                        //updateConversation();
// RequestContext context = RequestContext.getCurrentInstance();
                        // RequestContext.getCurrentInstance().update("inputTextarea:conversation");
                        
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
                System.out.println(ex.getMessage());
            }
        }
    }

    //--------------------------//
    public void connect() {
        
        if (isConnected == false) {
            String anon = "anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is = String.valueOf(i);
            anon = anon.concat(is);
            username = "Marcio";

           // tf_username.setText(anon);
            //tf_username.setEditable(false);
            try {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
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
