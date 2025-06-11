package com.movieflix.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse (
        @Schema(type = "long", description = "Codigo do Streaming")
        Long id,
        @Schema(type = "string", description = "Nome do Streaming")
        String name) {
}
