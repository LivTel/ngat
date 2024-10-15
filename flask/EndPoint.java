// EndPoint.java
package ngat.flask;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.*;

/**
 * Invoke a Flask end-point via a get or post call.
 * Usage something like:
 * <pre>
 * import org.json.*;
 * import ngat.flask.*;
 * 
 * EndPoint exposureEndPoint = new EndPoint();
 * exposureEndPoint.setIPAddress("127.0.0.1");
 * exposureEndPoint.setPortNumber(5100);
 * exposureEndPoint.setFlaskEndPointName("takeExposure");
 * exposureEndPoint.addParameter("exposure_time",10.0);
 * exposureEndPoint.addParameter("filename","test1.fits");
 * exposureEndPoint.setDoPost();
 * exposureEndPoint.run();
 * if(exposureEndPoint.getRunException() != null)
 *    throw new Exception(exposureEndPoint.getRunException());
 * if(exposureEndPoint.getReturnValueString("status").equals("Success"))
 *    System.out.println("Success:"+exposureEndPoint.getReturnValueString("message"));
 * </pre>
 */
public class EndPoint implements Runnable
{
	/**
	 * The IP address of the machine running the flask end-point (server).
	 */
	protected String ipAddressString = null;
	/**
	 * The port number the Flask end-point is running on.
	 */
	protected int portNumber = 5100;
	/**
	 * The name of the Flask end-point to invoke.
	 */
	protected String flaskEndPointName = null;
	/**
	 * A JSON object holding a collection of end-point argument parameters (key/value pairs).
	 */
	protected JSONObject endPointParameters = null;
	/**
	 * Whether the end-point is a post or get end-point.
	 */
	protected boolean doPost = true;
	/**
	 * The URL defining the end-point.
	 */
	protected URL endPointURL = null;
	/**
	 * The connection to the end-point.
	 */
	protected HttpURLConnection endPointConnection = null;
	/**
	 * A JSON object holding the returned end-point values as a collection of (key/value pairs).
	 */
	protected JSONObject endPointReturnValues = null;
	/**
	 * If an exception is generated when an end-point call is invoked via a Thread (run method), any generated
	 * exception is stored here.
	 */
	protected Exception runException = null;
	
	/**
	 * Constructor. Constructs the endPointParameters object.
	 * @see #endPointParameters
	 */
	public EndPoint()
	{
		super();
		endPointParameters = new JSONObject();
		doPost = true;
	}

	/**
	 * Set the IP address of the machine the end-point is running on.
	 * @param ipAddress The IP address of the machine the end-point is running on.
	 * @see #ipAddressString
	 */
	public void setIPAddress(String ipAddress)
	{
		ipAddressString = ipAddress;
	}

	/**
	 * Set the port number of Flask container the end-point is running on.
	 * @param n The port number the end-point is running on.
	 * @see #portNumber
	 */
	public void setPortNumber(int n)
	{
		portNumber = n;
	}
		
	/**
	 * Set the end-point name.
	 * @param name The name of the flask end-point.
	 * @see #flaskEndPointName
	 */
	public void setFlaskEndPointName(String name)
	{
		flaskEndPointName = name;
	}

	/**
	 * Add a string end-point argument parameter.
	 * @param key The key (name) of the parameter.
	 * @param value The string value of that parameter.
	 * @see #endPointParameters
	 */
	public void addParameter(String key, String value)
	{
		endPointParameters.put(key,value);
	}
	
	/**
	 * Add a integer end-point argument parameter.
	 * @param key The key (name) of the parameter.
	 * @param value The integer value of that parameter.
	 * @see #endPointParameters
	 */
	public void addParameter(String key, int value)
	{
		endPointParameters.put(key,value);
	}
	
	/**
	 * Add a double end-point argument parameter.
	 * @param key The key (name) of the parameter.
	 * @param value The double value of that parameter.
	 * @see #endPointParameters
	 */
	public void addParameter(String key,double value)
	{
		endPointParameters.put(key,value);
	}
	
	/**
	 * Add a boolean end-point argument parameter.
	 * @param key The key (name) of the parameter.
	 * @param value The boolean value of that parameter.
	 * @see #endPointParameters
	 */
	public void addParameter(String key,boolean value)
	{
		endPointParameters.put(key,value);
	}
	
