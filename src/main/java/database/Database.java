package database;


import config.Configs;
import org.sql2o.Sql2o;

public class Database {

public static Sql2o databaseConnection = new Sql2o(Configs.DB_URI, Configs.DB_USER, Configs.DB_PASS);
}
