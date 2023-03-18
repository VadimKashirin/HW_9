package com.example.hw_9.DAO;

import com.example.hw_9.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component//  чтобы генирировать бин данногокласса
public class UserDAO {
    private int id;
    // получение списка всех пользователей
    private List<User> userList;
    {
        userList = new ArrayList<>();
    }
    //Метод добавления в лист клиента
   public void addUser(User newUser){
        userList.add(newUser);// добавляем в лист
       newUser.setId(++id);// при добавления пошльзователь увеличиваем id
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    //Получения пользователя и Arraylist по id
    public User getUserId (int id){
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    //Обновление данных (редактирование данных )
    public void userUpdate(int id,User user){
            User userUpdate = getUserId(id);
            userUpdate.setLastName(user.getLastName());
            userUpdate.setFirstName(user.getFirstName());
            userUpdate.setPatronymic(user.getPatronymic());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setAge(user.getAge());
            userUpdate.setPhoneNumber(user.getPhoneNumber());
    }
    //Метод удаления элемента  из ArrayList
    public  void userDelete(int id){
        userList.removeIf(user -> user.getId() == id);
    }
}
