package ru.chernov.urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.chernov.urlshortener.repository.UserRepository;
import ru.chernov.urlshortener.service.link.LinkRedisService;
import ru.chernov.urlshortener.service.user.UserService;


@AutoConfigureMockMvc
@SpringBootTest
public class TestDependencies {

    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected LinkRedisService linkRedisService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected PasswordEncoder passwordEncoder;

}
