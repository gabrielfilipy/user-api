package com.br.api.exceptionhandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@Builder
public class Problema {

	@ApiModelProperty(example = "400")
	private Integer status;
	
	@ApiModelProperty(example = "http://localhost:8080/rest/dados-invalidos")
	private String type; 
	
	@ApiModelProperty(example = "Dados inválidos")
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
	private String userMessage;
	
	@ApiModelProperty(example = "2019-12-01T18:09:02.70844Z")
	private OffsetDateTime timeStamp;
	
	@ApiModelProperty("Objetos ou campos que geraram o erro (Opcional)")
	private List<Object> fields;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Setter 
	@Builder 
	public static class Object {
		@ApiModelProperty(example = "É necessário informar um responsável pela assinatura.")
		private String userMessage;
		
		@ApiModelProperty(example = "Responsável pela assinatura não informado.")
		private String name;
	}
	
}
