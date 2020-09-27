package com.group.connect4.restUtils;


public class RestStatus<T> {

	public String RequestID= ThreadLocalClass.getID();
	
	public int status;
	
	public String message;
	
	public T value;
	
	
	public RestStatus(int status, String message, T value)
	{
		this.status = status;
		this.message = message;
		this.value = value;
	}
	
	public RestStatus(int status, String message)
	{
		this(status, message, null);
	}
    
    public String toJsonString()
    {
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append("{");
    	stringBuilder.append("\"RequestID\"");
    	stringBuilder.append(":");
    	stringBuilder.append(RequestID);
    	stringBuilder.append(",");
    	
    	stringBuilder.append("\"status\"");
    	stringBuilder.append(":");
    	stringBuilder.append(status);
    	stringBuilder.append("}");
    	
    	return stringBuilder.toString();
    }
}