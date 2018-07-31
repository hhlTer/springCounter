package com.sb.curilisterner.domain.jpa;

import com.sb.curilisterner.domain.repository.CountRepository;
import com.sb.curilisterner.domain.resources.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {

    private final long SINGLE_INSTANCE = 1l;
    private final long ZERO = 0l;

    @Autowired
    private CountRepository countRepository;

    public Counter getCounter(){
        if (countRepository.existsById(SINGLE_INSTANCE)){
            return countRepository.getOne(SINGLE_INSTANCE);
        } else {
            return defoultCounter();
        }
    }

    public void countIncrement(Counter counter){
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
        if (countRepository.existsById(SINGLE_INSTANCE)) {
            return countRepository.getRCounter();
        } else {
            return 0;
        }
    }

    private Counter defoultCounter(){
        Counter counter = new Counter();
        counter.setId(SINGLE_INSTANCE);
        counter.setRequestCount(SINGLE_INSTANCE);
        counter.setContent("Default string");
        return counter;
    }

    public void dropCount() {
        Counter counter = getCounter();
        counter.setRequestCount(ZERO);
        countRepository.save(counter);
    }

    public void save(Counter counter) {
        if (!countRepository.existsById(SINGLE_INSTANCE)){
            counter = defoultCounter();
        }
        saveCounter(counter);
    }

}
