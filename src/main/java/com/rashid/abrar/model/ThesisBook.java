package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.Entity;


@Entity
@Data
public class ThesisBook extends Book {

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
