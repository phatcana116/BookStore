package com.example.BookStore.Validatior;

import com.example.BookStore.Validatior.annotation.ValidCategoryId;
import com.example.BookStore.entity.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category != null && category.getId() !=null;
    }
}
