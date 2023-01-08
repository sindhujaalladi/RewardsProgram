package com.rewardsprogram.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rewardsprogram.pojo.CustomerDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name  = "CustomerDetails")
public class CustomerDetails {

	
	@Column(name = "CustomerId")
	private String customerId;
	
	@Column(name = "CustomerName")
	private String customerName;
	
	@Id
	@Column(name = "TranscationAmount")
	private int transcationAmount;
	
	@Column(name = "TranscationDate")
	private LocalDate transcationDate;


}
