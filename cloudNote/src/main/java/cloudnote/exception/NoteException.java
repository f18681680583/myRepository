package cloudnote.exception;

import java.io.Serializable;

public class NoteException extends RuntimeException implements Serializable{

	public NoteException(String message, Throwable cause) {
		super(message, cause);
	}

	
	
}
