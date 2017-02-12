/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_app;

import chat_app.Server.Chat_Server;

/**
 *
 * @author Bobby Smith
 */
public class Chat_App {

    public static void main(String[] args) {
       Chat_Server server = new Chat_Server("test",2000);
       server.start();
    
    }
    
}
