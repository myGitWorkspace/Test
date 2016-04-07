package ua.deliveryservice.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String DEFAULT_ERROR_VIEW = "error";

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 
	 * Exception handler for all exceptions
	 * 
	 * @param req Request parameter 
	 * @param e Current exception object
	 * @param model Model object to pass attributes to result view 
	 * @return name of the view page to view the result of given request
	 * 
	 */
	@ExceptionHandler(value = Exception.class)
	public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) throws Exception {
		
		defaultHandler(e, req, model);
				
		return DEFAULT_ERROR_VIEW;
	}
	
	/**
	 * 
	 * Exception handler for NoHandlerFoundException
	 * 
	 * @param e Current exception object
	 * @param req Request parameter 
	 * @param model Model object to pass attributes to result view 
	 * @return name of the view page to view the result of given request
	 * 
	 */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFoundException(Exception e, HttpServletRequest req, Model model){
    	
    	defaultHandler(e, req, model);
    	
    	return DEFAULT_ERROR_VIEW;
    }
    
    private void defaultHandler(Exception e, HttpServletRequest req, Model model) {
    	
		logger.error("Exception caught by [URL] : {}; {}", req.getRequestURL(), e);

		model.addAttribute("exception", e);
		model.addAttribute("url", req.getRequestURL());
    }
}
