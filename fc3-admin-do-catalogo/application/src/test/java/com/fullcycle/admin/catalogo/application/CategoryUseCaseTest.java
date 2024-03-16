package com.fullcycle.admin.catalogo.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryUseCaseTest {

    @Test
    public void testCreateUseCase() {
        Assertions.assertNotNull(new CategoryUseCase());
        Assertions.assertNotNull(new CategoryUseCase().execute());
    }
}
