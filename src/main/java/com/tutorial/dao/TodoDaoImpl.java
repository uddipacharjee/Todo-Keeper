package com.tutorial.dao;

import com.tutorial.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addTodo(Todo todo) {

        int lastID=totalRowCount()>0 ? getMaxIdFromTable():0;

        lastID++;
        System.out.println("Add :"+todo);
        String sql="insert into TODO (id,user,description,targetDate,isDone) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, lastID,todo.getUser(),todo.getDescription(),todo.getTargetDate(),todo.isDone());
    }
    private int totalRowCount(){
        String sql="select count(*) from TODO";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    private int getMaxIdFromTable() {
        int id;
        System.out.println("start");
        String sql="SELECT MAX(id) FROM TODO";
        id=jdbcTemplate.queryForObject(sql,new Object[]{},Integer.class);
        return id;
    }

    @Override
    public void updateTodo(Todo todo) {
        String sql="UPDATE TODO SET description= ?,targetDate = ? WHERE id=?";

        jdbcTemplate.update(sql,todo.getDescription(),todo.getTargetDate(),todo.getId());
    }

    @Override
    public boolean deleteTodo(int id) {
        String sql="DELETE FROM TODO WHERE id = ?";
        Object[] args=new Object[]{id};
        return jdbcTemplate.update(sql,args)==1;
    }

    @Override
    public List<Todo> retrieveTodos(String user) {
        List<Todo> todos=new ArrayList<Todo>();
        jdbcTemplate.query("select * from TODO where user ='"+user+"'", new ResultSetExtractor<List<Todo>>() {
            @Override
            public List<Todo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                while(resultSet.next()){
                    Todo todo=new Todo();
                    todo.setId(resultSet.getInt(1));
                    todo.setUser(resultSet.getString(2));
                    todo.setDescription(resultSet.getString(3));
                    todo.setTargetDate(resultSet.getDate(4));
                    todo.setDone(resultSet.getBoolean(5));

                    todos.add(todo);
                }
                //System.out.println(todos);
                return todos;

            }
        });
        return todos;
    }

    @Override
    public Todo retrieveTodo(int id) {
        String sql="SELECT * FROM TODO WHERE id='"+id+"'";
        Todo todo=jdbcTemplate.queryForObject(sql,new Object[]{}, BeanPropertyRowMapper.newInstance(Todo.class));
        System.out.println("Retrieved :   "+todo);
        return todo;
    }

    @Override
    public void changeTodoStatus(int id) {
        String sql="UPDATE TODO SET isDone= CASE WHEN isDone=0 THEN 1 ELSE 0 END WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
}
