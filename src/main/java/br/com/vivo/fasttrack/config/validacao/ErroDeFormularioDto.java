package br.com.vivo.fasttrack.config.validacao;

public class ErroDeFormularioDto {
	
	private String status;
	private String message;
	
	public ErroDeFormularioDto(String string, String message) {
		this.status = string;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	
}
