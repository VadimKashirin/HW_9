package com.example.hw_9.controllers;

import com.example.hw_9.DAO.UserDAO;
import com.example.hw_9.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserDAO userDAO;
@Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    // Данный метод позволяет отобразить страницу добавления
    @GetMapping("/add")
    public String userAdd(Model model){
        model.addAttribute("user", new User());
        return "userAdd";
    }
    // Данные метод позволяет принять объект с формы  редактирования и сохранить нового пользователя
    @PostMapping ("/add")
    public String userAdd(@ModelAttribute("user") @Valid User newUser, BindingResult bindingResult){
      if(bindingResult.hasErrors()){
          return "userAdd";
      }
        userDAO.addUser(newUser);
        return "redirect:/user";
    }
    // данный метод позволяет отобразить главную страницу со списком всех пользователей
    @GetMapping("")
    public String index ( Model model){
      model.addAttribute("user",userDAO.getUserList());
      return "user";
    }
    // Данный метод позволяет найти пользователя по айди и вернуть шаблон с информацией
    @GetMapping("/{id}")
    public  String userInfo(Model model, @PathVariable("id") int id){
       model.addAttribute("user",userDAO.getUserId(id));
       return "userInfo";
    }
    //Метод  получать объект редактируемого пользователя
    @GetMapping("/edit/{id}")
    public String userEdit(Model model,@PathVariable("id")int id){
       model.addAttribute("userEdit",userDAO.getUserId(id));
       return "userEdit";
    }
    // Метод позволяет принять данные с формы  редактирвания  пользователя и обновить данные редактируемого пользователя
    @PostMapping("/edit/{id}")
    public String userEditValue(@ModelAttribute("userEdit") User user,@PathVariable("id") int id){
     userDAO.userUpdate(id, user);
     return "redirect:/user/" + id;
    }
    // Удаление пользователя
    @GetMapping("/delete/{id}")
    public String userDalete(@PathVariable("id")int id ){
    userDAO.userDelete(id);
    return "redirect:/user";
    }
}
