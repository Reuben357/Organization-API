package database;

import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import java.util.Collections;
import java.util.List;

public class NewsDao {
    List<News> news, showNews;
    static final Logger NewDaoLogger = LoggerFactory.getLogger(News.class.getSimpleName());
    private static final Connection databaseConnection = Database.databaseConnection.open();


    public void add(News news){
        try (databaseConnection){
            String insertNewsQuery = "INSERT INTO news (news, department_id) VALUES (:news, :department_id) ";

            int news_id = (int) databaseConnection.createQuery(insertNewsQuery, true)
                    .addParameter("news", news.getNews())
                    .addParameter("department_id", news.getDepartment_id())
                    .executeUpdate()
                    .getKey();

            news.setId(news_id);
        }catch (Exception exception){
            NewDaoLogger.error("Could not save data, error: {}", exception.getLocalizedMessage());
        }
        NewDaoLogger.info("News returned is {}", news.toString());
    }

    public List<News> getAllNews(){
        String getAllNewsQuery = "SELECT * FROM news";
        try(databaseConnection){
            news = databaseConnection.createQuery(getAllNewsQuery).executeAndFetch(News.class);

            if (news == null){
                news = Collections.emptyList();
            }
        }catch (Exception exception){
            NewDaoLogger.error("An error occurred: {}", exception.getLocalizedMessage());
        }
        return news;
    }

    public List<News> getNewsByDepartmentId(int departmentId) {
        String  getNewsByDepartmentId = "SELECT * FROM public.news WHERE department_id = :department_id";
        try(Connection connection = Database.databaseConnection.open()){
            showNews = connection.createQuery(getNewsByDepartmentId)
                    .addParameter("department_id", departmentId)
                    .executeAndFetch(News.class);

            if (showNews == null){
                showNews = Collections.emptyList();
            }
        } catch (Exception exception){
            NewDaoLogger.error("An error occurred: {}", exception.getLocalizedMessage());
        }
        return news;
    }

    public List<News> findById(int id){
        String getNewsById = "SELECT * FROM news WHERE id=:id;";
        try(Connection connection = Database.databaseConnection.open()){
            news = databaseConnection.createQuery(getNewsById)
                    .addParameter("id", id)
                    .executeAndFetch(News.class);
            if (news == null){
                news = Collections.emptyList();
            }
        }catch (Exception exception){
            NewDaoLogger.error("An error occurred: {}", exception.getLocalizedMessage());
        }
        return news;
        }

}


