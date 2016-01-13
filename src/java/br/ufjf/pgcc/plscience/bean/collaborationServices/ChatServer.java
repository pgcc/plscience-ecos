package br.ufjf.pgcc.plscience.bean.collaborationServices;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean()
@ViewScoped
public class ChatServer implements Serializable {
    ArrayList clientOutputStreams;
   ArrayList<String> users;
public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;

       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
               // ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    //ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
                    for (String token:data) 
                    {
                   //     ta_chat.append(token + "\n");
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":has disconnected." + ":" + chat));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else 
                    {
                      //  ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                //ta_chat.append("Lost a connection. \n");
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

   
   private void print() {                                        
        System.out.println("Mae to no print");
        
        //ta_chat.append("Server started...\n");
    }   
    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {                                      
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
       // ta_chat.append("Server stopping... \n");
        
       // ta_chat.setText("");
    }                                     

    private void startServer() {                                        
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        //ta_chat.append("Server started...\n");
    }                                       

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {                                        
        //ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
         //   ta_chat.append(current_user);
         //   ta_chat.append("\n");
        }    
        
    }                                       

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {                                        
      //  ta_chat.setText("");
    }                                       

    public static void main(String args[]) 
    {
        /*java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ChatServer();
            }
        });*/
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);

				Thread listener = new Thread(new ClientHandler(clientSock, writer));
				listener.start();
			//	ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
               // ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        //ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        //ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);

        for (String token:tempList) 
        {
            message = (token + add);
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		//ta_chat.append("Sending: " + message + "\n");
                writer.flush();
               // ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		//ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
}