package com.desk.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desk.app.exception.ResourceNotFoundException;
import com.desk.app.model.Desk;
import com.desk.app.repository.DeskRepository;
import com.desk.app.service.SequenceGeneratorService;


@RestController
@RequestMapping("/api/v1")
public class DeskController {

	@Autowired
	private DeskRepository deskRepository;
	
	@Autowired
    private SequenceGeneratorService sequenceGeneratorService;
	
	
	@Value("${spring.data.mongodb.host}")
	private String mongoURL;
	
	@GetMapping("/desks")
	public List< Desk > getAllDesks(){
		return deskRepository.findAll();
	}
	
	@GetMapping("/desks/{id}")
    public ResponseEntity < Desk > getDeskById(@PathVariable(value = "id") Long deskId)
    throws ResourceNotFoundException {
        Desk desk = deskRepository.findById(deskId)
            .orElseThrow(() -> new ResourceNotFoundException("Desk not found for this id :: " + deskId));
        return ResponseEntity.ok().body(desk);
    }
	
	@PostMapping("/desks")
    public Desk createDesk(@Validated @RequestBody Desk desk) {
        desk.setId(sequenceGeneratorService.generateSequence(Desk.SEQUENCE_NAME));
        return deskRepository.save(desk);
    }
	
	@DeleteMapping("/desks/{id}")
    public Map < String, Boolean > deleteDesk(@PathVariable(value = "id") Long deskId)
    throws ResourceNotFoundException {
        Desk desk = deskRepository.findById(deskId)
            .orElseThrow(() -> new ResourceNotFoundException("Desk not found for this id :: " + deskId));

        deskRepository.delete(desk);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	@PutMapping("/employee/{id}")
	public ResponseEntity <Desk> updateEmployee(@PathVariable(value = "id")Long deskId,
	        @Valid @RequestBody Desk deskDetails) throws ResourceNotFoundException {
		
		Desk desk = deskRepository.findById(deskId)
	            .orElseThrow(() -> new ResourceNotFoundException("Desk not found for this id :: " + deskId));
		desk.setOffice(deskDetails.getOffice());
		desk.setFloor(deskDetails.getFloor());
		
		
		final Desk updatedDesk = deskRepository.save(desk);
		return ResponseEntity.ok(updatedDesk);
	}
	
}
