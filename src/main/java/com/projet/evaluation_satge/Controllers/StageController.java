package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.Entities.Stage;
import com.projet.evaluation_satge.Services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
@CrossOrigin("*")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return new ResponseEntity<>(stages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable int id) {
        Optional<Stage> stage = stageService.getStageById(id);
        return stage.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        Stage savedStage = stageService.saveStage(stage);
        return new ResponseEntity<>(savedStage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable int id, @RequestBody Stage stage) {
        Optional<Stage> existingStage = stageService.getStageById(id);
        if (existingStage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stage.setId(id);
        Stage updatedStage = stageService.saveStage(stage);
        return new ResponseEntity<>(updatedStage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable int id) {
        Optional<Stage> existingStage = stageService.getStageById(id);
        if (existingStage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stageService.deleteStage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}