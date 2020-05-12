import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import routes.WebServer;
import config.Configs;
import utils.Constants;

public class App {
    static final Logger appLogger = LoggerFactory.getLogger(App.class.getSimpleName());

    public static void main(String[] args) {
        try {
            Configs.initializeConfigs(Constants.ENV_PRINT_DEBUG);
        } catch (Exception exception) {
            appLogger.error("Missing environment variables, can't start server.");
            System.exit(-3);
        }
        new WebServer();
    }

}
