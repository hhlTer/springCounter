package com.sb.curilisterner.domain.resources;

import javax.persistence.*;

@Entity(name = "counter")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "rc")
    private long requestCount;

    @Column(name = "content")
    private String content;

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id=" + id +
                ", requestCount=" + requestCount +
                ", content='" + content + '\'' +
                '}';
    }
}
