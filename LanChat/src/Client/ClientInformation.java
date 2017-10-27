/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 *
 * @author Emon
 */
public class ClientInformation {
    
    private ClientInformation()
    {
        
    }
    
    public static String getHostIP() throws SocketException
    {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface netint : Collections.list(nets)) {

            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                // String pattern = "(.*)";
                String pattern1 = "^(/)(10)\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                String pattern2 = "^(/)(192)\\." + "(168)\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                String pattern3 = "^(/)(172)\\." + "(1[6-9]|2[0-9]|3[0-1])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                String pattern4 = "^(/)(169)\\." + "(254)\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                        + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                String strInetAddress = inetAddress.toString();
                // boolean  flag= Pattern.compile(pattern).matcher(inetAddress.toString()).matches();
                //System.out.printf("InetAddress: %s\n", );
                if (Pattern.compile(pattern1).matcher(strInetAddress).matches()
                        || Pattern.compile(pattern2).matcher(strInetAddress).matches()
                        || Pattern.compile(pattern3).matcher(strInetAddress).matches()
                        || Pattern.compile(pattern4).matcher(strInetAddress).matches()) {
                    return strInetAddress.substring(1);

                }
            }
        }

        return null;
    }
    
    public static String getHostUserName()
    {
        return System.getProperty("user.name");
    }
}
