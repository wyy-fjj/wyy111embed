
package com.embed1.wyyembed.controller;

        import com.embed1.wyyembed.mapper.UserMapper;
        import com.embed1.wyyembed.model.User;
//import com.sun.deploy.net.HttpResponse;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.util.Map;
        import java.util.UUID;

@Controller
public class logincontroller {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/checkuser")
    public String checkuser(HttpServletRequest request,
                            HttpServletResponse response,
                            Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        User user= userMapper.checkuser(username,password);
        if(user!=null){
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userMapper.updateUser(user);
            response.addCookie(new Cookie("token",token));
            session.setAttribute("loginUser",user);
            map.put("msg0","欢迎"+username);
            map.put("user",user);
            //model.addAttribute("user",username);

            return "/index";
        }else {
            map.put("msg","login fail");
            return "/login";
        }
    }
}
