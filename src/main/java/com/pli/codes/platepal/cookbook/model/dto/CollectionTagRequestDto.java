package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.CollectionTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link CollectionTag}
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CollectionTagRequestDto {

    private Long idCollectionId;
    private String idTagTitle;
}
