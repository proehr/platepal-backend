package com.pli.codes.platepal.cookbook.model.dto;

import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Recipe}
 */
@AllArgsConstructor
@Getter
public class RecipeDtoSmall {

    private final Long id;
    private final String title;
    private final Duration totalTime;
    private final List<String> imagePaths;
}
