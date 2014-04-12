package org.hycoder.shorturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/url")
public class URLController {

	@Autowired
	private URLDAO urlDAO;

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such short URL")
	public class URLNotFoundException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request")
	public class BadRequestException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	/**
	 * Given short url, return long url.
	 * */
	@RequestMapping(value = "/{shortURL}", method = RequestMethod.GET)
	public @ResponseBody
	URLMap getLongURL(@PathVariable String shortURL) {
		String regex = "[0-9a-zA-Z]{1,6}";
		if (shortURL.matches(regex)) {
			int urlId = Utils.convertToId(shortURL);
			//System.out.println("userid: " + urlId);
			URLMap urlMap = urlDAO.findById(urlId);
			if (urlMap == null)
				throw new URLNotFoundException();
			return urlMap;
		} else {
			throw new BadRequestException();
		}
	}

	/**
	 * Given long url, return short url.
	 * */
	@RequestMapping(value = "/{longURL}", method = RequestMethod.POST)
	public @ResponseBody
	String getShortURL(@PathVariable String longURL) {
		URLMap urlMap = new URLMap(-1, "000000", longURL);
		int rowId = urlDAO.insert(urlMap);
		if (rowId > 0) {
			//String sURL = Utils.shortURL(rowId);
			//String rURL = request.getRequestURI();
			//String requestBase = rURL.substring(0, rURL.length()- longURL.length());
			// update tiny_url column
			
			//return Utils.getHostname() + requestBase + sURL;
			return "";
		} else {
			throw new BadRequestException();
		}
	}

}