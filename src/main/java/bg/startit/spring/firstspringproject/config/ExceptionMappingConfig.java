package bg.startit.spring.firstspringproject.config;

import javax.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMappingConfig {

  private static final Logger log = LoggerFactory.getLogger(ExceptionMappingConfig.class);

  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class})
  public void handleValidationException(Exception ex) {
    log.error("An validation error occurred processing request" + ex);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
  @ExceptionHandler(Exception.class)
  public void handleGeneralError(Exception ex) {
    log.error("An error occurred processing request" + ex);
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(AccessDeniedException ex) {
    log.error("An error occurred processing request" + ex);
  }
}
