package co.istad.mbanking.api.user.validator;

import co.istad.mbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique,String> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userMapper.existByEmail(email);
    }
}
