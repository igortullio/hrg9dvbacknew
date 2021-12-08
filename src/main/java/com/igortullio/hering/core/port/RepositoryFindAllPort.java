package com.igortullio.hering.core.port;

import com.igortullio.hering.core.domain.AbstractDomain;

import java.util.List;

public interface RepositoryFindAllPort<T extends AbstractDomain> {

    List<T> findAll();

}
