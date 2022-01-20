package com.springframe.contactapp.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.springframe.contactapp.domain.Contact;

public class ContactSpecification implements Specification<Contact>{

	private static final long serialVersionUID = 1L;
	
	private Contact filter;
	 
    public ContactSpecification(Contact filter) {
        this.filter = filter;
    }

	@Override
	public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate p = criteriaBuilder.disjunction();
		 
        if (filter.getName() != null) {
            p.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
        }
 
        if (filter.getPhone()!= null) {
            p.getExpressions().add(criteriaBuilder.like(root.get("phone"), "%" + filter.getPhone() + "%"));
        }
 
        return p;
	}

}
