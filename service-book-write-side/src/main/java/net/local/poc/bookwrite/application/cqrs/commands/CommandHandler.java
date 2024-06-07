package net.local.poc.bookwrite.application.cqrs.commands;

public interface CommandHandler<C> {
    void handle(C command);
    Class<?> commandType();
}
