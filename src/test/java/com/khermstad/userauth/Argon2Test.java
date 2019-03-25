package com.khermstad.userauth;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.junit.Test;

public class Argon2Test {

    @Test
    public void testArgon(){
        Argon2 argon2 = Argon2Factory.create();
        System.out.println(argon2.hash(20, 65536, 10, "Pdigms1986"));
    }



}
