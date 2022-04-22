package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    public Long countById(Integer id);
}
