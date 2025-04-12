package com.example.springai2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropClass {

    public PropClass(){

    }

    @Value("${OPEN_API_KEY}")
    private String key1;

    @Value("${ANTHROPIC_API_KEY}")
    private String key2;

    @Value("${GEMINI_API_KEY}")
    private String key3;

    public String getKey1(){
        System.out.println(key1);
        return key1;
    }

    public String getKey2(){
        return key2;
    }

    public String getKey3(){
        return key3;
    }

}
