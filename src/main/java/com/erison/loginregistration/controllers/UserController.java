package com.erison.loginregistration.controllers;

import com.erison.loginregistration.models.User;
import com.erison.loginregistration.models.UserLogin;
import com.erison.loginregistration.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model , @ModelAttribute( "newUser")User newUser,
                       @ModelAttribute("newLogin")User newLogin , HttpSession session){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId!=null){
            return "redirect:/dashboard";
        }
        model.addAttribute("newUser",new User());
        model.addAttribute("newLogin",new UserLogin());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser")User newUser , BindingResult result,
                           Model model,HttpSession session){
        userService.register(newUser,result);
        if (result.hasErrors()){
            model.addAttribute("newLogin",new UserLogin());
            return "index";
        }
        session.setAttribute("loggedInUserId",newUser.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin")UserLogin newLogin,BindingResult result
            ,Model model,HttpSession session){
        User user = userService.login(newLogin,result);
        if (result.hasErrors()){
            model.addAttribute("newUser",new User());
            return "index";
        }
        session.setAttribute("loggedInUserId",user.getId());
        return "redirect:/dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null){
            return "redirect:/";
        }
        User loggedInUser = userService.findUser(loggedInUserId);
        model.addAttribute("user",loggedInUser);

        // this code prevent user going back at dashboard after logout

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        return "dashboard";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpSession session ){
        session.invalidate();
        return "redirect:/";
    }
}
