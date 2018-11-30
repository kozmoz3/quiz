package com.quizwish.quiz.sesion

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private AppUserDAO appUserDAO
 
    @Autowired
    private AppRoleDAO appRoleDAO
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	AppUser appUser = this.appUserDAO.findUserAccount( userName )
 
        if ( appUser.getUserName() == (null))
            throw new UsernameNotFoundException("User: " + userName + " was not found in the database")
 
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId()) 
        List<GrantedAuthority> grantList = new ArrayList<>()
		
        if (roleNames != null)
            for (String role : roleNames)
                grantList.add( new SimpleGrantedAuthority(role) )
 
        return (UserDetails) new User(appUser.getUserName(),  appUser.getEncrytedPassword(), grantList);
    } 
}