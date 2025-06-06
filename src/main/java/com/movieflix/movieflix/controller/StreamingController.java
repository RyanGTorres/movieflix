package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.mapper.StreamingMapper;
import com.movieflix.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity <List<StreamingResponse>> findALl(){
       List<StreamingResponse> streaming = streamingService.findAll()
               .stream()
               .map(StreamingMapper::toStreamingResponse)
               .toList();
       return ResponseEntity.ok(streaming);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request){
        Streaming newStreming = StreamingMapper.toStreaming(request);
        Streaming streamingSaved = streamingService.saveStreaming(newStreming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(streamingSaved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        streamingService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
