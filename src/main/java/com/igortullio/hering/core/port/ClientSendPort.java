package com.igortullio.hering.core.port;

import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.domain.User;

public interface ClientSendPort {

    void send(User user, Integration integration);

}
