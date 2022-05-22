package com.Zopa.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    /*
    Hooks Class is to create a structure in case we need to execute some actions before and after each test
    like creating connection to retrieve some data in order to assert etc.
    */

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
    }
}
