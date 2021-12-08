package com.igortullio.hering.core.exception.not_found;

import com.igortullio.hering.core.domain.Integration;

public class IntegrationNotFoundException extends AbstractNotFoundException {

    private static final long serialVersionUID = 8646421395081439639L;

    public IntegrationNotFoundException(Long id) {
        super(Integration.class, id);
    }

}
