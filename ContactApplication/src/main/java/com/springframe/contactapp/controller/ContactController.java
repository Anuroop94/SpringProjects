package com.springframe.contactapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframe.contactapp.domain.Address;
import com.springframe.contactapp.domain.Contact;
import com.springframe.contactapp.service.ContactService;

@RestController
@RequestMapping("/")
public class ContactController {
	
	private final int ROW_PER_PAGE = 5;
	
	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value = "/contacts",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> findAll(@RequestParam(value = "page",defaultValue = "1") int pageNumber,
			@RequestParam(required = false) String name){
		if(StringUtils.isEmpty(name)) {
			return ResponseEntity.ok(contactService.findAll(pageNumber, ROW_PER_PAGE));
		}
		else {
			return ResponseEntity.ok(contactService.findAllByName(name, pageNumber, ROW_PER_PAGE));
		}
	}
	
	@GetMapping(value = "/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> findContactById(@PathVariable Long contactId){
		    System.out.println("Hello Welcome");
			Contact book = contactService.findById(contactId).get();
			return ResponseEntity.ok(book);
	}
	
	@PostMapping(value = "/contacts")
	public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) throws URISyntaxException{
		Contact newContact = contactService.save(contact);
		return ResponseEntity.created(new URI("/api/contacts/"+newContact.getId())).body(contact);
	}
	
	@PutMapping(value = "/contacts/{contactId}")
	public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact,
			@PathVariable Long contactId){
		contact.setId(contactId);
		contactService.update(contact);
		return ResponseEntity.ok().build();
		
	}
	
	@PatchMapping(value = "/contacts/{contactId}")
	public ResponseEntity<Contact> updateAddress(@RequestBody Address address,
			@PathVariable Long contactId){
		contactService.updateAddress(contactId, address);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping(value = "/contacts/{contactId}")
	public ResponseEntity<Void> deleteById(@PathVariable Long contactId){
		contactService.deleteById(contactId);
		return ResponseEntity.ok().build();
	}
}
