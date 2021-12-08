package com.igortullio.hering.core.exception.in_use;

import com.igortullio.hering.core.exception.HeringException;

public abstract class AbstractInUseException extends HeringException {

    private static final long serialVersionUID = -7825847390815478488L;

    protected AbstractInUseException(Class<?> clazz, Long id) {
        super(clazz.getSimpleName() + " with id (" + id + ") is in use");
    }

}
