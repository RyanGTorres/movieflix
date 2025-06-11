package com.movieflix.movieflix.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "O campo nome é obrigatório")  @Schema(type = "string", description = "Nome da categoria") String name) {

}
