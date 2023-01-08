package com.rewardsprogram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rewardsprogram.entity.CustomerDetails;
import com.rewardsprogram.exception.RewardsProgramException;
import com.rewardsprogram.pojo.CustomerDetailsDTO;
import com.rewardsprogram.pojo.RewardPoints;
import com.rewardsprogram.repository.RewardsProgramRepository;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RewardsProgramServiceImpTest {

	@MockBean
	private RewardsProgramRepository rewardsProgramRepository;

	@Mock
	private RewardsProgramServiceImp mockserviceobj;

	// RewardsProgramServiceImp mockserviceobj =
	// Mockito.mock(RewardsProgramServiceImp.class);

	@Test
	void calculateRewardPointsPerTranscationTest() {
		int transcationAmount = 130;
		int rewardpoints = 110;
		Mockito.when(mockserviceobj.calculateRewardPointsPerTranscation(transcationAmount)).thenReturn(rewardpoints);
		Assertions.assertEquals(110, rewardpoints);

	}

	@Test
	void createCustomerDataTest() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId("2345");
		customerDetails.setCustomerName("sanjose");
		customerDetails.setTranscationAmount(200);
		LocalDate date = LocalDate.of(2021, 04, 16);
		customerDetails.setTranscationDate(date);

		CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO();
		customerDetailsDTO.setCustomerId("2345");
		customerDetailsDTO.setCustomerName("sanjose");
		customerDetailsDTO.setTranscationAmount(200);
		customerDetailsDTO.setTranscationDate(date);
		String response = "Datahas been posted in DB successfully";
		when(rewardsProgramRepository.save(customerDetails)).thenReturn(customerDetails);
		OngoingStubbing<String> res = Mockito.when(mockserviceobj.createCustomerData(customerDetailsDTO))
				.thenReturn(response);
		assertThat(res).isNotNull();
	}

	@Test
	void calculateRewardPointsPerMonthTest() throws Exception {

		List<CustomerDetails> custlistobj = new ArrayList<>();
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId("2345");
		customerDetails.setCustomerName("sanjose");
		customerDetails.setTranscationAmount(200);
		LocalDate date = LocalDate.of(2021, 04, 16);
		customerDetails.setTranscationDate(date);
		custlistobj.add(customerDetails);
		String customerId = "2345";
		int totalrewardpoints = 0;

		RewardPoints rewardPoints = new RewardPoints();
		RewardPoints rewardobj = new RewardPoints();
		rewardPoints.setCustomerId(customerId);
		rewardPoints.setCustomerName("sanjose");
		rewardPoints.setMessage("Reward Points per month per customer for customerId" + customerId);
		rewardPoints.setRewardPoints(250);
		when(rewardsProgramRepository.findByCustomerId(customerId)).thenReturn(custlistobj);
		Mockito.when(mockserviceobj.calculateRewardPointsPerMonth(customerId)).thenReturn(rewardPoints);
		// Assertions.assertEquals(rewardPoints.getRewardPoints(),);
	}

	@Test
	void calculateTotalRewardPointsPerCustomerTest() throws Exception {
		String customerId = "2345";

		List<CustomerDetails> custlistobj = new ArrayList<>();
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId("2345");
		customerDetails.setCustomerName("sanjose");
		customerDetails.setTranscationAmount(200);
		LocalDate date = LocalDate.of(2021, 04, 16);
		customerDetails.setTranscationDate(date);
		custlistobj.add(customerDetails);
		int totalEarnedRewardPoints = 0;

		RewardPoints rewardPoints = new RewardPoints();
		RewardPoints rewardobj = new RewardPoints();
		rewardPoints.setCustomerId(customerId);
		rewardPoints.setCustomerName("sanjose");
		rewardPoints.setMessage("Total reward points earned by customer : " + customerId);
		rewardPoints.setRewardPoints(910);

		when(rewardsProgramRepository.findByCustomerId(customerId)).thenReturn(custlistobj);
		Mockito.when(mockserviceobj.calculateTotalRewardPointsPerCustomer(customerId)).thenReturn(rewardPoints);

	}

	@Test
	void calculateRewardPointsPerEachMonthTest() throws Exception {
		String customerId = "2345";
		List<CustomerDetails> custlistobj = new ArrayList<>();
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setCustomerId("2345");
		customerDetails.setCustomerName("sanjose");
		customerDetails.setTranscationAmount(200);
		LocalDate date = LocalDate.of(2021, 04, 16);
		customerDetails.setTranscationDate(date);

		CustomerDetails customerDetails2 = new CustomerDetails();
		customerDetails2.setCustomerId("2345");
		customerDetails2.setCustomerName("sanjose");
		customerDetails2.setTranscationAmount(130);
		LocalDate date1 = LocalDate.of(2021, 04, 23);
		customerDetails2.setTranscationDate(date1);

		CustomerDetails customerDetails3 = new CustomerDetails();
		customerDetails3.setCustomerId("2345");
		customerDetails3.setCustomerName("sanjose");
		customerDetails3.setTranscationAmount(150);
		LocalDate date2 = LocalDate.of(2021, 04, 20);
		customerDetails3.setTranscationDate(date2);

		CustomerDetails customerDetails4 = new CustomerDetails();
		customerDetails3.setCustomerId("2345");
		customerDetails3.setCustomerName("sanjose");
		customerDetails3.setTranscationAmount(160);
		LocalDate date4 = LocalDate.of(2021, 05, 13);
		customerDetails3.setTranscationDate(date4);

		CustomerDetails customerDetails5 = new CustomerDetails();
		customerDetails3.setCustomerId("2345");
		customerDetails3.setCustomerName("sanjose");
		customerDetails3.setTranscationAmount(190);
		LocalDate date5 = LocalDate.of(2021, 05, 10);
		customerDetails3.setTranscationDate(date5);

		custlistobj.add(customerDetails);
		custlistobj.add(customerDetails2);
		custlistobj.add(customerDetails3);
		custlistobj.add(customerDetails4);
		custlistobj.add(customerDetails5);

		int totalEarnedRewardPoints = 0;

		List<RewardPoints> rewardlist = new ArrayList<>();
		RewardPoints rewardPoints = new RewardPoints();
		RewardPoints rewardobj = new RewardPoints();
		rewardPoints.setCustomerId("2345");
		rewardPoints.setCustomerName("sanjose");
		rewardPoints.setMessage("Reward Points earned by customer : " + 2345 + " for the month : " + 4);
		rewardPoints.setRewardPoints(510);

		RewardPoints rewardPoints1 = new RewardPoints();
		rewardPoints1.setCustomerId("2345");
		rewardPoints1.setCustomerName("sanjose");
		rewardPoints.setMessage("Reward Points earned by customer : " + 2345 + " for the month : " + 5);
		rewardPoints1.setRewardPoints(400);
		rewardlist.add(rewardPoints1);
		rewardlist.add(rewardPoints);

		when(rewardsProgramRepository.findByCustomerId(customerId)).thenReturn(custlistobj);
		Mockito.when(mockserviceobj.calculateRewardPointsPerEachMonth(customerId)).thenReturn(rewardlist);

	}

	@Test
	void calculateTotalRewardPointsPerCustomerInvalidInputTest() throws Exception {
		String customerId = null;
		Mockito.when(mockserviceobj.calculateRewardPointsPerEachMonth(customerId))
				.thenThrow(RewardsProgramException.class);
	}

	@Test
	void calculateRewardPointsPerEachMonthInvalidInputTest() throws Exception {
		String customerId = null;
		Mockito.when(mockserviceobj.calculateRewardPointsPerEachMonth(customerId))
				.thenThrow(RewardsProgramException.class);
	}

	@Test
	void calculateRewardPointsPerMonthInvalidInputTest() throws Exception {
		String customerId = null;
		Mockito.when(mockserviceobj.calculateRewardPointsPerMonth(customerId)).thenThrow(RewardsProgramException.class);
	}
}
