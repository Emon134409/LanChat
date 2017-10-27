/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emon
 */

public class LanChatServer implements Runnable {

    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private DatagramSocket serverSocket;
    private byte[] receiveData;
    private byte[] sendData;
    
    
    public LanChatServer() throws SocketException {
        
       
        this.serverSocket = new DatagramSocket(9999);
    
    }

    @Override
    public void run() {
        
        try {
            
            this.handleRequest();
            
        } catch (IOException ex) {
            Logger.getLogger(LanChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void handleRequest() throws SocketException, IOException {
         
      
        
        while (true) {

            //System.out.println("Yahoo !! I AM handling");
            
              this.sendData = new byte[2 * 1024];
              this.receiveData = new byte[2 * 1024];
            
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            //String msg = new String(receiveData, 0, receivePacket.getLength());
            String sentence = new String(receivePacket.getData()).trim();
            
            if(sentence.charAt(0)=='1' && sentence.charAt(6)=='1' && sentence.charAt(7)==':')
            {
               String [] splice=sentence.split(":");
               if(splice[0].equals("1010101"))
               {
                   TrackOnlineUsers temp=TrackOnlineUsers.getInstance();
                   temp.addUser(new User(splice[1],splice[2]));
               }
              
               //System.out.println("RECEIVED: " + sentence);  
            }
            
            else if(sentence.charAt(0)=='0' && sentence.charAt(6)=='0' && sentence.charAt(7)==':')
            {
               String [] splice=sentence.split(":");
               if(splice[0].equals("0101010"))
               {
                   TrackOnlineUsers temp=TrackOnlineUsers.getInstance();
                   temp.removeUser(new User(splice[1],splice[2]));
               }
                
            }
            
            //
            //InetAddress IPAddress = receivePacket.getAddress();
            //int port = receivePacket.getPort();
            //String capitalizedSentence = sentence.toUpperCase();
            
            else{  
             //   System.out.println("RECEIVED: " + sentence);  
                     String [] splice=sentence.split(":");
            
                     sendData = (splice[0]+":"+splice[1]).getBytes();
                // System.out.println("RECEIVED: " + splice[0] + " "+splice[1]); 
                     sendPacket
                         = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(splice[2]), 6666);
            
        //   System.out.println(receivePacket.getAddress()+" "+receivePacket.getPort());
                     serverSocket.send(sendPacket);
            
            }
        }
    }

}
