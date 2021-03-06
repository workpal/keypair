package com.workpal.keypair.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workpal.keypair.enums.ErrorCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {

	@JsonProperty("error_code")
	private ErrorCode errorCode;

	@JsonProperty("error_info")
	private Map<String, Object> errorInfo;

}
