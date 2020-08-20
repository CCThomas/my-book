package com.github.ccthomas.mybook.configuration;

import com.github.ccthomas.mybook.service.UserService;
import com.github.ccthomas.mybook.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBookConfiguration {
    private static Logger LOGGER = LoggerFactory.getLogger(MyBookConfiguration.class);

    @Autowired
    private ApplicationArguments args;

//    @Bean
//    public UserService userService() {
//        LOGGER.debug("Creating User Service Bean");
//        for (String arg: args.getSourceArgs()) {
//            if (arg.contains("--userService=")) {
//                LOGGER.debug("Application Arguments contains User Service");
//                String implName = arg.replace("--userService=", "");
//                if (UserServiceExistingDB.class.getSimpleName().equals(implName)) {
//                    LOGGER.info("Using UserServiceExistingDB Implementation");
//                    return new UserServiceExistingDB();
//                } else if (UserServiceImpl.class.getSimpleName().equals(implName)) {
//                    LOGGER.info("Using UserServiceImpl Implementation");
//                    return new UserServiceImpl();
//                } else {
//                    LOGGER.warn("Unknown Implementation give with name={}", implName);
//                    break;
//                }
//            }
//        }
//        LOGGER.debug("Defaulting to UserServiceImpl Implementation");
//        return new UserServiceImpl();
//    }
}
