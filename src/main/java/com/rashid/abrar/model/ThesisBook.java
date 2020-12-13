package com.rashid.abrar.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import static com.rashid.abrar.util.Constants.*;

@Entity
@Data
@DiscriminatorValue(value = "thesis")
public class ThesisBook extends Book {

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getType() {
        return THESIS;
    }
}
