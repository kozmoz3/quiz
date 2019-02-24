package com.quizwish.quiz.component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.quizwish.quiz.entity.Contrato;

@Component("contratoscomponent")
public class ContratosComponent {
	
	public boolean hasAny(List<Contrato> contrato) {
		List<Contrato> lstcontrato = contrato.stream()
				.filter(predicate->predicate.getFechavence().compareTo(new Date()) <= 0)
				.collect(Collectors.toList());
		return lstcontrato.isEmpty();
	}
	
}
