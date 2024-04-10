package com.guilhermepinheiro.workshopmongo.services;

import com.guilhermepinheiro.workshopmongo.domain.Post;
import com.guilhermepinheiro.workshopmongo.domain.User;
import com.guilhermepinheiro.workshopmongo.repositories.PostRepository;
import com.guilhermepinheiro.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findAll(){
        return repository.findAll();
    }

    public List<Post> findByTitle(String text){
        return repository.findByTitleContainingIgnoreCase(text);
    }


}
