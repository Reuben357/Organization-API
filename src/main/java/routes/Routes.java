package routes;

import com.google.gson.Gson;
import config.Configs;
import database.Database;
import database.DepartmentDao;
import database.NewsDao;
import database.UserDao;
import exceptions.APIException;
import models.Department;
import models.News;
import models.User;
import utils.Constants;

import java.util.HashMap;

import static spark.Spark.*;

public class Routes {

    public static void main(String[] args) {
        port( getHerokuAssignedPort());
        UserDao userDao = new UserDao();
        NewsDao newsDao = new NewsDao();
        DepartmentDao departmentDao = new DepartmentDao();

        Gson gson = new Gson();

        get("/users", "application/json",(req, res) -> {
            if(userDao.getAll().size() < 1){
                throw new APIException(404, "The requested resource was not found.");
            }
            res.type("application/json");
            return gson.toJson(userDao.getAll());
        });
        get("/news", "application/json",(req, res) ->{
            if(newsDao.getAllNews().size() < 1){
                throw new APIException(404,"The requested resource was not found.");
            }
            res.type("application/json");
            return gson.toJson(newsDao.getAllNews());
        });
        get("/departments", "application/json",(req, res) ->{
            res.type("application/json");
            return gson.toJson(departmentDao.getAllDepartments());
        });


        post("/users/new", "application/json", (req, res)->{
            User user = gson.fromJson(req.body(), User.class);
            if(user == null || user.getUsername() == null){
                throw new APIException(404,"The requested resource was not found.");
            }
            userDao.add(user);
            res.type("application/json");
            res.status(201);
            return gson.toJson(user);
        });
        post("/news/new", "application/json", (req, res)->{
            News news = gson.fromJson(req.body(), News.class);
            if(news == null || news.getNews() == null){
                throw new APIException(404, "The requested resource was not found.");
            }
            newsDao.add(news);
            res.type("application/json");
            res.status(201);
            return gson.toJson(news);
        });
        post("/department/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            if (department == null){
                throw new APIException(404, "The requested resource was not found.");
            }
            departmentDao.add(department);
            res.type("application/json");
            res.status(201);
            return gson.toJson(department);
        });


        exception(APIException.class, (exception, request, response) ->{
            HashMap<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", exception.getStatusCode());
            jsonMap.put("errorMessage", exception.getMessage());
            response.type("application/json");
            response.status(exception.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });

    }

    static int getHerokuAssignedPort() {
        if (System.getenv("PORT") != null) {
            Constants.DEV = false;
            return Integer.parseInt(System.getenv("PORT"));
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            Constants.DEV = false;
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000;
    }

    public static void initializeFileRoutes() {
    }
}

