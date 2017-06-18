package lapr4.red.s3.ipc.n1150385.groupchat.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

/**
 * Created by Ricardo Catalão (1150385)
 */
public class InetUtils {

    public static InetAddress getOwnAddress(){
        InetAddress res = null;
        mainLoop:
        try {
            for(NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())){
                if(!networkInterface.isUp() || networkInterface.isLoopback())
                    continue;
                for(InetAddress address : Collections.list(networkInterface.getInetAddresses())){
                    if (address.isLinkLocalAddress() || address.isAnyLocalAddress() || address.isLoopbackAddress())
                        continue;

                    res = address;
                    break mainLoop;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return res;
    }
}
