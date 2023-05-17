package co.istad.mbanking.api.user.validator;

import co.istad.mbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstraintValidation implements ConstraintValidator<RoleIdConstraint, List<Integer>> {
    private final UserMapper userMapper;

    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {
        for (Integer role : roleIds) {
            if (userMapper.existByRoleId(role)) {
                return false;
            }
        }
        return true;
    }
}
