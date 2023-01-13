package io.github.teixeira007.Vendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {
    
    @Bean
    public Animal cachorro(){
        return new Animal() {
            @Override
            public void fazerbarulho() {
                System.out.println("AU Au");
            }
        };
    }

    @Bean
    public Animal gato(){
        return new Animal() {
            @Override
            public void fazerbarulho() {
                System.out.println("Miau");
            }
        };
    }

}
