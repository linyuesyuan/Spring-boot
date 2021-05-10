package com.example.demo;

public class MyStarter {
    private MyStarterProperties myStarterProperties;
    public MyStarter(){    }

    public MyStarter(MyStarterProperties myStarterProperties) {
        this.myStarterProperties = myStarterProperties;
    }
    public String print(){
        System.out.println("參數：" + myStarterProperties.getParameter());
        String s = myStarterProperties.getParameter();
        return s;
    }
}
