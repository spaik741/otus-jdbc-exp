package otus.orm.exp.service.io;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfig {
    @Bean
    public IOService ioService() {
        return new ConsoleService(System.in, System.out);
    }
}