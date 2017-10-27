/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 *
 * @author Emon
 */

public class LanChatClient {
    
    private DatagramPacket sendPacket;
    private  DatagramSocket clientSocket;
    private byte[] sendData;
    
    public LanChatClient(String sIP) throws SocketException, IOException
    {
        
        this.clientSocket = new DatagramSocket();
        
        this.start(sIP);
        //for(int i=0;i<1000000;i++)
        //{
            
        //}
        //this.getAllOnlineUser();
        
    }
    
    
    
    private String getHostIP() throws SocketException {

        return ClientInformation.getHostIP();
    }
    
    private String getHostUserName()
    {
        return ClientInformation.getHostUserName();
    }
    
    private void start(String sIP) throws SocketException, UnknownHostException, IOException
    {
        InetAddress IPAddress = InetAddress.getByName(sIP);
        String sentence="1010101" + ":" + this.getHostUserName() + ":" + this.getHostIP();
        
        //System.out.println(sIP);
        //System.out.println(this.getHostUserName());
        //System.out.println(this.getHostIP());
        
        //this.sendData = new byte[2 * 1024];
        this.sendData = new byte[sentence.length()];
      
        this.sendData = sentence.getBytes();
        this.sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
        this.clientSocket.send(sendPacket);
    }
    
    public void end(String sIP) throws UnknownHostException, SocketException, IOException
    {
        InetAddress IPAddress = InetAddress.getByName(sIP);
        String sentence="0101010";
        
        this.sendData = new byte[2 * 1024];
        sendData = sentence.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
        clientSocket.send(sendPacket);
        
    }
    
   

}
