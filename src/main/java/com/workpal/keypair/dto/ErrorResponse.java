package com.workpal.keypair.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

	@JsonProperty("error_messages")
	private List<Error> errorMessages;

}
