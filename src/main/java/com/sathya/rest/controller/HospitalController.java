package com.sathya.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.rest.model.Hospital;
import com.sathya.rest.service.HospitalService;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {
@Autowired
HospitalService hospitalService;
@PostMapping("/savehosp")
public ResponseEntity<Hospital> saveHospital(@RequestBody Hospital hospital){
	Hospital savehosp = hospitalService.saveHospital(hospital);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("hospital status", "hospital saved successfully...")
			.body(savehosp);
}
@PostMapping("/saveall")
public ResponseEntity<List<Hospital>> saveAllHospital(@RequestBody List<Hospital> hospitals){
	List<Hospital> hosps=hospitalService.saveallHospitals(hospitals);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("success", "hospitals saved successfully...")
			.body(hosps);
}
@GetMapping("/getall")
public ResponseEntity<List<Hospital>> getAllHospitals(){
List<Hospital> hosps = hospitalService. getallhospitals();
	return ResponseEntity.status(HttpStatus.OK)
			.header("status","data reading is successfully...")
			.body( hosps);
}
@GetMapping("/getByid/{id}")
public ResponseEntity<?> getById(@PathVariable Long id){
Optional<Hospital>optionalHosp= hospitalService.getById(id);
if(optionalHosp.isPresent()) {
	return ResponseEntity.status(HttpStatus.OK)
			.body(optionalHosp.get());
}
else
{
	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Emp is not found ..."+id);
}
}
@GetMapping("/getBySpecialization/{specialization}")
public ResponseEntity<?>getBySpecialization(@PathVariable String specialization){
Optional<Hospital>optionalHosp= hospitalService.getBySpecialization(specialization);
if(optionalHosp.isPresent()) {
	return ResponseEntity.status(HttpStatus.OK)
			.body(optionalHosp.get());
}
else
{
	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Hosp is not found ..."+specialization);
}
}
@PatchMapping("/partialupdate/{id}")
public ResponseEntity<?>updatePartialById (@PathVariable Long id, @RequestBody Map<String,Object>updates)
{Optional<Hospital> updatedhosp=hospitalService.updatePartialById(id, updates);
if(updatedhosp.isPresent())
{
	return  ResponseEntity.status(HttpStatus.OK)
                          .body(updatedhosp.get());
   }
     else
   {
     return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                      .body("data is not found.."+id);
   }
}
}
