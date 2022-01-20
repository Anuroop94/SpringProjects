package com.springframe.contactapp.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springframe.contactapp.domain.Contact;


public interface ContactRepository extends PagingAndSortingRepository<Contact,Long>,JpaSpecificationExecutor<Contact>{

}
