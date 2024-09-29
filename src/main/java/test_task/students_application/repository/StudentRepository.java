package test_task.students_application.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import test_task.students_application.model.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentRepository {


    private final ApplicationContext context;

    @Autowired
    StudentRepository(ApplicationContext context) {
        this.context = context;
    }

    public Integer saveStudent(Student student) {
        try (Connection con = context.getBean(Connection.class)) {
            String query = "insert into students (first_name, last_name, fathers_name, birth_date, group_name) " +
                    "values (?, ?, ?, ?, ?) returning id";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, student.getFirst_name());
            statement.setString(2, student.getLast_name());
            statement.setString(3, student.getFathers_name());
            statement.setDate(4, Date.valueOf(student.getBirth_date()));
            statement.setString(5, student.getGroup_name());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public boolean deleteById(Integer id) {
        try (Connection con = context.getBean(Connection.class)) {
            String query = "delete from students where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAll() {
        try (Connection con = context.getBean(Connection.class)) {
            String query = "select * from students";
            Statement statement = con.createStatement();
            boolean hasResult = statement.execute(query);
            if (hasResult) {
                ResultSet rs = statement.getResultSet();
                List<Student> result = new LinkedList<>();
                while (rs.next()){
                    Student student = Student.builder()
                            .id(rs.getInt("id"))
                            .first_name(rs.getString("first_name"))
                            .last_name(rs.getString("last_name"))
                            .fathers_name(rs.getString("fathers_name"))
                            .birth_date(rs.getDate("birth_date").toLocalDate())
                            .group_name(rs.getString("group_name"))
                            .build();
                    result.add(student);
                    System.out.println(student);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
