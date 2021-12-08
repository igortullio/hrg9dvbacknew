package com.igortullio.hering.core.service;

import com.igortullio.hering.core.domain.AbstractDomain;

public interface InterfaceService<T extends AbstractDomain> {

    T find(Long id);

    T save(T t);

    T update(Long id, T t);

    void delete(Long id);

}
