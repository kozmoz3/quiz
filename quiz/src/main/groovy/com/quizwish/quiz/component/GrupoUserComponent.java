package com.quizwish.quiz.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.quizwish.quiz.entity.Grupo;
import com.quizwish.quiz.entity.Grupousuario;
import com.quizwish.quiz.models.User;
import com.quizwish.quiz.services.GroupUserService;

@Component("grupouserComponent")
public class GrupoUserComponent {
	
	@Autowired
	@Qualifier("grupouserService")
	GroupUserService grupouserService;
	
	boolean bandera;
	
	public List<Grupousuario> setGrupoUser(Grupo grupo, List<Grupousuario> grupousuario, User user) {
		List<Grupousuario> lstguser = new ArrayList<>();
		grupousuario.forEach( item -> {
			item.setIdgrupo(grupo);
			item.setIduser(user);
			item.setEstatus(true);
			
			Grupousuario grupouser = getOnlyOneGrupousuario( grupo, item.getIdstudent() );
			if(grupouser != null && grupouser.getIdrelaciongu() != null) {
				grupouser.setEstatus(item.getEstatus());
				lstguser.add(grupouser);
			}else lstguser.add(item);
		});
		return lstguser;
	}
	
	public Grupousuario getOnlyOneGrupousuario(Grupo grupo, Integer idStudent) {
		List<Grupousuario> grupousuario = grupouserService.findAllByIdStudent(idStudent)
			.stream()
			.filter(grupu -> grupu.getIdgrupo().getIdgrupo().equals(grupo.getIdgrupo()))
			.collect(Collectors.toList());
		return grupousuario.isEmpty() ? new Grupousuario() : grupousuario.get(0);
	}
	
	public Map<Integer, Object> getListEditGrupoUser( List<Grupousuario> grupou , List<User> lstUser) {
		Map<Integer, Object> lstUserInGrupo = new HashMap<>();
		lstUser.forEach( user ->{
			bandera = false;
			grupou.forEach( grupousr -> {
				if(grupousr.getIdstudent().equals( user.getIduser() ) && grupousr.getEstatus() ) bandera = true;
			});
			lstUserInGrupo.put(user.getIduser(), new Object[]{ bandera, user });
		});
		return lstUserInGrupo.isEmpty() ? new HashMap<>() : lstUserInGrupo;
	}
	

}
