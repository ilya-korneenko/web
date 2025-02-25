package com.example.web_aplication.mapper;

import com.example.web_aplication.dto.LikeDTO;
import com.example.web_aplication.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    LikeMapper INSTANCE = Mappers.getMapper(LikeMapper.class);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    Like toEntity(LikeDTO likeDTO);

    LikeDTO toDTO(Like like);
}
