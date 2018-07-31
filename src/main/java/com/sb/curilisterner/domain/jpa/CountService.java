package com.sb.curilisterner.domain.jpa;

import com.sb.curilisterner.domain.repository.CountRepository;
import com.sb.curilisterner.domain.resources.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

    @Autowired
    private CountRepository countRepository;

    public Counter getCounter(){
        if (countRepository.existsById(1l)){
            return countRepository.getOne(1l);
        } else {
            return defoultCounter();
        }
    }

    public void countIncrement(Counter counter){
//        Counter counter = getCounter();
        long c = counter.getRequestCount();
        c++;
        counter.setRequestCount(c);
    }

    public void saveContent(String content){
        Counter counter = getCounter();
        counter.setContent(content);
        countIncrement(counter);
        saveCounter(counter);
    }

    private void saveCounter(Counter counter){
        counter.setId(1);
        countRepository.save(counter);
    }

    public long getCount(){
        if (countRepository.existsById(1l)) {
            return countRepository.getRCounter();
        } else {
            return 0;
        }
    }

    private Counter defoultCounter(){
        Counter counter = new Counter();
        counter.setId(1l);
        counter.setRequestCount(1l);
        counter.setContent("Default string");
        return counter;
    }

    public void dropCount() {
        Counter counter = getCounter();
        counter.setRequestCount(0l);
        countRepository.save(counter);
    }

    public void save(Counter counter) {
        if (!countRepository.existsById(1l)){
            counter = defoultCounter();
        }
        saveCounter(counter);
    }
}
