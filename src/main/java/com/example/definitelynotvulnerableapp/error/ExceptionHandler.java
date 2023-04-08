package com.example.definitelynotvulnerableapp.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("ErrorLog");

	private final ThrowableAnalyzer throwableAnalyzer = new ThrowableAnalyzer();
	/**
	 * Handle exceptions thrown by handlers.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error/general");
		Throwable[] throwables = throwableAnalyzer.determineCauseChain(exception);
		Throwable rootCause = throwables[throwables.length-1];
        modelAndView.addObject("errorMessage", rootCause);
        LOGGER.error(rootCause.toString(), exception);
		return modelAndView;
	}
}