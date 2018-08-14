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
    /**
     * A hashmap used to fill the html template with specific values
     */
    private Map<String, String> model = new HashMap<String, String>();

    /**
     * Getter
     * @return string value of from
     */
    public String getFrom() {
        return from;
    }
    /**
     * Setter
     * @param from Who sends the email
     */
    public void setFrom(String from) {
        this.from = from;
    }
    /**
     * Getter
     * @return The address where the email is sent
     */
    public String getTo() {
        return to;
    }
    /**
     * Setter
     * @param to The address where the email is sent
     */
    public void setTo(String to) {
        this.to = to;
    }
    /**
     * Getter
     * @return the subject of the email
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Setter
     * @param subject subject of the email
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
    
    /**
     * Overridden toString method
     * Returns the object as String
     */
    @Override
    public String toString() {
        return "Mail{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
    /**
     * Setter
     * @param model hashmap fields and values
     */
    public void setModel(Map<String, String> model) {
    	this.model = model;
    }
    /**
     * Getter
     * @return hashmap fields and values
     */
    public Map<String, String> getModel() {
    	return model;
    }
}
