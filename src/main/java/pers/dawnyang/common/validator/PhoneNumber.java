package pers.dawnyang.common.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
//注解@Constraint表示校验此注解的校验器类
@Constraint(validatedBy = PhoneNumber.Validator.class)
public @interface PhoneNumber {

    String message() default "手机号格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public void initialize(PhoneNumber arg0) {

	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
	    return ValidatorTools.isPhoneNumber(arg0);
	}
    }
}