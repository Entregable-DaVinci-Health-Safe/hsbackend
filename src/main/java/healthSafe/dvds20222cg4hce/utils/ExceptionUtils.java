package healthSafe.dvds20222cg4hce.utils;

import javax.validation.ConstraintViolationException;

import healthSafe.dvds20222cg4hce.controller.response.ExceptionResponse;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public final class ExceptionUtils {
	private ExceptionUtils() {}
	
	public static String getContstraintViolationMessage(ConstraintViolationException e) {
		return e.getConstraintViolations().iterator().next().getMessage();
	}
	
	public static ExceptionResponse getExceptionResponse(BusinessException e) {
		return ExceptionResponse.builder()
								.code(e.getCode())
								.message(e.getMessage())
								.alertType(e.getAlerta().getDescripcion())
								.build();
	}
}
