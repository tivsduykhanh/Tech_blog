package com.example.demo.Services;

import com.example.demo.models.Contact;
import com.example.demo.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repo;

    public List<Contact> listAll() {
        return (List<Contact>) repo.findAll();
    }

    public Contact save(Contact contact) {
        repo.save(contact);
        return contact;
    }

    public Contact get(Integer id) throws PostNotFoundException {
        Optional<Contact> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PostNotFoundException("Could not find any contact with ID " + id);
    }

    public void delete(Integer id) throws PostNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count ==0) {
            throw new PostNotFoundException("Could not find any contact with ID " + id);
        }
        repo.deleteById(id);
    }
}
