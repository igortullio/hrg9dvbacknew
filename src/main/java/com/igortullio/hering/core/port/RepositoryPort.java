package com.igortullio.hering.core.port;

import com.igortullio.hering.core.domain.AbstractDomain;

public interface RepositoryPort<T extends AbstractDomain> {

    T find(Long id);

    T save(T t);

    T update(Long id, T t);

    void delete(Long id);

}
