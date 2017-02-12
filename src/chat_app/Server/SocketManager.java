/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app.Server;

import static com.sun.glass.ui.Application.run;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bobby Smith
 */
public class SocketManager {
    
    private Chat_Server m_server;
    private ServerSocket m_serverSocket;
   
    private boolean m_listening;
    
    public SocketManager(Chat_Server server,int portNumber) {
        try {
            m_serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            Logger.getLogger(SocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        m_listening = false;
        m_server = server;
    }
    
    public void StartListening(){
        m_listening = true;
        new Thread(){
            @Override
            public void run(){
                 while(m_listening){
                    ConnectedClient newConnectedClient = null;
                    try{
                        newConnectedClient = new ConnectedClient(m_serverSocket.accept());
                      
                        System.out.println("Client Connected");
                        m_server.getConnectedClientManager().addClient(newConnectedClient); // This adds its to connected clients and also runs it
                        
                    } catch (IOException ex) {
                         Logger.getLogger(SocketManager.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
            
                try {
                    m_serverSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(SocketManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }.start();
        
    }
    
    public void StopListening(){
        m_listening = false;
    }
    
    public ServerSocket getServerSocket(){
        return m_serverSocket;
    }
}
