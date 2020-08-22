package server;

import java.util.Properties;

public class ServerProperties extends LoadProperties {
    private static final Properties serviceProperties;

    static {
        serviceProperties = getServiceProperties();
    }

    public static final String IP_ADDRESS = serviceProperties.getProperty("server.ip");
    public static final String PORT = serviceProperties.getProperty("server.port");
}
