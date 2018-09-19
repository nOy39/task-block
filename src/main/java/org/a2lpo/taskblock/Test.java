package org.a2lpo.taskblock;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Test {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now().with(LocalTime.MAX);
        System.out.println(localDateTime);
    }
}
