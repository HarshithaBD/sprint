package com.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mts.entities.AdmissionStatus;
import com.mts.entities.Applicant;
import com.mts.exception.ApplicantNotFoundException;
import com.mts.repository.IApplicantRepository;

@Service
public class ApplicantServiceImpl  implements IApplicantService{
	
	@Autowired
	IApplicantRepository repo;
	
	@Override
	public Applicant addApplicant(Applicant applicant)
	{
		return repo.save(applicant);
		
	}
	@Override
	public Applicant updateApplicant(Applicant applicant)throws ApplicantNotFoundException
	{
	Applicant applicant1=repo.findById(applicant.getApplicantId()).orElseThrow(()->new ApplicantNotFoundException("no applicant found with this id!"));
	applicant1.setApplicantName(applicant.getApplicantName());
	applicant1.setMobileNumber(applicant.getMobileNumber());
	applicant1.setApplicantDegree(applicant.getApplicantDegree());
	applicant1.setApplicantGraduationPercent(applicant.getApplicantGraduationPercent());
	applicant1.setAdmission(applicant.getAdmission());
	applicant1.setStatus(applicant.getStatus());
	return applicant1;
	}
@Override
public Applicant deleteApplicant(Applicant applicant)throws ApplicantNotFoundException
{
	Applicant applicant1=repo.findById(applicant.getApplicantId()).orElseThrow(()-> new ApplicantNotFoundException("no applicant found with this id!"));
	repo.delete(applicant1);
	return applicant1;
}
@Override
public List<Applicant> viewAllApplicantByStatus(AdmissionStatus status){
return repo.findByStatus(status);
}
@Override
public Applicant viewApplicant(int applicantId) throws ApplicantNotFoundException {
	
	return repo.findById(applicantId).orElseThrow(()->new ApplicantNotFoundException("no applicant found with this id!"));
}
}
