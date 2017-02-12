/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app.Server;

import chat_app.Server.SocketManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bobby Smith
 */
public class Chat_Server {
    
    public final String m_serverName;
    
    private SocketManager m_socketManager;
    private ConnectedClientManager m_clientManager;
    
    public Chat_Server(String name, int portNumber){
        m_serverName = name;
        m_socketManager= new SocketManager(this,portNumber);
        m_clientManager = new ConnectedClientManager(this);
        
    }
    
    public void start(){
        m_socketManager.StartListening();
        System.out.println("Server has started: Port[" + m_socketManager.getServerSocket().getLocalPort() +"]");
    }
    
    public void stop(){
        m_socketManager.StopListening();
        System.out.println("Server has stopped: Port["+ m_socketManager.getServerSocket().getLocalPort() +"]");
    }
    
    public ConnectedClientManager getConnectedClientManager(){
        return m_clientManager;
    } 
    
     public static void main(String[] args) {
       Chat_Server server = new Chat_Server("test",2000);
       server.start();
    
    }
}
