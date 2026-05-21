package com.hesias.mapper;

import com.hesias.dto.BookDTO;
import com.hesias.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO dto);
}