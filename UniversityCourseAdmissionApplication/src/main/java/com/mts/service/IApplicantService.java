
package com.mts.service;

import java.util.List;
import com.mts.DTO.ApplicantDto;
import com.mts.entity.AdmissionStatus;
import com.mts.entity.Applicant;
import com.mts.exception.ApplicantNotFoundException;

public interface IApplicantService {
 
	public Applicant addApplicant(Applicant applicant);

	public Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException;

	public Applicant deleteApplicant(Applicant applicant) throws ApplicantNotFoundException;

	public ApplicantDto viewApplicant(int applicantId) throws ApplicantNotFoundException;

	public List<ApplicantDto> viewAllApplicantsByStatus(AdmissionStatus status);
}

