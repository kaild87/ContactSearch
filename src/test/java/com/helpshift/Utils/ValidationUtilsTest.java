package com.helpshift.Utils;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * @author Kailash Dalmia
 */
public final class ValidationUtilsTest {

	@Test
	public void testValidateString(){
		Assert.assertThat(ValidationUtils.validateString("Kailash Dalmia"), is(true));
		Assert.assertThat(ValidationUtils.validateString("Kailash Suresh Dalmia"), is(true));
		Assert.assertThat(ValidationUtils.validateString("Kailash"), is(true));
		Assert.assertThat(ValidationUtils.validateString("123456789       1011121314151617181920     21 46 47 48"), is(false));
	}

}
