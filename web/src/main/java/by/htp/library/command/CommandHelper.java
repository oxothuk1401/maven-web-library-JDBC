package by.htp.library.command;

import by.htp.library.command.impl.*;
import by.htp.library.controller.exception.CommandException;
import java.util.EnumMap;

public class CommandHelper {
    private static EnumMap<CommandName, ICommand> commands;

    static {
        commands = new EnumMap<CommandName, ICommand>(CommandName.class);
        commands.put(CommandName.LOCALIZATION, new LocalizationCommand());
        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.TO_MAIN_PAGE, new MainPageCommand());
        commands.put(CommandName.SIGN_UP_USER, new SignUpUserCommand());
        commands.put(CommandName.SHOW_ALL_USERS, new ShowAllUsersCommand());
        commands.put(CommandName.SHOW_ALL_BOOKS, new ShowAllBooksCommand());
        commands.put(CommandName.ADD_BOOK, new AddBookCommand());
        commands.put(CommandName.SEARCH_BOOK, new SearchBookCommand());
        commands.put(CommandName.USER_OPERATION, new UserOperationCommand());
        commands.put(CommandName.BOOK_OPERATION, new BookOperationCommand());
    }

    public static ICommand getCommand(String commandName) throws CommandException {
        try {
            CommandName key = CommandName.valueOf(commandName.replace('-', '_').toUpperCase());
            ICommand command = commands.get(key);
            return command;
        } catch (IllegalArgumentException e) {
            throw new CommandException("Wrong command name", e);
        }
    }
}

