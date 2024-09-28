package com.vuk.fishingcompetition.controller;

import com.vuk.fishingcompetition.model.Catches;
import com.vuk.fishingcompetition.service.CatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/catches")
public class CatchesController {


    private final CatchesService catchesService;

    @Autowired
    public CatchesController(CatchesService catchesService) {
        this.catchesService = catchesService;
    }


    @PostMapping("/{competitionId}/{teamId}")
    public ResponseEntity<Void> createCatch(@RequestBody Catches catches
                           , @PathVariable("competitionId")Long competitionId, @PathVariable("teamId") Long teamId){
        catchesService.insertNewCatch(catches,teamId,competitionId);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{competitionId}/{teamId}")
    public ResponseEntity<Void> updateCatch(@RequestBody Catches catches,@PathVariable("competitionId")Long competitionId,
                            @PathVariable("teamId")Long teamId){

        catchesService.updateCatch(catches,competitionId,teamId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/{catchId}")
    public ResponseEntity<Void> deleteCatch(@PathVariable("catchId")Long catchId){
        catchesService.deleteCatch(catchId);
        return ResponseEntity.noContent().build();
    }


}
