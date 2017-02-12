/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bobby Smith
 */
public class Client {
    private Socket m_socket;
    private BufferedReader  m_recieving;
   // private BufferedReader m_clientTextInput;
    private PrintWriter m_sending;
    
      public static void main(String[] args) {
       Client client = new Client("localhost",2000);
       
       while(client.getSocket().isConnected()){
           //client.recieve();
           client.write("Hello World! \r\n");
           
       }
    }
    
    Client(String hostName, int portNumber){
        try {
            m_socket = new Socket(hostName,portNumber);
            m_recieving = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
            m_sending = new PrintWriter(m_socket.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Socket getSocket(){
        return m_socket;
    }
    
    public void write(String stringData){
        if(!stringData.isEmpty()){
            m_sending.print(stringData);
            m_sending.flush();
        }
    }
    
    public void recieve(){
        String serverResponse = null;
        try {
            while((serverResponse = m_recieving.readLine()) != null){
                System.out.println("Message from server!:" + serverResponse);
            }} catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
