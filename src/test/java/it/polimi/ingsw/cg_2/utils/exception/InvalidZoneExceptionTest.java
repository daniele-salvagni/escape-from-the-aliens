package it.polimi.ingsw.cg_2.utils.exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidZoneExceptionTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldBeThrown() {

		thrown.expect(InvalidZoneException.class);

		throw new InvalidZoneException();

	}

	@Test
	public void shouldBeThrownWithMessage() {

		String exceptionMessage = "Test Message 1";

		thrown.expect(InvalidZoneException.class);
		thrown.expectMessage(exceptionMessage);

		throw new InvalidZoneException(exceptionMessage);

	}

}
