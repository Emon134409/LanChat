/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import GUI.LanChatGUI;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emon
 */
public class ReceiveMessage implements Runnable {
    
    private DatagramPacket receivePacket;
    private DatagramSocket clientSocket;
    private byte[] receiveData;
    
    
    public ReceiveMessage() throws SocketException
    {
            
        this.clientSocket = new DatagramSocket(6666);
            
    }
    
    @Override
    public void run() {
        
        try {
            
            this.receiveMsg();
        
        } catch (IOException ex) {
            Logger.getLogger(ReceiveMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void receiveMsg() throws IOException
    {
        
        while(true)
        {
            
                this.receiveData = new byte[2 * 1024];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData()).trim();
                
                LanChatGUI.showReceivedMessage(sentence);


        }
    }

   
    
}
