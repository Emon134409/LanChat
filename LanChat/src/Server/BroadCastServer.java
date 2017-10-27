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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emon
 */

public class BroadCastServer implements Runnable {
    
    String serverIP=null;
    MulticastSocket socket;
    
    public BroadCastServer(String sIP) throws IOException
    {
        socket = new MulticastSocket(8888);
        this.serverIP=sIP;
    }
    
    @Override
    public void run()
    {
        try {
                        
                this.broadcastServerIp();
            
        } catch (IOException ex) {
            Logger.getLogger(BroadCastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void broadcastServerIp() throws UnknownHostException, IOException
    {
        byte[] buf = new byte[256];
        buf = serverIP.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length,InetAddress.getByName("224.2.2.3"), 8888);
            
        while(true){
            
            //System.out.println("I am Server "+serverIP);
            socket.send(packet);
            
        }
    
    }
}
