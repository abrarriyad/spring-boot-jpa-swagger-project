package com.rashid.abrar.entity;

import com.rashid.abrar.dto.AuthorDTO;
import com.rashid.abrar.model.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GetAPIAuthor {

    List<Author> authors;
}
