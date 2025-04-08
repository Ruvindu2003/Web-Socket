package org.example.configuration;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();


        return modelMapper;
    }



}
