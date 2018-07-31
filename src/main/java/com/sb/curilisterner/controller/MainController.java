package com.sb.curilisterner.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sb.curilisterner.domain.jpa.CountService;
import com.sb.curilisterner.domain.resources.Counter;
import com.sb.curilisterner.service.GsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    @Autowired
    private CountService countService;

    @Autowired
    GsonService gsonService;

    private AtomicLong atomicCounter = new AtomicLong();

    @RequestMapping(method = GET, value = "/readcontent")
    public String outContent() {
        Counter counter = countService.getCounter();
        countService.countIncrement(counter);
        countService.save(counter);
        atomicCounter.incrementAndGet();
        String gsonResult = gsonService.returnGson(counter);
        return gsonResult;
    }

    @RequestMapping(method = GET, value = "/getsessioncount")
        public String outAtomic(){
            return getResult(atomicCounter.get());
        }


    @RequestMapping(method = GET, value = "/getcount")
    public String outCounter() {
        Counter counter = countService.getCounter();
        return getResult(counter.getRequestCount());
    }


    @RequestMapping(method = GET, value = "/postcontent")
    public void postContent(@RequestParam(value = "content", defaultValue = "Default string") String content) {
        atomicCounter.incrementAndGet();
        countService.saveContent(content);
    }

    @RequestMapping(method = GET, value = "/dropcount")
    public void dropCount() {
        atomicCounter.set(0l);
        countService.dropCount();
    }


    private String getResult(long result){
        return String.format("Count of request: %d", result);
    }
}
