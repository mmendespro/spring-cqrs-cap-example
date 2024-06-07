package net.local.poc.bookwrite.application.cqrs.commands;

public interface CommandBus {
    void execute(Command command);
}
