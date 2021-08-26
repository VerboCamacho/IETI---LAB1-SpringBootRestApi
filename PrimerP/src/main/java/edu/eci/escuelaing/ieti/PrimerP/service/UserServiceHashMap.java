package edu.eci.escuelaing.ieti.PrimerP.service;
import edu.eci.escuelaing.ieti.PrimerP.data.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class UserServiceHashMap implements edu.eci.escuelaing.ieti.PrimerP.service.UserService {

    private HashMap<String,User> userHash = new HashMap<String,User>();

    @Override
    public User create(User user) {
        User newUser=new User(user.getId(),user.getName(), user.getEmail(), user.getLastName());
        userHash.put(newUser.getId(), newUser);

        return newUser;
    }

    @Override
    public User findById(String id) {
        User matchUser=null;
        matchUser=userHash.get(id);
        return matchUser;
    }

    @Override
    public List<User> all() {
        ArrayList<User> list=new ArrayList<User>(userHash.values());
        return list;
    }

    @Override
    public void deleteById(String id) {
        userHash.remove(id);
    }

    @Override
    public User update(User user, String userId) {
        userHash.put(userId,user);
        return user;
    }
}
