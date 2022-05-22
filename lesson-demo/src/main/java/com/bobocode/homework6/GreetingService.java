package com.bobocode.homework6;

public class GreetingService {

    public GreetingService() {
    }

    public void hello(){
        int i = 100;
        while (i > 0){
            i--;
        }
    }

    @LogInvocation
    public void gloryToUkraine(){
        int i = 200;
        while (i > 0){
            i--;
        }
    }
}
