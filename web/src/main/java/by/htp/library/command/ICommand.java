package by.htp.library.command;

import by.htp.library.controller.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ICommand {
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
