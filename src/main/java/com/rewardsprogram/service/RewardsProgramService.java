package com.rewardsprogram.service;

import java.util.List;

import com.rewardsprogram.pojo.CustomerDetailsDTO;
import com.rewardsprogram.pojo.RewardPoints;

public interface RewardsProgramService {

	String createCustomerData(CustomerDetailsDTO customerDetails);

	int calculateRewardPointsPerTranscation(int transcationAmount);

	RewardPoints calculateRewardPointsPerMonth(String customerId) throws Exception;

	RewardPoints calculateTotalRewardPointsPerCustomer(String customerId) throws Exception;

	List<RewardPoints> calculateRewardPointsPerEachMonth(String customerId) throws Exception;

}
