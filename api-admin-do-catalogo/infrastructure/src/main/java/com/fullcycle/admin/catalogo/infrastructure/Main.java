package com.fullcycle.admin.catalogo.infrastructure;

import com.fullcycle.admin.catalogo.application.CategoryUseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(new CategoryUseCase().execute());
    }
}
