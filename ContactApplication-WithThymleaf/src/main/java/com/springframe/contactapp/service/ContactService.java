package com.springframe.contactapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.springframe.contactapp.domain.Address;
import com.springframe.contactapp.domain.Contact;
import com.springframe.contactapp.repository.ContactRepository;
import com.springframe.contactapp.specification.ContactSpecification;


@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	public boolean existsById(Long Id) {
		return contactRepository.existsById(Id);
	}
	
	public Optional<Contact> findById(Long Id) {
		Optional<Contact> contact = contactRepository.findById(Id);
		if(contact==null) {
			throw new RuntimeException("Cannot find the Contact By Id: "+Id);
		}
		else 
			return contact;
	}
	
	public List<Contact> findAll(int pageNumber,int rowPerPage){
		List<Contact> contacts = new ArrayList<Contact>();
		contactRepository.findAll(PageRequest.of(pageNumber-1, rowPerPage)).forEach(contacts::add);
		return contacts;
	}
	
	public List<Contact> findAllByName(String name,int pageNumber,int rowPerPage){
		List<Contact> contacts = new ArrayList<Contact>();
		Contact filter = new Contact();
		filter.setName(name);
		Specification<Contact> spec = new ContactSpecification(filter);
		
		contactRepository.findAll(spec,PageRequest.of(pageNumber-1, rowPerPage)).forEach(contacts::add);
		
		return contacts;
	}
	
	public Contact save(Contact contact) {
		if(contact.getId()!=null && existsById(contact.getId())) {
			throw new RuntimeException("Contact with Id: "+contact.getId()+" Already Exist");
		}
		return contactRepository.save(contact);
	}
	
	public void update(Contact contact) {
		if(!existsById(contact.getId())) {
			throw new RuntimeException("Cannot find Contact By Id: "+contact.getId());
		}
		contactRepository.save(contact);
	}
	
	public void updateAddress(Long id,Address address) {
		Contact contact = findById(id).get();
		contact.setAddress1(address.getAddress1());
		contact.setAddress2(address.getAddress2());
		contact.setAddress3(address.getAddress3());
		contact.setPostalCode(address.getPostalCode());
		contactRepository.save(contact);	
	}
	
	public void deleteById(Long Id) {
		if(!existsById(Id)) {
			throw new RuntimeException("Cannot find the Contact with Id: "+Id);
		}
		contactRepository.deleteById(Id);
		
	}
	
	public Long count() {
		return contactRepository.count();
	}

}
