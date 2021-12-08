package com.igortullio.hering.core.exception.not_found;

import com.igortullio.hering.core.domain.User;

public class UserNotFoundException extends AbstractNotFoundException {

    private static final long serialVersionUID = 4468281535940810181L;

    public UserNotFoundException(Long id) {
        super(User.class, id);
    }

}
