package com.fatihmayuk.kartaca.backend.controller;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.service.impl.LoggerServiceImpl;
import com.fatihmayuk.kartaca.backend.service.impl.TravelServiceImpl;
import com.fatihmayuk.kartaca.backend.util.ApiPaths;
import com.fatihmayuk.kartaca.backend.util.TPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(ApiPaths.TravelCtrl.CTRL)
public class TravelController {

    @Value("${fatihmayuk.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, TravelDetailDto> kafkaTemplate;

    private final LoggerServiceImpl loggerService;
    private final TravelServiceImpl travelServiceImpl;

    public TravelController(KafkaTemplate<String, TravelDetailDto> kafkaTemplate, LoggerServiceImpl loggerService, TravelServiceImpl travelServiceImpl) {
        this.kafkaTemplate = kafkaTemplate;
        this.loggerService = loggerService;
        this.travelServiceImpl = travelServiceImpl;
    }


    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = TravelDto.class)
    public ResponseEntity<TPage<TravelDto>> getAllByPagination(Pageable pageable) {
        TPage<TravelDto> data = travelServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = TravelDto.class)
    public ResponseEntity<TravelDetailDto> getById(@PathVariable(value = "id", required = true) Long id) {
        TravelDto travelDto = travelServiceImpl.getById(id);
        String username =  loggerService.getCurrentUser();
        TravelDetailDto travelDetailDto = loggerService.sendToLoggerApi(travelDto,username);

        kafkaTemplate.send(topic, UUID.randomUUID().toString(),travelDetailDto);

        return ResponseEntity.ok(travelDetailDto);
    }


    @PostMapping
    @ApiOperation(value = "Create Operation", response = TravelDto.class)
    public ResponseEntity<TravelDto> createTravel(@Valid @RequestBody TravelDto travelDto) {

        return ResponseEntity.ok(travelServiceImpl.save(travelDto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = TravelDto.class)
    public ResponseEntity<TravelDto> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TravelDto travelDto) {

        return ResponseEntity.ok(travelServiceImpl.update(id, travelDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> deleteProject(@PathVariable(value = "id", required = true) Long id) {

        return ResponseEntity.ok(travelServiceImpl.delete(id));
    }


}
