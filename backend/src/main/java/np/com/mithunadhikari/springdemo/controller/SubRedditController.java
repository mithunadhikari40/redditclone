package np.com.mithunadhikari.springdemo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.mithunadhikari.springdemo.dto.SubRedditDto;
import np.com.mithunadhikari.springdemo.service.SubRedditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubRedditController {
    private final SubRedditService service;

    @PostMapping
    public ResponseEntity<SubRedditDto> createSubReddit(@RequestBody SubRedditDto subRedditDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(subRedditDto));
    }


    @GetMapping
    public ResponseEntity<List<SubRedditDto>> getAllReddit() {
        List<SubRedditDto> all = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);

    }
}
