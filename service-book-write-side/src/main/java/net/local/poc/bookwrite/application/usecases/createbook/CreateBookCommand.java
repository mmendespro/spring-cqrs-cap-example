package net.local.poc.bookwrite.application.usecases.createbook;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import net.local.poc.bookwrite.application.cqrs.commands.Command;
import net.local.poc.bookwrite.application.cqrs.validation.SelfValidating;

public class CreateBookCommand extends SelfValidating<CreateBookCommand> implements Command {
    
    @NotBlank(message = "[title]: field is required.")
    private final String title;
    
    @NotBlank(message = "[author]: field is required.")
    private final String author;

    @Min(value = 1900L, message = "[year]: invalid value")
    private final int year;
    
    public CreateBookCommand(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.selfValidate(this);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "CreateBookCommand {title=" + title + ", author=" + author + ", year=" + year + "}";
    }
}
