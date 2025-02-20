package com.guilhermels.crud.mappers;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.entities.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity toEntity(ProductRequestDto productRequestDto);

    ProductResponseDto toDto(ProductEntity productEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductRequestDto productRequestDto, @MappingTarget ProductEntity productEntity);
}
