package org.axonframework.extensions.authorization.commandhandling;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Message dispatch interceptor that adds the {$code username} and {$code authorities}
 * from the authorized principle
 *
 * @author Roald Bankras
 */
public class AuthorizationMessageDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationMessageDispatchInterceptor.class);

    @Nonnull
    @Override
    public CommandMessage<?> handle(@Nonnull CommandMessage<?> message) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("Adding message metadata for username & authorities");
        return message.andMetaData(Map.of(
                "username", authentication.getPrincipal(),
                "authorities", authentication.getAuthorities()
        ));
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (position, message) -> handle(message);
    }
}
