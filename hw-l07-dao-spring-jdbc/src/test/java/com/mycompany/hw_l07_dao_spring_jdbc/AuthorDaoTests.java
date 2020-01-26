package com.mycompany.hw_l07_dao_spring_jdbc;

import com.mycompany.hw_l07_dao_spring_jdbc.dao.AuthorDaoJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoTests {
}
