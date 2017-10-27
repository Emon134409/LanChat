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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emon
 */
public class SendMessage{
    
    private DatagramPacket sendPacket;
    private DatagramSocket clientSocket;
    private byte[] sendData;
    private String serverIP;
    private String intendedClient;
    private String message;
    
    public SendMessage(String sIP,String iClient,String msg) throws SocketException, IOException
    {
        this.clientSocket = new DatagramSocket();     
        this.serverIP=sIP;
        this.intendedClient=iClient;
        this.message=msg;
    }
    
    
    public void run() {
        
//        String msg;
//        Scanner input=new Scanner(System.in);
//        msg=input.next();
        try {
             
             this.sendMessage();
             
         } catch (IOException ex) {
             Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void sendMessage() throws UnknownHostException, IOException
    {
        this.sendData = new byte[2 * 1024];
        
        InetAddress IPAddress = InetAddress.getByName(serverIP);
        
        StringBuilder msg = new StringBuilder();
        msg.append(this.message);
        msg.append(":");
        msg.append(intendedClient);
        
        this.sendData = msg.toString().getBytes();
        this.sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9999);
        this.clientSocket.send(sendPacket);
    
       // receivePacket = new DatagramPacket(receiveData, receiveData.length);
       // clientSocket.receive(receivePacket);
       // String sentence = new String(receivePacket.getData());
       // System.out.println(sentence);

    }
    
}

