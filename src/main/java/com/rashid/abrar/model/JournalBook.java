package com.rashid.abrar.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import static com.rashid.abrar.util.Constants.*;

@Entity
@Data
@DiscriminatorValue(value = "journal")
@JsonAutoDetect
public class JournalBook extends Book {

    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getType() {
        return JOURNAL;
    }
}
