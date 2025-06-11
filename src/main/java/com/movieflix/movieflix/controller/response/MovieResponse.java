package com.movieflix.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
                            @Schema(type = "long", description = "Codigo do filme")
                            Long id,
                            @Schema(type = "string", description = "Titulo do filme")
                            String title,
                            @Schema(type = "string" , description = "Descrição do filme")
                            String description,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            @Schema(type = "date", description = "Data de lançamento do filme")
                            LocalDate releaseDate,
                            @Schema(type = "double", description = "Avaliação do filme, ex: 7.8")
                            double rating,
                            @ArraySchema(schema = @Schema(implementation = CategoryResponse.class,
                                    description = "Categorias do filme"))
                            List<CategoryResponse> categories,

                            @ArraySchema(schema = @Schema(implementation = StreamingResponse.class,
                                    description = "Plataformas de streaming do filme"))
                            List<StreamingResponse> streamings)
{
}
