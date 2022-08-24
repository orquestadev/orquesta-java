package dev.orquesta.exception;

/***
 * Custom exception if API key is invalid
 * 
 * @author murtaza
 *
 */
public class OrquestaIncorrectAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrquestaIncorrectAPIException(String str) {
		super(str);
	}

}
