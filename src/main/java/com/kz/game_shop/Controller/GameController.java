package com.kz.game_shop.Controller;

import com.kz.game_shop.Service.GameService;
import com.kz.game_shop.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<?> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createGame(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {

        GameDto gameDto = new GameDto();
        gameDto.setTitle(title);
        gameDto.setDescription(description);
        gameDto.setPrice(price);
        gameDto.setCategoryId(categoryId);

        return new ResponseEntity<>(gameService.createGame(gameDto, image), HttpStatus.OK);
    }

    // Реализовано как в StudentController (try-catch)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}