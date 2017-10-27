/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Emon
 */

public class User {
    
    String name;
    String IP;
    
    public User(String n,String ip)
    {
        this.name=n;
        this.IP=ip;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getIP()
    {
     //   return "10.220.62.5";
        // System.out.println(this.IP);
          return this.IP;
    }
        
}
