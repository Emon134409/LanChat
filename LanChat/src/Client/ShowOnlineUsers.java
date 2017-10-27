/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import GUI.LanChatGUI;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emon
 */
public class ShowOnlineUsers implements Runnable {

    String onlineUsers;
    MulticastSocket socket;
    DatagramPacket inPacket;
    byte[] inBuf;
    private static ShowOnlineUsers singleton;

    private ShowOnlineUsers() throws IOException {
        
        this.singleton = null;
        socket = new MulticastSocket(7777);
        InetAddress address = InetAddress.getByName("224.2.2.4");
        socket.joinGroup(address);

    }

    @Override
    public void run() {
            
        try {
            while(true){
                
               
                Thread.sleep(3000);
                onlineUsers=this.receiveOnlineUsers();
                
                LanChatGUI.setOnlineUsers(onlineUsers.substring(0,onlineUsers.length()-1));
                
            }
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ShowOnlineUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public String getOnlineUsers()
//    {
//        return this.onlineUsers;
//    }

    private String receiveOnlineUsers() throws IOException {
        
        inBuf= new byte[10 * 1024];
        inPacket = new DatagramPacket(inBuf, inBuf.length);
        socket.receive(inPacket);
        String msg = new String(inBuf, 0, inPacket.getLength()).trim();
       // System.out.println(msg);
        return msg;

    }
    
    
    public static ShowOnlineUsers getInstance() throws IOException {
        
        if (singleton == null) {
            singleton = new ShowOnlineUsers();
        }
        return singleton;
    }

    
}
