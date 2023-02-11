package com.pzj.project.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author rookie
 * @Date 2021/4/22 15:39
 * @Description
 **/
public class IpUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    private static int timeout = 3*1000; // 超时时间
    public static boolean ping(String ipAddress){
        try {
            boolean status = InetAddress.getByName(ipAddress).isReachable(timeout);
            return status;
        } catch (IOException e) {
            return false;
        }
    }
    public static boolean validePort(String location, int port) {
        Socket s = new Socket();
        try {
            SocketAddress add = new InetSocketAddress(location, port);
            s.connect(add, 2000);
            return true;
        } catch (IOException e) {
            return false;
        }finally{
            try {
                s.close();
            } catch (IOException e1) {
            }
        }
    }

    public static boolean validePort(String location) {
        Socket s = new Socket();
        try {
            // 这里的地址有可能有两种形式，一种可能是 http://ip  一种可能是 http://ip:port
            String[] param = location.split(":");
            if(param.length == 2){
                return ping(param[1].replaceAll("\\//",""));
            }else{
                Integer port = Integer.valueOf(location.substring(location.lastIndexOf(":")+1));
                String url = location.split(":")[1].replaceAll("\\//","");
                SocketAddress add = new InetSocketAddress(url, port);
                s.connect(add, 2000);
                return true;
            }
        } catch (IOException e) {
            return false;
        }finally{
            try {
                s.close();
            } catch (IOException e1) {
            }
        }
    }

    public static String execCurl(String url) {
        String[] cmds={"curl",url};
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }

            LOGGER.info("execCurl`s result is:{}",builder.toString());
            return builder.toString();

        } catch (IOException e) {
            LOGGER.error("execCurl:{},an exception occured:{}",cmds,e.getMessage());
            return null;
        }
    }
}
