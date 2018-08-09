package com.registration;

import java.util.HashMap;
import java.util.Map;
/**
 * Mail model
 *
 */
public class Mail {
	/**
	 * From field of the email
	 */
    private String from;
    /**
     * To field of the email
     */
    private String to;
    /**
     * Subject of the email
     */
    private String subject;
    /**
     * Body of the email
     */
    private String content;
    /**
     * A hashmap used to fill the html template with specific values
     */
    private Map model = new HashMap();

    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    
    public void setModel(Map model) {
    	this.model = model;
    }
    
    public Map getModel() {
    	return model;
    }
}
