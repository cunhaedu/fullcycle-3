package com.fullcycle.admin.catalogo.domain;

import com.fullcycle.admin.catalogo.domain.Validation.handler.ThrowsValidationHandler;
import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectName = "Filmes";
        final var expectDescription = "A categoria mais assistida";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorsCount = 1;
        final String expectName = null;
        final var expectDescription = "A categoria mais assistida";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        final var actualException = Assertions.assertThrows(
            DomainException.class,
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
            expectedErrorsCount,
            actualException.getErrors().size()
        );

        Assertions.assertEquals(
            expectedErrorMessage,
            actualException.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorsCount = 1;
        final var expectName = " ";
        final var expectDescription = "A categoria mais assistida";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        final var actualException = Assertions.assertThrows(
            DomainException.class,
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
            expectedErrorsCount,
            actualException.getErrors().size()
        );

        Assertions.assertEquals(
            expectedErrorMessage,
            actualException.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorsCount = 1;
        final var expectName = "Fi ";
        final var expectDescription = "A categoria mais assistida";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        final var actualException = Assertions.assertThrows(
            DomainException.class,
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
            expectedErrorsCount,
            actualException.getErrors().size()
        );

        Assertions.assertEquals(
            expectedErrorMessage,
            actualException.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAnInvalidNameLengthGreaterThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorsCount = 1;
        final var expectName = "a".repeat(256);
        final var expectDescription = "A categoria mais assistida";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        final var actualException = Assertions.assertThrows(
            DomainException.class,
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );

        Assertions.assertEquals(
            expectedErrorsCount,
            actualException.getErrors().size()
        );

        Assertions.assertEquals(
            expectedErrorMessage,
            actualException.getErrors().get(0).message()
        );
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOk() {
        final var expectName = "Filmes";
        final var expectDescription = "  ";
        final var expectIsActive = true;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOk() {
        final var expectName = "Filmes";
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = false;

        final var actualCategory = Category.newCategory(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thanReturnCategoryInactivate() {
        final var expectName = "Filmes";
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = false;

        final var aCategory = Category.newCategory(
            expectName,
            expectDescription,
            true
        );

        Assertions.assertDoesNotThrow(
            () -> aCategory.validate(new ThrowsValidationHandler())
        );

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertNull(aCategory.getDeletedAt());
        Assertions.assertTrue(aCategory.getIsActive());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActivate_thanReturnCategoryActivate() {
        final var expectName = "Filmes";
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = true;

        final var aCategory = Category.newCategory(
            expectName,
            expectDescription,
            false
        );

        Assertions.assertDoesNotThrow(
            () -> aCategory.validate(new ThrowsValidationHandler())
        );

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertNotNull(aCategory.getDeletedAt());
        Assertions.assertFalse(aCategory.getIsActive());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thanReturnUpdatedCategory() {
        final var expectName = "Filmes";
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = true;

        final var aCategory = Category.newCategory(
            "Film",
            "A categoria",
            false
        );

        Assertions.assertDoesNotThrow(
            () -> aCategory.validate(new ThrowsValidationHandler())
        );

        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thanReturnUpdatedCategory() {
        final var expectName = "Filmes";
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = false;

        final var aCategory = Category.newCategory(
            "Film",
            "A categoria",
            true
        );

        Assertions.assertDoesNotThrow(
            () -> aCategory.validate(new ThrowsValidationHandler())
        );

        final var updatedAt = aCategory.getUpdatedAt();

        Assertions.assertNull(aCategory.getDeletedAt());
        Assertions.assertTrue(aCategory.getIsActive());

        final var actualCategory = aCategory.update(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertDoesNotThrow(
            () -> actualCategory.validate(new ThrowsValidationHandler())
        );
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thanReturnUpdatedCategory() {
        final String expectName = null;
        final var expectDescription = "Categoria mais assistida";
        final var expectIsActive = true;

        final var aCategory = Category.newCategory(
            "Filmes",
            "A categoria",
            expectIsActive
        );

        Assertions.assertDoesNotThrow(
            () -> aCategory.validate(new ThrowsValidationHandler())
        );

        final var updatedAt = aCategory.getUpdatedAt();

        final var actualCategory = aCategory.update(
            expectName,
            expectDescription,
            expectIsActive
        );

        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectIsActive, actualCategory.getIsActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNull(actualCategory.getDeletedAt());
    }
}
