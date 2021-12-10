package com.igortullio.hering.core.service;

import com.igortullio.hering.core.domain.Email;
import com.igortullio.hering.core.domain.Integration;
import com.igortullio.hering.core.domain.Precondition;
import com.igortullio.hering.core.domain.User;
import com.igortullio.hering.core.domain.enumeration.IntegrationType;
import com.igortullio.hering.core.port.ClientSendPort;
import com.igortullio.hering.core.port.EmailSendPort;
import com.igortullio.hering.core.port.RepositoryFindAllPort;
import com.igortullio.hering.core.port.RepositoryPort;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.BiPredicate;

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
                .filter(integration -> verifyPrecondition(user, integration))
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

    private boolean verifyPrecondition(User user, Integration integration) {
        Precondition precondition = integration.getPrecondition();
        if (Objects.isNull(precondition)) {
            return true;
        }

        try {
            Field field = user.getClass().getDeclaredField(precondition.getUserField().name().toLowerCase());
            field.setAccessible(true);

            Class<?> type = field.getType();
            boolean classEqualsString = String.class.equals(type);
            boolean classEqualsInteger = Integer.class.equals(type);

            if (!classEqualsString && !classEqualsInteger) {
                throw new IllegalArgumentException();
            }

            if (classEqualsString) {
                String value = (String) field.get(user);

                BiPredicate<String, String> biPredicate = precondition.getBiPredicate();
                return biPredicate.test(value, precondition.getConditionString());
            } else {
                Integer value = (Integer) field.get(user);

                BiPredicate<Integer, Integer> biPredicate = precondition.getBiPredicate();
                return biPredicate.test(value, precondition.getConditionInteger());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException();
        }
    }

    private void integrationEmail(User user, Integration integration) {
        String text = user.getName() + ", " + user.getAge() + ", " + user.getPhone() + ", " + user.getEmail();
        String destination = Objects.nonNull(integration.getDestination()) ? integration.getDestination() : user.getEmail();
        Email email = new Email(user.getEmail(), destination, integration.getSubject(), text);

        emailSendPort.send(email);
    }

    private void integrationPost(User user, Integration integration) {
        clientSendPort.send(user, integration);
    }

}
