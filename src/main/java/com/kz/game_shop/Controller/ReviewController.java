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

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.addReview(reviewDto), HttpStatus.OK);
    }
}
