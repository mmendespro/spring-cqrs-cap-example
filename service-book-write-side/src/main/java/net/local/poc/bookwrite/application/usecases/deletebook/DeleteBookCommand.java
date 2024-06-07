package net.local.poc.bookwrite.application.usecases.deletebook;

import jakarta.validation.constraints.NotBlank;
import net.local.poc.bookwrite.application.cqrs.commands.Command;
import net.local.poc.bookwrite.application.cqrs.validation.SelfValidating;

public class DeleteBookCommand extends SelfValidating<DeleteBookCommand> implements Command {

    @NotBlank(message = "[bookId]: field is required.")
    private final String bookId;

    public DeleteBookCommand(String bookId) {
        this.bookId = bookId;
        selfValidate(this);
    }

    public String getBookId() {
        return bookId;
    }
}
