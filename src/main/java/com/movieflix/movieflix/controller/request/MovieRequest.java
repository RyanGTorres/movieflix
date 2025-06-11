package com.movieflix.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;


public record MovieRequest (@NotEmpty(message = "O titulo do filme é obrigatório")
                            @Schema(type = "string", description = "Resposta do titulo")
                            String title,
                            @Schema(type = "string", description = "Resposta da descrição do filme")
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            @Schema(type = "date", description = "Resposta da data lançamento do filme")
                            LocalDate releaseDate,
                            @Schema(type = "double", description = "Resposta daAvaliação do filme, ex: 7.8")
                            double rating,
                            @Schema(type = "array", description = "Resposta do Codigo de categoria")
                            List<Long> categories,
                            @Schema(type = "array", description = "Resposta do Codigo de Streaming")
                            List<Long> streamings) {
}
