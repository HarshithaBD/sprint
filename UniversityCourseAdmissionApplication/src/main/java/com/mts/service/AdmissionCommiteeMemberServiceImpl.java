package com.mts.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mts.DTO.AdmissionCommiteeMemberDto;
import com.mts.entity.AdmissionCommiteeMember;
import com.mts.entity.AdmissionStatus;
import com.mts.entity.Applicant;
import com.mts.exception.AdmissionMemNotFoundException;
import com.mts.repository.IAdmissionCommiteeRepository;

@Service
public class AdmissionCommiteeMemberServiceImpl implements IAdmissionCommiteeMemberService{

	@Autowired
	IAdmissionCommiteeRepository repo; 
	
	@Override
	public AdmissionCommiteeMember addCommiteeMember(AdmissionCommiteeMember member) {
		return repo.save(member);
	}

	@Override
	public AdmissionCommiteeMember updateCommiteeMember(AdmissionCommiteeMember member) throws AdmissionMemNotFoundException{
		AdmissionCommiteeMember member1=repo.findById(member.getAdminId()).orElseThrow(()->new AdmissionMemNotFoundException("No record present with given id"));
		member1.setAdminName(member.getAdminName());
		member1.setAdminContact(member.getAdminContact());
		member1.setPassword(member.getPassword());
		return repo.save(member1);
	}

	@Override
	public AdmissionCommiteeMemberDto viewCommiteeMember(int adminId) throws AdmissionMemNotFoundException{
		AdmissionCommiteeMember member1=repo.findById(adminId).orElseThrow(()->new AdmissionMemNotFoundException("Invalid memberId !"));
		return mapper.map(member1, AdmissionCommiteeMemberDto.class);
	}

	@Override
	public void removeCommiteeMember(int adminId) throws AdmissionMemNotFoundException{
		repo.deleteById(adminId);	
	}

	@Override
	public List<AdmissionCommiteeMemberDto> viewAllCommiteeMembers() {
		
		List<AdmissionCommiteeMember>lst=repo.findAll();
		List<AdmissionCommiteeMemberDto> toDTO= Arrays.asList(mapper.map(lst, AdmissionCommiteeMemberDto[].class));
		return toDTO;
	}
	//to provideAdmissionresult
	@Override
	public AdmissionStatus provideAdmissionResult(Applicant applicant) {
		return applicant.getStatus();	
	}

}
