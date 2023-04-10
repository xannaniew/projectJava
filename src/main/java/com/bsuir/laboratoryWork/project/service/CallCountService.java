package com.bsuir.laboratoryWork.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class CallCountService {
    private final Object mutex = new Object();
    private static int counter = 0;
    public int incrementCountAndReturnValue(){
        synchronized (mutex){
        counter++;
        log.info("count = " + counter);
        return counter;}
    }
    public int getCounter(){
        synchronized (mutex){
        return counter;}
    }
}
