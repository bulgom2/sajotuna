package com.mes.sajotuna.calculation;

import java.time.LocalDateTime;

public class mmm {

    public static void main(String[] args) {

        LocalDateTime bbb = LocalDateTime.of(2023, 05, 23, 9, 20, 0);

        LocalDateTime aaa = bbb.plusSeconds((int)((100000/1750.0)*3600));

        System.out.println(aaa);

        //1번째 충진 종료 시간 : 2023년 05월 30일 12시 45분 42초
        //1번째 충진 종료 시간 : 2023년 05월 25일 18시 28분 34초

    }
}
