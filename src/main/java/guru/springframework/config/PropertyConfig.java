package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
// Can take in a list.
@PropertySources({
        @PropertySource("classpath:datasource.properties"),
        @PropertySource("classpath:jms.properties")})
public class PropertyConfig {


    // Not a good use case, but an example:
    @Autowired
    Environment env;

    // This needs evaluated as an expression.
    @Value("${guru.username}")
    String user;
    @Value("${guru.password}")
    String password;
    @Value("${guru.dburl}")
    String url;


    //JMS Properties:
    @Value("${guru.jms.username}")
    String jmsUsername;

    @Value("${guru.jms.password")
    String jmsPassword;

    @Value("${guru.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource(){
        System.out.println("inside FakeDataSource Bean Construction ");
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(user);
//        fakeDataSource.setUser(env.getProperty("USERNAME"));

        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker jmsBroker = new FakeJmsBroker();
        jmsBroker.setJmsUsername(jmsUsername);
        jmsBroker.setJmsPassword(jmsPassword);
        jmsBroker.setJmsUrl(jmsUrl);
        return jmsBroker;
    }


    // This reads the file for us.
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        System.out.println("Inside PlaceholderConfigurer");
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }

}
