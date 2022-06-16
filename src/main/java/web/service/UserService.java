package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public void addUser(User user){
        entityManager.persist(user);
    }

    public List<User> getAllUsers(){
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUser(int id){
        return entityManager.find(User.class, id);
    }

    public void updateUser(User user){
        entityManager.merge(user);
    }

    public void deleteUser(int id){
        entityManager.remove(getUser(id));
    }

}