package com.rewardsprogram.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetailsDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8275333514011195593L;
	
	private String customerId;
	private String customerName;
	private int transcationAmount;
	private LocalDate transcationDate;

	
}
