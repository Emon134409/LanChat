/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Client.LanChatClient;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Server.LanChatServer;
import Server.BroadCastServer;
import Client.ReceiveMessage;
import Client.ShowOnlineUsers;
import Client.ClientInformation;

/**
 *
 * @author Emon
 */
public class SetUPServerClient {

    private String serverIP;
    private static SetUPServerClient singleton=null;
    
    public String getServerIP()
    {
        return this.serverIP;
    }
    
    private SetUPServerClient()
    {
        
    }
    
    public static SetUPServerClient getInstance()
    {
        if(singleton==null)
        {
            singleton=new SetUPServerClient();
        }
        
        return singleton;
    }
    
    public void run() {
        try {

            
            serverIP = this.detectServer();

            if (serverIP == null) {
                
                new Thread(new LanChatServer()).start();
                serverIP= this.initiateBroadcastServer();
               
            } 
            //else {
                
                //return serverIP;
                //System.out.println("Yahoo !! I got the server" + serverIP);
            //}
            
            
            new Thread(new ReceiveMessage()).start();
            LanChatClient lanChatClient = new LanChatClient(serverIP);
            new Thread(ShowOnlineUsers.getInstance()).start();
            
            
        } catch (IOException | InterruptedException ex) {

            Logger.getLogger(SetUPServerClient.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }

    private String detectServer() throws IOException, InterruptedException {

        MulticastSocket socket;
        DatagramPacket inPacket;
        byte[] inBuf = new byte[2 * 1024];

        socket = new MulticastSocket(8888);
        inPacket = new DatagramPacket(inBuf, inBuf.length);

        InetAddress address = InetAddress.getByName("224.2.2.3");
        socket.joinGroup(address);
        socket.setSoTimeout(6000);

        try {

            socket.receive(inPacket);
            String msg = new String(inBuf, 0, inPacket.getLength()).trim();
            return msg;

        } catch (SocketTimeoutException s) {
            //System.out.println("Socket timed out!");
            return null;
        }

    }

    private String initiateBroadcastServer() throws SocketException, IOException {

        
        serverIP = getHostIP();
        //System.out.print(serverIP);
        if (serverIP == null) {
            //code that shows error msg about either no lan connection or invalid lan ip
        } else {

            new Thread(new BroadCastServer(serverIP)).start();

        }
    
        return serverIP;

    }
    
    

    private String getHostIP() throws SocketException {

        return ClientInformation.getHostIP();
    }

}
