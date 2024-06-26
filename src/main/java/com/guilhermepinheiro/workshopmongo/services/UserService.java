package com.guilhermepinheiro.workshopmongo.services;

import com.guilhermepinheiro.workshopmongo.domain.User;
import com.guilhermepinheiro.workshopmongo.dto.UserDTO;
import com.guilhermepinheiro.workshopmongo.repositories.UserRepository;
import com.guilhermepinheiro.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Not found object"));
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj){
        Optional<User> obj1 = repository.findById(obj.getId());
        User newObj = obj1.orElseThrow(() -> new ObjectNotFoundException("Not found object"));
        updateDate(newObj, obj);
        return repository.save(newObj);
    }

    private void updateDate(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }


    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
