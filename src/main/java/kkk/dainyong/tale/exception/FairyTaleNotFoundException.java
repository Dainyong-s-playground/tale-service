package kkk.dainyong.tale.exception;

public class FairyTaleNotFoundException extends RuntimeException {
	public FairyTaleNotFoundException(Long id) {
		super("FairyTale not found with id: " + id);
	}
}