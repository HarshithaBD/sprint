package com.mts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mts.DTO.ApplicantDto;
import com.mts.entity.AdmissionStatus;
import com.mts.entity.Applicant;
import com.mts.exception.ApplicantNotFoundException;

import com.mts.service.IApplicantService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	IApplicantService service;
	
	@PostMapping("/addApplicant")
	public Applicant addApplicant(@RequestBody Applicant applicant) {
		return service.addApplicant(applicant);
	}
	
	@PutMapping("/updateApplicant")
	public Applicant updateApplicant(@RequestBody Applicant applicant) throws ApplicantNotFoundException {
		return service.updateApplicant(applicant);
	}
	
	@DeleteMapping("/deleteApplicant")
	public Applicant deleteApplicant(@PathVariable Applicant applicant) throws ApplicantNotFoundException {
		return service.deleteApplicant(applicant);
	}
	
	@GetMapping("/viewApplicant/{applicantId}")
	public ApplicantDto viewApplicant(@PathVariable int applicantId) throws ApplicantNotFoundException {
		return service.viewApplicant(applicantId);
	}
	
	@GetMapping("/viewAllApplicantsByStatus/{status}")
	public List<ApplicantDto> viewAllApplicantsByStatus(@PathVariable AdmissionStatus status){
		return service.viewAllApplicantsByStatus(status);
	}
}