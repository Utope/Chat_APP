/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app.Server;

import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Bobby Smith
 */
public class ConnectedClientManager {
    
    private Chat_Server m_server;
    private ArrayList<ConnectedClient> m_connectedClients;
     
    public ConnectedClientManager(Chat_Server server){
        m_server = server;
        m_connectedClients = new ArrayList<>();
    }
    
    public void addClient( ConnectedClient newClient){ // We can do checks here for whitelisting / blacklisting
        new Thread(newClient).start();
        m_connectedClients.add(newClient);
    
        
    }
    
    public void removeClient(){
        
    }
}
