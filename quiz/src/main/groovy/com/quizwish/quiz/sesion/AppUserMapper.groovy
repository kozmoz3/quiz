package com.quizwish.quiz.sesion

import java.sql.ResultSet
import java.sql.SQLException

import org.springframework.jdbc.core.RowMapper

class AppUserMapper implements RowMapper<AppUser> { 
    static final String BASE_SQL = "select idusuario, correo, password from usuario where correo = ?"
 
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppUser( rs.getLong("idusuario"), rs.getString("correo"), rs.getString("password") )
    }
 
}