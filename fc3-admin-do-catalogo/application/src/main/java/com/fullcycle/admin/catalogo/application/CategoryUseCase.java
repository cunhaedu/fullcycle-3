package com.fullcycle.admin.catalogo.application;

import com.fullcycle.admin.catalogo.domain.category.Category;

public class CategoryUseCase {
    public Category execute() {
        return new Category();
    }
}
