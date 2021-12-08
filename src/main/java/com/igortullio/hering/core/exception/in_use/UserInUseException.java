package com.igortullio.hering.core.exception.in_use;

import com.igortullio.hering.core.domain.User;

public class UserInUseException extends AbstractInUseException {

    private static final long serialVersionUID = 4723245870249669361L;

    public UserInUseException(Long id) {
        super(User.class, id);
    }

}
