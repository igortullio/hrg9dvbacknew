package com.igortullio.hering.core.port;

import com.igortullio.hering.core.domain.Email;

public interface EmailSendPort {

    void send(Email email);

}
