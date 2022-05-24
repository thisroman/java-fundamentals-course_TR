package com.bobocode.lesson7;

public class TestMethodService {

    public TestMethodService() {
    }

    public void hello(){
        int i = 100;
        while (i > 0){
            i--;
        }
    }

    @CheckNotNull
    public void gloryToUkraine(Integer count){
        int i = count;
        while (i > 0){
            i--;
        }
    }

    public void gloryToUkraine(Integer count, @CheckNotNull String bount){
        int i = count;
        while (i > 0){
            i--;
        }
        System.out.println(bount);
    }
}
