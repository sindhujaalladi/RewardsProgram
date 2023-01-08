package com.rewardsprogram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewardsprogram.entity.CustomerDetails;
import com.rewardsprogram.pojo.CustomerDetailsDTO;

@Repository
public interface RewardsProgramRepository extends JpaRepository<CustomerDetails,Integer> {
	
	List<CustomerDetails> findByCustomerId(String customerId);


}
