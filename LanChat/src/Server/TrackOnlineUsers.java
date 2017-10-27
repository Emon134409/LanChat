/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Emon
 */
public class TrackOnlineUsers {

    private ArrayList< User> oUsers;
    private static TrackOnlineUsers singleton = null;
    MulticastSocket socket;
    
    
    private TrackOnlineUsers() throws IOException {
        this.oUsers = new ArrayList<User>();
        socket = new MulticastSocket(7777);
    }

    public void addUser(User u) throws IOException {
        oUsers.add(u);
        //oUsers.add(u);
        this.sendOnlineUsers();
    }

    public void removeUser(User u) throws IOException {
        for (User tmp : oUsers) {
            if (tmp.getName().equals(u.getName()) && tmp.getIP().equals(u.getIP())) {
                oUsers.remove(tmp);
            }
        }
        
        this.sendOnlineUsers();
        
    }

    private String getAllUser() {
        
        StringBuilder users=new StringBuilder(10240);
        
        for (User tmp : oUsers) {
            users.append(tmp.getName());
            users.append(",");
            users.append(tmp.getIP());
            users.append(":");
        }
        
        users.append("Emmm");
        users.append(",");
        users.append("10.220.62.10");
        users.append(":");
        
        //users.append("haha");
        
        //System.out.println("1"+users.toString());
        
        return users.toString();
    }
    
    public void sendOnlineUsers() throws UnknownHostException, IOException
    {
        
        byte[] buf = new byte[10*1024];
        buf = this.getAllUser().getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length,InetAddress.getByName("224.2.2.4"), 7777);    
        socket.send(packet);
           
    }

    
    public static TrackOnlineUsers getInstance() throws IOException {
        
        if (singleton == null) {
            singleton = new TrackOnlineUsers();
        }
        return singleton;
    }

}
