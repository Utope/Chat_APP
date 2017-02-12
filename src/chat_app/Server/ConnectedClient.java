/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app.Server;

import java.io.BufferedReader;
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
public class ConnectedClient implements Runnable {

    private Socket m_clientSocket;
    PrintWriter m_sending;
    BufferedReader m_recieving;
        
    public ConnectedClient(Socket socket){
        m_clientSocket = socket;
        
        try {
            m_sending = new PrintWriter(socket.getOutputStream(), true);
            m_recieving = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run() {
        while(true){
            this.recieveClientInput();
        }
    }
    
    public Socket getClientSocket(){
        return m_clientSocket;
    }
    
    // Thread methods
    
    public void sendServerOutputToClient(String stringData){
        if(!stringData.isEmpty()){
            m_sending.print(stringData);
            m_sending.flush();
        }
    }
    
    public String recieveClientInput(){
        String clientResponse = null;
        System.out.println("recivie!!");
        try {
            while((clientResponse = m_recieving.readLine()) != null){
                System.out.println("Message from Client!:" + clientResponse);
                
            }   } catch (IOException ex) {
            Logger.getLogger(ConnectedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientResponse;
    }
}