package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.MovieRequest;
import com.movieflix.movieflix.controller.response.MovieResponse;
import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.mapper.MovieMapper;
import com.movieflix.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento de filmes")
public class MovieController {
    private final MovieService movieService;

    @Operation(summary = "Criar filmes", description = "Método para criar filmes")
    @ApiResponse(responseCode = "200", description = "Filme criado com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @ApiResponse(responseCode = "400", description = "Não foi possivel criar filme")
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest){
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }

    @Operation(summary = "Listar todos os filmes", description = "Método para listar todos os filmes")
    @ApiResponse(responseCode = "200", description = "Filmes listados com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @ApiResponse(responseCode = "404", description = "Filmes não encontrados")
    @GetMapping
    public ResponseEntity <List<MovieResponse>> findAll(){
        return ResponseEntity.ok(movieService.findAll().stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @Operation(summary = "Encontrar Filme", description = "Metodo para encontrar filme pelo ID" ,
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme encontrado com sucesso!",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @ApiResponse (responseCode = "404", description = "Filme não encontrado!")
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar filme", description = "Método para deletar filme pelo ID")
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado para exclusão")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable Long id){
       Optional<Movie> optMovie = movieService.findById(id);
       if (optMovie.isPresent()){
           movieService.deleteById(id);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar filme", description = "Método para atualizar um filme existente")
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @ApiResponse(responseCode = "404", description = "Filme não encontrado para atualização")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
        return movieService.update(id , MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar filmes por categoria", description = "Método para listar filmes pela categoria")
    @ApiResponse(responseCode = "200", description = "Filmes listados com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class)))
    )
    @ApiResponse(responseCode = "404", description = "Nenhum filme encontrado para a categoria")
    @GetMapping("/search")
    public ResponseEntity <List<MovieResponse>> findByCategory (@RequestParam Long category){
        return ResponseEntity.ok(movieService.findMovieByCategories(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }
}