	/**
	 * Set the end-point input parameters from the specified JSON string. 
	 * e.g. "{\"exposure_time\": 10.0, \"filename\":\"expose.fits\"}".
	 * @param jsonString A string representing a JSON object, i.e. a set of key/value pairs.
	 * @see #endPointParameters
	 */
	public void setParameters(String jsonString)
	{
		endPointParameters = new JSONObject(jsonString);
	}

	/**
	 * Set the end-point to be one that is posted to (as opposed to GET from).
	 * @see #doPost
	 */
	public void setDoPost()
	{
		doPost = true;
	}

	/**
	 * Set the end-point to be one that is GETed from (as opposed to POSTed to).
	 * @see #doPost
	 */
	public void setDoGet()
	{
		doPost = false;
	}
	
	/**
	 * run method. Depending on the doPost variable, either invoke doPostRequest or doGetRequest.
	 * The end-point ip address, port number, method name, 
	 * and input parameters should have been setup prior to calling this methos.
	 * @see #doPost
	 * @see #doPostRequest
	 * @see #doGetRequest
	 * @see #runException
	 */
	public void run()
	{
		try
		{
			if(doPost)
				doPostRequest();
			else
				doGetRequest();
		}
		catch(Exception e)
		{
			runException = e;
		}
	}

	/**
	 * Perform a POST request on the Flask end-point.
	 * <ul>
	 * <li>Create the end-point URL by calling createEndPointURL .
	 * <li>Create end-point connection by calling createEndPointConnection .
	 * <li>Configure the created end-point connection (endPointConnection), do output, request method POST,
	 *     content type "application/json", charset "utf-8".
	 * <li>Call createPostData to create the data to post from the endPointParameters.
	 * <li>Create a new DataOutputStream on the opened connection, and write the post data to it (and flush this).
	 * <li>Check the endPointConnection response code is 200 (all good), otherwise throw an error.
	 * <li>Create a buffered reader and read in a multi-line string from the connection's input stream.
	 * <li>Create a new JSONObject (endPointReturnValues) containing the parsed reply string 
	 *     (which should be valid JSON).
	 * <li>Finally, close the endPointConnection.
	 * </ul>
	 * @see #createEndPointURL
	 * @see #createEndPointConnection
	 * @see #endPointConnection
	 * @see #createPostData
	 * @see #endPointURL
	 * @see #endPointReturnValues
	 * @exception MalformedURLException Thrown if the URL is wrong.
	 * @exception IOException Thrown if en error occurs writing to/reading from the end-point.
	 * @exception RuntimeException Thrown if invoking the Flask POST end-point returns a non-success response code.
	 * @exception JSONException Thrown if the endPointParameters JSONObject cannot be represented as a string, or
	 *            the reply JSON from the invoked Flask service cannot be parsed.
	 */
	public void doPostRequest() throws MalformedURLException, IOException, RuntimeException, JSONException
	{
		DataOutputStream os = null;
		BufferedReader br = null;
		String outputString, lineOutputString;
		byte[] postData = null;

		// Create the end point URL object from input ip address/port number/endpoint
		createEndPointURL();
		try
		{
			// create the endPointConnection from the URL
			createEndPointConnection();
			// Configure the endPointConnection to do a POST
			endPointConnection.setDoOutput(true);
			endPointConnection.setRequestMethod("POST");
			endPointConnection.setRequestProperty("Content-Type", "application/json");
			endPointConnection.setRequestProperty( "charset", "utf-8");
			// create the end-point post parameter data (method key/value pairs) from the parameter JSON.
			postData = createPostData();
			// write the parameter data to the conenction's output stream
			os = new DataOutputStream(endPointConnection.getOutputStream());
			os.write(postData);
			os.flush();
			if (endPointConnection.getResponseCode() != 200)
			{
				throw new RuntimeException(this.getClass().getName()+":Failed accessing end-point: "+endPointURL+
							   " with parameters: "+endPointParameters.toString()+
							   " : HTTP error code : "+ endPointConnection.getResponseCode());
			}
			// read the end-point's reply data
			br = new BufferedReader(new InputStreamReader((endPointConnection.getInputStream())));
			outputString = new String();
			while ((lineOutputString = br.readLine()) != null)
			{
				outputString = new String(outputString + lineOutputString);
			}
			// parse the returned output data into a JSONObject
			endPointReturnValues = new JSONObject(outputString);
		}
		finally
		{
			if(endPointConnection != null)
			{
				endPointConnection.disconnect();
			}
		}
	}

