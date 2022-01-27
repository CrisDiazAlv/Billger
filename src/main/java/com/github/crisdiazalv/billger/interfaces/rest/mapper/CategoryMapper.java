package com.github.crisdiazalv.billger.interfaces.rest.mapper;

import com.github.crisdiazalv.billger.domain.model.Category;
import com.github.crisdiazalv.billger.interfaces.rest.dto.category.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(Category category);

    Category toCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> toDTOList(List<Category> categories);

}
