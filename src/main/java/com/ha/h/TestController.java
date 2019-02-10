package com.ha.h;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RestController("/")
public class TestController {
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("test")
    public String helloGradle() {
        return "Hello Gradle!";
    }

    @PutMapping("inp")
    public void input(){
        TestModel m = new TestModel();
        m.setAge(18);
        m.setName("HHAH");
//        mongoTemplate.insert(m, "test");
        String query = "INSERT INTO user(name, age) VALUES(?,?)";
        try {
            jdbcTemplate.update(query, m.getName(), m.getAge());

        }catch (Exception e){

        }
    }

    @GetMapping("model")
    public List<TestModel> getModels(){
//        Query qry = new Query();
//        return mongoTemplate.find(qry, TestModel.class, "test");
        String query = "select * from user";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TestModel.class));
    }
}
