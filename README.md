# Axon Framework - Command Authorization Extension
[![Axon Command Authorization](https://github.com/kretar/axon-command-authorization/actions/workflows/main.yaml/badge.svg?event=push)](https://github.com/kretar/axon-command-authorization/actions/workflows/main.yaml)

Axon Framework is a framework for building evolutionary, event-driven microservice systems,
based on the principles of Domain Driven Design, Command-Query Responsibility Segregation (CQRS) and Event Sourcing.

As such it provides you the necessary building blocks to follow these principles.
Building blocks like Aggregate factories and Repositories, Command, Event and Query Buses and an Event Store.
The framework provides sensible defaults for all of these components out of the box.

This set up helps you create a well structured application without having to bother with the infrastructure.
The main focus can thus become your business functionality.

Axon Framework Command Authorization Extension provides integration with [Spring Security]() and [Axon Reactor Extension](). Currently, it
provides message processors that will propagate part of the principal object and an interceptor that will block unauthorized commands.

For more information on anything Axon, please visit the website, [http://axoniq.io](http://axoniq.io).

## Getting started

To use these processors in your Axon based application:
1. register the `ReactorAuthorizationMessageDispatchInterceptor` as an `dispatchInterceptor` on your `CommandGateway`.
This will make sure the principals `username` and `grantedAuthorities` are copied from the SecurityContext tot the command messages. 
2. register the `CommandAuthorizationInterceptor` as an `handlerInterceptor` on your `CommandBus`. This will verify 
the authorities on the command, set by `@PreAuthorize`, with the users granted authorities.

## Receiving help

Are you having trouble using the extension?
We'd like to help you out the best we can!
