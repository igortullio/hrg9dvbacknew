package com.igortullio.hering.core.exception.not_found;

import com.igortullio.hering.core.exception.HeringException;

public abstract class AbstractNotFoundException extends HeringException {

    private static final long serialVersionUID = 5248404811933402603L;

    protected AbstractNotFoundException(Class<?> clazz, Long id) {
        super(clazz.getSimpleName() + " not found with id: " + id);
    }

    protected AbstractNotFoundException(String message) {
        super(message);
    }

}
