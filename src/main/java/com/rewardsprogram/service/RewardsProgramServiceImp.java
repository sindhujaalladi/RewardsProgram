package com.rewardsprogram.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewardsprogram.entity.CustomerDetails;
import com.rewardsprogram.exception.RewardsProgramException;
import com.rewardsprogram.pojo.CustomerDetailsDTO;
import com.rewardsprogram.pojo.RewardPoints;
import com.rewardsprogram.repository.RewardsProgramRepository;

@Service
public class RewardsProgramServiceImp implements RewardsProgramService {

	@Autowired
	private RewardsProgramRepository rewardsProgramRepository;

	private int calculateRewardPoints(int transcationAmount) {
		if (transcationAmount <= 50) {
			return 0;
		} else {
			if (transcationAmount <= 100) {
				return transcationAmount - 50;
			} else {
				return (transcationAmount - 100) * 2 + 50;
			}
		}

	}

	@Override
	public int calculateRewardPointsPerTranscation(int transcationAmount) {
		int rewardpoints = calculateRewardPoints(transcationAmount);
		return rewardpoints;

	}

	@Override
	public String createCustomerData(CustomerDetailsDTO customerDetailsdto) {
		String response;
		CustomerDetails custdetails = CustomerDetails.builder().customerId(customerDetailsdto.getCustomerId())
				.customerName(customerDetailsdto.getCustomerName())
				.transcationAmount(customerDetailsdto.getTranscationAmount())
				.transcationDate(customerDetailsdto.getTranscationDate()).build();
		rewardsProgramRepository.save(custdetails);
		response = "Datahas been posted in DB successfully";
		return response;

	}

	@Override
	public RewardPoints calculateRewardPointsPerMonth(String customerId) throws Exception {
		if (customerId == null) {
			throw new RewardsProgramException("invalid customerId value");
		} else {
			List<CustomerDetails> custdetailslist = rewardsProgramRepository.findByCustomerId(customerId);
			int month = custdetailslist.get(0).getTranscationDate().getMonthValue();
			List<CustomerDetails> samemonthdates = getDatesOfSimilarMonth(custdetailslist, month);
			samemonthdates.forEach(System.out::println);
			int rewardPoints = 0;
			int totalrewardpoints = 0;
			for (CustomerDetails samedates : samemonthdates) {
				rewardPoints = calculateRewardPoints(samedates.getTranscationAmount());
				totalrewardpoints = totalrewardpoints + rewardPoints;
			}
			RewardPoints rewardpoint = RewardPoints.builder().rewardPoints(totalrewardpoints).customerId(customerId)
					.customerName(samemonthdates.get(0).getCustomerName())
					.message("Reward Points per month per customer for customerId" + customerId).build();
			return rewardpoint;
		}
	}

	private List<CustomerDetails> getDatesOfSimilarMonth(List<CustomerDetails> custdetailslist, int month) {
		List<CustomerDetails> filteredlist = custdetailslist.stream()
				.filter(i -> i.getTranscationDate().getMonthValue() == month).collect(Collectors.toList());
		return filteredlist;
	}

	public List<RewardPoints> calculateRewardPointsPerEachMonth(String customerId) throws Exception {
		if (customerId == null) {
			throw new RewardsProgramException("invalid customerId value");
		} else {
			List<CustomerDetails> custdetailslist = rewardsProgramRepository.findByCustomerId(customerId);
			HashSet<Integer> set = new HashSet<Integer>();
			for (CustomerDetails cdetails : custdetailslist) {
				set.add(cdetails.getTranscationDate().getMonthValue());
			}
			List<Integer> listdate = new ArrayList<Integer>(set);
			List<CustomerDetails> filtered = new ArrayList<CustomerDetails>();

			List<RewardPoints> rewardPointslist = new ArrayList<>();
			RewardPoints rewardPointsobj = new RewardPoints();
			for (Integer num : listdate) {
				filtered = getDatesOfSimilarMonth(custdetailslist, num);
				rewardPointsobj = calculatingRewardPointsPerMonth(filtered, num);
				System.out.println("Reward Points per month per customer" + rewardPointsobj);
				rewardPointslist.add(rewardPointsobj);
			}
			return rewardPointslist;
		}
	}

	private RewardPoints calculatingRewardPointsPerMonth(List<CustomerDetails> filteredlist, Integer num) {
		int rewardPoints = 0;
		int totalrewardpoints = 0;
		for (CustomerDetails filteredlist1 : filteredlist) {
			rewardPoints = calculateRewardPoints(filteredlist1.getTranscationAmount());
			totalrewardpoints = totalrewardpoints + rewardPoints;
		}
		RewardPoints rewardpoint = RewardPoints.builder().rewardPoints(totalrewardpoints)
				.customerId(filteredlist.get(0).getCustomerId()).customerName(filteredlist.get(0).getCustomerName())
				.message("Reward Points earned by customer : " + filteredlist.get(0).getCustomerId()
						+ " for the month : " + num)
				.build();
		return rewardpoint;
	}

	private List<CustomerDetails> getDatesOfSimilarMonth(List<CustomerDetails> custlist, Integer num) {
		List<CustomerDetails> filtered = custlist.stream().filter(i -> i.getTranscationDate().getMonthValue() == num)
				.collect(Collectors.toList());
		return filtered;

	}

	@Override
	public RewardPoints calculateTotalRewardPointsPerCustomer(String customerId) throws Exception {
		if (customerId == null) {
			throw new RewardsProgramException("invalid customerId value");
		} else {
			List<CustomerDetails> custdetailslist = rewardsProgramRepository.findByCustomerId(customerId);
			int earnedPoints = 0;
			int totalEarnedRewardPoints = 0;
			for (CustomerDetails customerdetailslist : custdetailslist) {
				earnedPoints = calculateRewardPoints(customerdetailslist.getTranscationAmount());
				totalEarnedRewardPoints = totalEarnedRewardPoints + earnedPoints;
			}
			RewardPoints rewardpoint = RewardPoints.builder().rewardPoints(totalEarnedRewardPoints)
					.customerId(customerId).customerName(custdetailslist.get(0).getCustomerName())
					.message("Total reward points earned by customer : " + customerId).build();
			return rewardpoint;
		}
	}

}