	/**
	 * Perform a GET request on the Flask end-point. <b>NOT IMPLEMENTED YET.</b>
	 * @see #createEndPointURL
	 * @see #createEndPointConnection
	 * @see #endPointConnection	
	 * @exception MalformedURLException Thrown if the URL is wrong.
	 * @exception IOException Thrown if en error occurs writing to/reading from the end-point..
	 */
	public void doGetRequest() throws MalformedURLException, IOException
	{
		createEndPointURL();
		createEndPointConnection();
	}

	/**
	 * Get the parsed JSON return values from the Flask API call.
	 * @return An instance of JSONObject containing the JSON key/value pairs returned from the Flask API call.
	 * @see #endPointReturnValues
	 */
	public JSONObject getReturnValues()
	{
		return endPointReturnValues;
	}
	
	/**
	 * Get the parsed JSON return value as a String for the specified key from the Flask API call.
	 * @param key A string representing a valid key in the JSONObject.
	 * @return A string containing the String value for the specified key from the JSON key/value 
	 *         pairs returned from the Flask API call.
	 * @see #endPointReturnValues
	 * @exception JSONException Thrown if there is no string value for the key.
	 */
	public String getReturnValueString(String key) throws JSONException
	{
		return endPointReturnValues.getString(key);
	}
	
	/**
	 * Get the parsed JSON return value as an integer for the specified key from the Flask API call.
	 * @param key A string representing a valid key in the JSONObject.
	 * @return An integer containing the value for the specified key from the JSON key/value 
	 *         pairs returned from the Flask API call.
	 * @see #endPointReturnValues
	 * @exception JSONException Thrown if the key is not found or if the value cannot be converted to an integer.
	 */
	public int getReturnValueInteger(String key) throws JSONException
	{
		return endPointReturnValues.getInt(key);
	}
	
	/**
	 * Get the parsed JSON return value as a double for the specified key from the Flask API call.
	 * @param key A string representing a valid key in the JSONObject.
	 * @return An double containing the value for the specified key from the JSON key/value 
	 *         pairs returned from the Flask API call.
	 * @see #endPointReturnValues
	 * @exception JSONException Thrown if the key is not found or if the value cannot be converted to a double.
	 */
	public double getReturnValueDouble(String key) throws JSONException
	{
		return endPointReturnValues.getDouble(key);
	}
	
	/**
	 * Get the parsed JSON return value as a boolean for the specified key from the Flask API call.
	 * @param key A string representing a valid key in the JSONObject.
	 * @return A boolean containing the value for the specified key from the JSON key/value 
	 *         pairs returned from the Flask API call.
	 * @see #endPointReturnValues
	 * @exception JSONException Thrown if the key is not found or if the value cannot be converted to a boolean.
	 */
	public boolean getReturnValueBoolean(String key) throws JSONException
	{
		return endPointReturnValues.getBoolean(key);
	}
	
	/**
	 * Return an exception generated when an end-point call is invoked via a Thread (run method).
	 * @return An exception if one was generated during an end-point call, or null otherwise.
	 * @see #runException
	 */
	public Exception getRunException()
	{
		return runException;
	}
	
	// protected methods
	/**
	 * Create the end-point URL.
	 * @see #ipAddressString
	 * @see #portNumber
	 * @see #flaskEndPointName
	 * @see #endPointURL
	 * @exception MalformedURLException Thrown if the URL is wrong.
	 */
	protected void createEndPointURL() throws MalformedURLException
	{
		String urlString = new String("http://"+ipAddressString+":"+portNumber+"/"+flaskEndPointName);
		endPointURL = new URL(urlString);
	}

	/**
	 * Create a connection to the end-point URL.
	 * @see #endPointURL
	 * @see #endPointConnection
	 * @exception IOException Thrown if we cannot get a connection from the end-point URL.
	 */
	protected void createEndPointConnection() throws IOException
	{
		endPointConnection = (HttpURLConnection) endPointURL.openConnection();
	}

	/**
	 * Create a byte array with the data posted to the end-point url. This is the invoked method parameters,
	 * formatted as UTF-8 characters in a json-style string.
	 * @return A byte array, containing a UTF-8 byte representation of the JSON string formed by the contents of
	 *         the endPointParameters JSONObject.
	 * @see #endPointParameters
	 * @exception JSONException Thrown if the endPointParameters JSONObject cannot be represented as a string.
	 */
	protected byte[] createPostData() throws JSONException
	{
		String inputData = null;
		byte[] postData = null;

		inputData = endPointParameters.toString();
		postData = inputData.getBytes(StandardCharsets.UTF_8);
		return postData;
	}
}
