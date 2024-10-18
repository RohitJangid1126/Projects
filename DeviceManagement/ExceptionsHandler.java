package ivanovvasil.u5d5w2Project.exceptions;

import ivanovvasil.u5d5w2Project.payloads.exceptionsDTO.ErrorsListResponseDTO;
import ivanovvasil.u5d5w2Project.payloads.exceptionsDTO.ErrorsResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorsListResponseDTO handleBadRequest(BadRequestException e) {
    if (e.getErrorList() != null) {
      List<String> errorsList = e.getErrorList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
      return new ErrorsListResponseDTO(e.getMessage(), new Date(), errorsList);
    } else {
      return new ErrorsListResponseDTO(e.getMessage(), new Date(), new ArrayList<>());
    }
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorsResponseDTO handleNotFound(NotFoundException e) {
    return new ErrorsResponseDTO(e.getMessage(), new Date());
  }

  //if a client places an invalid path param on an endpoint
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorsResponseDTO handleMethodArgumentTypeMismatchExceptio(MethodArgumentTypeMismatchException e) {
    return new ErrorsResponseDTO("Invalid path param entered", new Date());
  }

  //if a client places an invalid path param on an endpoint
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ErrorsResponseDTO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    return new ErrorsResponseDTO(e.getMessage(), new Date());
  }

  //if a client is attempting to execute a prohibited method on a certain endpoint
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorsResponseDTO handleGeneric(Exception e) {
    e.printStackTrace();
    return new ErrorsResponseDTO("we are sorry at the moment we have some internal problems, we are trying to resolve them", new Date());
  }
}

