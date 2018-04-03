package spring.boot.security.manager;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tangrubei on 2018/4/3.
 */
public class MyVoter implements AccessDecisionVoter {

    private static Map urlMap;

    static {
        urlMap = new HashMap();
        urlMap.put("/index", "ROLE_Admin");
        urlMap.put("/login", "permitAll");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {
        String url = ((FilterInvocation) o).getRequestUrl();
        if (url.indexOf("?") != -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        String needRole = (String) urlMap.get(url);
        if ("permitAll".equals(needRole)) {
            return ACCESS_GRANTED;
        } else {
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.trim().equals(ga.getAuthority().trim())) {
                    return ACCESS_GRANTED;
                }
            }
        }
        return ACCESS_DENIED;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }
}
