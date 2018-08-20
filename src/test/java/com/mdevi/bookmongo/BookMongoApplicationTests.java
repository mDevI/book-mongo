package com.mdevi.bookmongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "= false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@ActiveProfiles("test")
public class BookMongoApplicationTests {

    @Test
    public void contextLoads() {
    }

}
