package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(
        @Schema(type = "string", description = "Nome do Usuario no Login")
        String name,
        @Schema(type = "string", description = "Senha do Usuario no Login")
        String password) {
}
