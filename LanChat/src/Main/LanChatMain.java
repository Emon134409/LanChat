/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Client.LanChatClient;
import GUI.LanChatGUI;
import java.io.IOException;

/**
 *
 * @author Emon
 */
public class LanChatMain {
    
    public static void main(String[] args) throws IOException
    {
        
        
         SetUPServerClient.getInstance().run();
         new LanChatGUI().setVisible(true);
        //System.out.println(sIP);
        
        
    }
    
}
