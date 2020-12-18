package com.rashid.abrar.dto;

import com.rashid.abrar.model.Author;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorGetDTO {
    private List<AuthorDTO> authors;
}
