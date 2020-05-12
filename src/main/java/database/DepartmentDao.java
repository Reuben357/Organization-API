package database;

import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;

import java.util.Collections;
import java.util.List;

public class DepartmentDao {
    List<Department> departments, showDepartment;
    static final Logger DepartmentDaoLogger = LoggerFactory.getLogger(Department.class.getSimpleName());
    private static final Connection databaseConnection = Database.databaseConnection.open();


    public void add(Department department) {
        try (databaseConnection) {
            databaseConnection.getJdbcConnection().setAutoCommit(false);
            String insertDepartmentQuery = "INSERT INTO public.departments(name, description, total_employees)" +
                    "VALUES (:name, :description, :total_employees)";

            int department_id = (int) databaseConnection.createQuery(insertDepartmentQuery, true)
                    .addParameter("name", department.getName())
                    .addParameter("description", department.getDescription())
                    .addParameter("total_employees", department.getTotal_employees())
                    .executeUpdate()
                    .getKey();

            databaseConnection.commit();

            department.setId(department_id);

        } catch (Exception exception) {
            DepartmentDaoLogger.error("Could not save data, error:{}", exception.getLocalizedMessage());
        }
        DepartmentDaoLogger.info("Department returned is {}", department.toString());

    }

    public List<Department> getAllDepartments(){
        String getAllDepartmentsQuery = "SELECT * FROM departments";
        try(databaseConnection){
            departments = databaseConnection.createQuery(getAllDepartmentsQuery).executeAndFetch(Department.class);

                if (departments == null){
                    departments = Collections.emptyList();
                }
        }catch (Exception exception){
            DepartmentDaoLogger.error("An error occurred: {}", exception.getLocalizedMessage());
        }
        return departments;
    }

    public List<Department> getDepartmentById(int departmentId) {
        String  getDepartmentById = "SELECT * FROM public.departments WHERE id = :department_id";
        try(Connection connection = Database.databaseConnection.open()){
            showDepartment = connection.createQuery(getDepartmentById)
                    .addParameter("department_id", departmentId)
                    .executeAndFetch(Department.class);

            if (showDepartment == null){
                showDepartment = Collections.emptyList();
            }
        } catch (Exception exception){
            DepartmentDaoLogger.error("An error occurred: {}", exception.getLocalizedMessage());
        }
        return departments;
    }
}
