package com.example.task_system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND_EXCEPTION("USER NOT FOUND","user not found with name %s" , NOT_FOUND),
    UNCONFIRM_PASSWORD("UNCONFIRM PASSWORD" , "unconfirm password", BAD_REQUEST),
    ERROR_PASSWORD("YOUR PASSWORD IS NOT RIGHT","your password is not right",BAD_REQUEST),
    TASK_NOT_FOUND_EXCEPTION("TASK_NOT_FOUND_EXCEPTION","task not found exception ",NOT_FOUND),
    NOTEBOOK_NOT_FOUND_EXCEPTION("NOTEBOOK_NOT_FOUND_EXCEPTION","noteBook not found exception ",NOT_FOUND),
    NOTE_NOT_FOUND_EXCEPTION("NOTE_NOT_FOUND_EXCEPTION","note not found exception ",NOT_FOUND),
    REFRESH_TOKEN_NOT_VALID("REFRESH_TOKEN_NOT_VALID","your refresh token is not valid",NOT_ACCEPTABLE),
    FIELD_NAME_SHOULD_HAS_VALUE("FIELD_NAME_SHOULD_HAS_VALUE","please insert the noteBook's name", BAD_REQUEST),
    YOUR_EMAIL_IS_EXISTS("YOUR_EMAIL_IS_EXISTS", "your email is exists",EXPECTATION_FAILED ),
    TOKEN_NOT_VALID("TOKEN_NOT_VALID","token not valid" ,BAD_REQUEST),
    PROBLEM_WITH_HEADER("PROBLEM_WITH_HEADER","problem with header" ,BAD_REQUEST ),
    TOKEN_IS_EXPIRED("TOKEN_IS_EXPIRED","token is expired" ,BAD_REQUEST ),
    UNAUTHORIZED("UNAUTHORIZED","unauthorized of user" ,NOT_ACCEPTABLE),
    ERROR_YOUR_PATH_NOT_CORRECT("ERROR_YOUR_PATH_NOT_CORRECT","error your path not correct" ,BAD_REQUEST);

    private final String code;

    private final String defaultMessage;

    private final HttpStatus status;

    ErrorCode(final String code,
              final String defaultMessage,
              final HttpStatus status) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }
}
