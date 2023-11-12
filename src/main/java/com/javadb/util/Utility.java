package com.javadb.util;

import java.util.Random;

public class Utility {
  
  public static long generateAccountNo() {
    Random random = new Random();
    double randNum = random.nextDouble() * 9_000_000_000L;
    return (long) Math.floor(randNum);
  }
}
