package app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@Controller
public class App {

  public static void main(String[] args) {
    new SpringApplicationBuilder(App.class).web(true).run(args);
  }

}