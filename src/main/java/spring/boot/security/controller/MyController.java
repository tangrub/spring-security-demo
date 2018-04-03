package spring.boot.security.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tangrubei on 2018/4/2.
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class MyController {

    @GetMapping(value = "index")
    @ResponseBody
    public String index() {
        return "hello!";
    }


    @GetMapping(value = "index2")
    @ResponseBody
    public String index2() {
        return "hello2!";
    }


    @GetMapping(value = "login")
    @ResponseBody
    public void Login(HttpServletRequest request,@RequestParam String role){
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_"+role);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("aaa", "bbbb", authorities);
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        System.out.println("this is MyFilter,url :" + request.getRequestURI());
    }
}


