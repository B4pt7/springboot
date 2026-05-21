package com.hesias.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private Long libraryId;
}