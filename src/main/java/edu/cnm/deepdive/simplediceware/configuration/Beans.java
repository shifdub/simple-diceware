package edu.cnm.deepdive.simplediceware.configuration;


import java.security.SecureRandom;
import java.util.Random;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
  @Value("${diceware.bundle}")
  private String wordListBundle;

  @Bean
  public Random rng() {
    return new SecureRandom();
  }

  @Bean
  public ResourceBundle bundle() {
    return ResourceBundle.getBundle(wordListBundle);

  }


}
