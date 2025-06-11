package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.mapper.StreamingMapper;
import com.movieflix.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streaming", description = "Recurso responsável pelo gerenciamento das plataformas de streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @Operation(
            summary = "Listar todas as plataformas de streaming",
            description = "Retorna uma lista com todas as plataformas de streaming cadastradas",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista retornada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        List<StreamingResponse> streaming = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streaming);
    }

    @Operation(
            summary = "Cadastrar uma nova plataforma de streaming",
            description = "Cria um novo registro de plataforma de streaming",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "201",
            description = "Plataforma cadastrada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming streamingSaved = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(streamingSaved));
    }

    @Operation(
            summary = "Buscar plataforma de streaming por ID",
            description = "Retorna uma plataforma de streaming correspondente ao ID informado",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Plataforma encontrada com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Plataforma não encontrada"
    )
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Deletar plataforma de streaming",
            description = "Remove a plataforma de streaming correspondente ao ID informado",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
            responseCode = "204",
            description = "Plataforma deletada com sucesso"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Plataforma não encontrada"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        streamingService.deleteId(id);
        return ResponseEntity.noContent().build();
    }
}

