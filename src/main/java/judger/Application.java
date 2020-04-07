package judger;

import judger.judge.ShellCommandProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(ShellCommandProperties.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
