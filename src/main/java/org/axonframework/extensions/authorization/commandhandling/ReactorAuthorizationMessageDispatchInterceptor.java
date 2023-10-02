package org.axonframework.extensions.authorization.commandhandling;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.extensions.reactor.messaging.ReactorMessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Reactive message dispatch interceptor that adds the {$code username} and {$code authorities}
 * from the authorized principle
 *
 * @author Roald Bankras
 */
public class ReactorAuthorizationMessageDispatchInterceptor implements ReactorMessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger log = LoggerFactory.getLogger(ReactorAuthorizationMessageDispatchInterceptor.class);

    @Override
    public Mono<CommandMessage<?>> intercept(Mono<CommandMessage<?>> monoMsg) {
        return monoMsg.flatMap(msg ->
                ReactiveSecurityContextHolder.getContext()
                        .map(SecurityContext::getAuthentication)
                        .map(authentication -> {
                            log.debug("Adding message metadata for username & authorities");
                            return msg.andMetaData(Map.of(
                                    "username", authentication.getPrincipal(),
                                    "authorities", authentication.getAuthorities()
                            ));
                        })
        );
    }
}
