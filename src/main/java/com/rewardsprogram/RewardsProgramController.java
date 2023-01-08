package com.rewardsprogram;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewardsprogram.pojo.CustomerDetailsDTO;
import com.rewardsprogram.pojo.RewardPoints;
import com.rewardsprogram.service.RewardsProgramService;

@RestController
@RequestMapping("/v1/rewards")
public class RewardsProgramController {

	@Autowired
	private RewardsProgramService rewardsProgramService;

	@GetMapping("/{transcationAmount}")
	public int calculateRewardPointsPerTranscation(@PathVariable int transcationAmount) {
		int rewardpoints = this.rewardsProgramService.calculateRewardPointsPerTranscation(transcationAmount);
		return rewardpoints;
	}

	@PostMapping("/createCustData")
	public String createCustomerData(@RequestBody CustomerDetailsDTO customerDetailsDTO) {
		String response = rewardsProgramService.createCustomerData(customerDetailsDTO);
		return response;
	}

	@GetMapping("/reward/{customerId}")
	public RewardPoints calculateRewardPointsPerMonth(@PathVariable String customerId) throws Exception {
		RewardPoints res = this.rewardsProgramService.calculateRewardPointsPerMonth(customerId);
		System.out.println("customer details" + res);
		return res;

	}

	@GetMapping("/totalrewardpoints/{customerId}")
	public ResponseEntity<RewardPoints> calculateTotalRewardPointsPerCustomer(@PathVariable String customerId)
			throws Exception {
		RewardPoints res = this.rewardsProgramService.calculateTotalRewardPointsPerCustomer(customerId);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/permonth/{customerId}")
	public List<RewardPoints> calculateRewardPointsPerEachMonth(@PathVariable String customerId) throws Exception {
		List<RewardPoints> res = this.rewardsProgramService.calculateRewardPointsPerEachMonth(customerId);
		return res;
	}
}
