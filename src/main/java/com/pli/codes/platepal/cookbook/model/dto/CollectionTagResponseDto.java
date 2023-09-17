package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.CollectionTag;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link CollectionTag}
 */
@AllArgsConstructor
@Getter
public class CollectionTagResponseDto implements Serializable {

    private final Long idCollectionId;
    private final String idTagTitle;
}
