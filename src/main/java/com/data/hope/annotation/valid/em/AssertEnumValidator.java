package com.data.hope.annotation.valid.em;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否枚举
 * @author guoqinghua
 * @since 2022-03-01
 */
public class AssertEnumValidator implements ConstraintValidator<AssertEnum,Object> {

    private Class enumClass;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        try {
            EnumInterface[] values = (EnumInterface[])enumClass.getDeclaredMethod("values").invoke(null);
            Boolean exist = false;
            for(EnumInterface enumInterface: values) {
                exist = enumInterface.exist(value);
                if(exist) {
                    break;
                }
            }

            return exist;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void initialize(AssertEnum constraintAnnotation) {
        enumClass = constraintAnnotation.value();
    }
}
