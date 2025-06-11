package com.movieflix.movieflix.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserResponse(
        @Schema(type = "long", description = "Codigo do ID do Usuario")
        Long id,
        @Schema(type = "string", description = "Nome do Usuario")
                           String name,
        @Schema(type = "string", description = "Email do Usuario")
                           String email) {
}
