package com.quizwish.quiz.sesion

import javax.sql.DataSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.support.JdbcDaoSupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository("appRoleDAO")
@Transactional
class AppRoleDAO extends JdbcDaoSupport{
	
	@Autowired
	public AppRoleDAO(DataSource dataSource) {
		this.setDataSource(dataSource)
	}
	
	public List<String> getRoleNames(Long userId) {
		String sql = "select r.descripcion from user u inner join rol r on(r.idrol = r.idrol) where u.iduser = ? "
		return this.getJdbcTemplate().queryForList(sql, [ userId ] as Object[], String.class) 
	}

}
