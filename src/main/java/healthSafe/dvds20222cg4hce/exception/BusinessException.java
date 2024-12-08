package healthSafe.dvds20222cg4hce.exception;

public class BusinessException extends Exception{
	private String code;
	private TipoAlerta alerta;
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String code, String message, TipoAlerta alerta) {
		super(message);
		this.code = code;
		this.alerta = alerta;
	}
	
	public String getCode() { return code;}
	public TipoAlerta getAlerta() {return alerta;}
}
