package com.mts.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mts.DTO.ApplicantDto;
import com.mts.entity.AdmissionStatus;
import com.mts.entity.Applicant;
import com.mts.exception.ApplicantNotFoundException;
import com.mts.repository.IApplicantRepository;

@Service
public class ApplicantServiceImpl implements IApplicantService{

	@Autowired
	IApplicantRepository repo;
	
	@Override
	public Applicant addApplicant(Applicant applicant) {
		return repo.save(applicant);
	}

	@Override
	public Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException {
		Applicant applicant1=repo.findById(applicant.getApplicantId()).orElseThrow(()-> new ApplicantNotFoundException("No applicant found with this id !"));
		
		applicant1.setApplicantName(applicant.getApplicantName());
		applicant1.setMobileNumber(applicant.getMobileNumber());
		applicant1.setApplicantDegree(applicant.getApplicantDegree());
		applicant1.setApplicantGraduationPercent(applicant.getApplicantGraduationPercent());
		applicant1.setAdmission(applicant.getAdmission());
		applicant1.setStatus(applicant.getStatus());
		applicant1.setPassword(applicant.getPassword());
		return repo.save(applicant1);
	}

	@Override
	public Applicant deleteApplicant(Applicant applicant) throws ApplicantNotFoundException {
		Applicant applicant1=repo.findById(applicant.getApplicantId()).orElseThrow(()-> new ApplicantNotFoundException("No applicant found with this id !"));
		repo.delete(applicant1);
		return applicant1;
	}

	@Override
	public ApplicantDto viewApplicant(int applicantId) throws ApplicantNotFoundException {
		Applicant applicant1=repo.findById(applicantId).orElseThrow(()-> new ApplicantNotFoundException("No applicant found with this id !"));
		return mapper.map(applicant1, ApplicantDto.class);
	}

	@Override
	public List<ApplicantDto> viewAllApplicantsByStatus(AdmissionStatus status)  {
		List<Applicant> lst=repo.findByStatus(status);
		List<ApplicantDto> toDTO=Arrays.asList(mapper.map(lst, ApplicantDto.class));
		return toDTO;
	}

}