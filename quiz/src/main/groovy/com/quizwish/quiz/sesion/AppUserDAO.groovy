package com.quizwish.quiz.sesion

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.support.JdbcDaoSupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
class AppUserDAO extends JdbcDaoSupport {
 
    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource)
    }
 
    public AppUser findUserAccount(String userName) {
        try {
            return this.getJdbcTemplate()
				.queryForObject( 
					AppUserMapper.BASE_SQL, 
					[userName] as Object[], 
					new AppUserMapper()
				)
				
        } catch (EmptyResultDataAccessException e) {
            return null
        }
    }
 
}
