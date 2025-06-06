package com.movieflix.movieflix.service;

import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Optional<Streaming> findById(Long id){
        return streamingRepository.findById(id);
    }

    public Streaming saveStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public void deleteId(Long id){
        streamingRepository.deleteById(id);
    }
}
