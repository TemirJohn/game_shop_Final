package com.kz.game_shop.Controller;

import com.kz.game_shop.Service.ReviewService;
import com.kz.game_shop.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/game/{gameId}")
    public ResponseEntity<?> getReviewsByGame(@PathVariable Long gameId) {
        return new ResponseEntity<>(reviewService.getReviewsByGame(gameId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.addReview(reviewDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(id, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
