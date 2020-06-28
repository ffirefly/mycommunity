package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @RequestMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length != 0){
            for(int i = 0;i < cookies.length;i++){
                if(cookies[i].getName().equals("token")){
                    String token = cookies[i].getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null ){
                        request.getSession().setAttribute("user",user);
                        break;
                    }

                }

            }
        }

/*        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null )
                    request.getSession().setAttribute("user",user);
            }
            break;
        }*/
        return "index";
    }
}