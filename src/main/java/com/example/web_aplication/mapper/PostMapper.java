package com.example.web_aplication.mapper;

import com.example.web_aplication.dto.PostDTO;
import com.example.web_aplication.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "author", ignore = true)  // Игнорируем автора, чтобы установить его позже
    Post toEntity(PostDTO postDTO);

    PostDTO toDTO(Post post);

}
