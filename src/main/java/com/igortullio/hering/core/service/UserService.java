package com.igortullio.hering.core.service;

import com.igortullio.hering.core.domain.Email;
import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.domain.User;
import com.igortullio.hering.core.domain.enumeration.IntegrationType;
import com.igortullio.hering.core.port.ClientSendPort;
import com.igortullio.hering.core.port.EmailSendPort;
import com.igortullio.hering.core.port.RepositoryFindAllPort;
import com.igortullio.hering.core.port.RepositoryPort;

import java.util.Objects;

public final class UserService implements InterfaceService<User> {

    private final RepositoryPort<User> userRepository;
    private final RepositoryFindAllPort<Integration> integrationFindAllRepository;
    private final EmailSendPort emailSendPort;
    private final ClientSendPort clientSendPort;

    public UserService(RepositoryPort<User> userRepository,
                       RepositoryFindAllPort<Integration> integrationFindAllRepository,
                       EmailSendPort emailSendPort,
                       ClientSendPort clientSendPort) {
        this.userRepository = userRepository;
        this.integrationFindAllRepository = integrationFindAllRepository;
        this.emailSendPort = emailSendPort;
        this.clientSendPort = clientSendPort;
    }

    @Override
    public User find(Long id) {
        return userRepository.find(id);
    }

    @Override
    public User save(User user) {
        user = userRepository.save(user);
        executeIntegrations(user);
        return user;
    }

    @Override
    public User update(Long id, User user) {
        return userRepository.update(id, user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    private void executeIntegrations(User user) {
        integrationFindAllRepository.findAll()
                .stream()
                .filter(integration -> Objects.isNull(integration.getPrecondition()))
                .forEach(integration -> {
                    IntegrationType integrationType = integration.getIntegrationType();

                    switch (integrationType) {
                        case EMAIL:
                            integrationEmail(user, integration);
                            break;
                        case POST:
                            integrationPost(user, integration);
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                });
    }

    private void integrationEmail(User user, Integration integration) {
        String text = user.getName() + ", " + user.getAge() + ", " + user.getPhone() + ", " + user.getEmail();
        Email email = new Email(user.getEmail(), integration.getDestination(), integration.getSubject(), text);

        emailSendPort.send(email);
    }

    private void integrationPost(User user, Integration integration) {
        clientSendPort.send(user, integration);
    }

}
