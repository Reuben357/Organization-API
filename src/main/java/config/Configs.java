package config;

import lombok.extern.slf4j.Slf4j;
import org.ini4j.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

@Slf4j
public class Configs {
    public static String DB_HOST;
    public static String DB_USER;
    public static String DB_PASS;
    public static String DB_URI;
    public static Integer PORT;
    static final Logger configsLogger = LoggerFactory.getLogger(Configs.class.getSimpleName());

    // giving context on what is running
    public static void initializeConfigs(boolean debug) throws Exception {

        try {
            // define file path to config file
            String projectDirectory = System.getProperty("user.dir");
            File developmentConfigFile = new File(projectDirectory + "/src/main/java/config/development.ini");
            Ini config = new Ini(developmentConfigFile);

            // get configs
            String dbHost = config.get("DATABASE", "host");
            String dbUser = config.get("DATABASE", "username");
            String dbPort = config.get("DATABASE", "port");
            String dbName = config.get("DATABASE", "database");
            String dbPassword = config.get("DATABASE", "password");
            int applicationPort = config.get("APP", "port", int.class);

            DB_HOST = dbHost;
            DB_USER = dbUser;
            DB_PASS = dbPassword;
            PORT = applicationPort;
            DB_URI = buildDatabaseURI(dbHost, dbPort, dbName);

            // validate fields
            checkFields();

            if (debug) {
                printConfigs(DB_HOST, DB_USER, PORT);
            }
            configsLogger.info("All settings loaded successfully");
        } catch (Exception exception) {
            configsLogger.error("Configs could not be loaded: ", exception);
        }
    }

    private static void printConfigs(String host, String databaseUser, int applicationPort){
        configsLogger.info("Using the following configs: \n" +
                "DATABASE HOST: " + host + "\n" +
                "DATABASE USER: " + databaseUser + "\n" +
                "APPLICATION PORT: " + applicationPort + "\n");
    }


    private static void checkFields() throws Exception {
        if (("" + DB_USER + DB_HOST + DB_PASS + PORT ).contains("null")) {
            configsLogger.error("unset variable");
            throw new Exception();
        }
    }

    private static String buildDatabaseURI(String host,
                                           String port,
                                           String databaseName) {
        String databaseURI;
        databaseURI = String.format("jdbc:postgresql://%s:%s/%s", host, port, databaseName);

        return databaseURI;
    }
}
