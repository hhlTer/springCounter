package com.sb.curilisterner.controller;


import com.sb.curilisterner.domain.jpa.CountService;
import com.sb.curilisterner.domain.resources.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    @Autowired
    private CountService countService;

    private AtomicLong atomicCounter = new AtomicLong();

    @RequestMapping(method = GET, value = "/readcontent")
    public String outContent() {
        Counter counter = countService.getCounter();
        countService.countIncrement(counter);
        countService.save(counter);
        return counter.getContent();
    }

    @RequestMapping(method = GET, value = "/getcount")
    public String outCounter() {
        Counter counter = countService.getCounter();
        return String.format("Count of requests: %d", counter.getRequestCount());
    }


    @RequestMapping(method = {POST, GET}, value = "/postcontent")
    public void postContent(@RequestParam(value = "content", defaultValue = "Default string") String content) {
        countService.saveContent(content);
    }

    @RequestMapping(method = {POST, GET}, value = "/dropcount")
    public void dropCount() {
        countService.dropCount();
    }


}
