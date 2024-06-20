package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.PlatformDto;
import ru.ivanov.gaming_enjoyment.queries.IdsQuery;
import ru.ivanov.gaming_enjoyment.services.intrf.PlatformService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;

    @GetMapping("/all")
    public ResponseEntity<List<PlatformDto>> getAllPlatforms() {
        return ResponseEntity.ok(platformService.getAllPlatforms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformDto> getPlatformById(@PathVariable Integer id) {
        return ResponseEntity.ok(platformService.getPlatformById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PlatformDto> createPlatform(@RequestBody PlatformDto platformDto) {
        return ResponseEntity.ok(platformService.createPlatform(platformDto));
    }

    @PutMapping("/update")
    public ResponseEntity<PlatformDto> updatePlatform(@RequestBody PlatformDto platformDto) {
        return ResponseEntity.ok(platformService.updatePlatform(platformDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable Integer id) {
        platformService.deletePlatform(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ids")
    public ResponseEntity<List<PlatformDto>> getPlatformsByIds(@RequestParam("ids") List<Integer> ids) {
        IdsQuery idsQuery = IdsQuery.builder().ids(ids).build();
        return ResponseEntity.ok(platformService.getPlatformsByIds(idsQuery));
    }
}
