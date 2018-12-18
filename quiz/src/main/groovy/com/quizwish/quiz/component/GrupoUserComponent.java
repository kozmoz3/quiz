package com.quizwish.quiz.component;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Grupousuario> setGrupoUser(Grupo grupo, List<Grupousuario> grupousuario, User user) {
		List<Grupousuario> lstguser = new ArrayList<>();
		grupousuario.forEach( item -> {
			item.setIdgrupo(grupo);
			item.setIduser(user);
			item.setEstatus(true);
			
			Grupousuario grupouser = grupouserService.findAllByIdStudent( item.getIdstudent() );
			if(grupouser != null && grupouser.getIdrelaciongu() != null) {
				grupouser.setEstatus(item.getEstatus());
				lstguser.add(grupouser);
			}else lstguser.add(item);
		});
		return lstguser;
	}
	

}
