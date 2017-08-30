/*
package com.dzr.mqmock.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatBootstrap {

    public void start(int port){
        try {
            String webappDirLocation = "src/main/webapp/";
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(port);
            tomcat.setBaseDir(System.getProperty("user.home") + "/tomcat." + port);
            tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TomcatBootstrap().start(9090);
    }
}
*/
