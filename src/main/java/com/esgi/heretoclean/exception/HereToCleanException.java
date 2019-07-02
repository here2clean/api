package com.esgi.heretoclean.exception;
import java.util.ArrayList;
import java.util.List;

public class HereToCleanException extends Exception {
	
	private int errorCode;
	private List<String> errorMessages = new ArrayList<>();
	private String errorMessage;
	
	public HereToCleanException() {
	}
	
    public HereToCleanException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HereToCleanException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HereToCleanException(Throwable cause) {
        super(cause);
    }

    public HereToCleanException(String message, Throwable cause) {
        super(message, cause);
    }

    public HereToCleanException(int errorCode, String errorMessage, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HereToCleanException(int errorCode, List<String> errorMessages) {
        this.errorCode = errorCode;
        this.errorMessages = errorMessages;
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
