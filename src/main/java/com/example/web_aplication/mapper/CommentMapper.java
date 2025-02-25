package com.example.web_aplication.mapper;

import com.example.web_aplication.dto.CommentDTO;
import com.example.web_aplication.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "post", ignore = true)        // Игнорируем поле post, так как оно будет установлено вручную

    @Mapping(target = "author", ignore = true)      // Игнорируем поле author, так как оно будет установлено вручную
    Comment toEntity(CommentDTO commentDTO);

    CommentDTO toDTO(Comment comment);
}
