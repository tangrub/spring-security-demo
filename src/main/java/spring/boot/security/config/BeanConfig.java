package spring.boot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import spring.boot.security.manager.MyAccessDecisionManager;
import spring.boot.security.manager.MyVoter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tangrubei on 2018/4/3.
 */
@Configuration
public class BeanConfig {
    @Bean(name = "voter")
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(new MyVoter() );
        return new UnanimousBased(decisionVoters);
    }

    @Bean(name = "manager")
    public AccessDecisionManager voter(){
        return new MyAccessDecisionManager();
    }

}
