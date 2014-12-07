package br.ufjf.pgcc.plscience.workflow;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author vitorfs
 */
public class TavernaClient {
    
    private String baseUri;
    private String username;
    private String password;
    
    public TavernaClient() {
    
    }
    
    public TavernaClient(String baseUri) {
        this.baseUri = baseUri;
    }
    
    public TavernaClient(String baseUri, String username, String password) {
        this.baseUri = baseUri;
        this.username = username;
        this.password = password;
    }
    
    private HttpURLConnection request(String endpoint, TavernaServerMethods method, int expectedResponseCode) throws TavernaException {
        return request(endpoint, method, expectedResponseCode, "text/plain", null, null);
    }

    private HttpURLConnection request(String endpoint, TavernaServerMethods method, int expectedResponseCode, String acceptData) throws TavernaException {
        return request(endpoint, method, expectedResponseCode, acceptData, null, null);
    }
    
    private HttpURLConnection request(String endpoint, TavernaServerMethods method, int expectedResponseCode, String acceptData, String filePath) throws TavernaException {
        return request(endpoint, method, expectedResponseCode, acceptData, filePath, null);
    }
    
    private HttpURLConnection request(String endpoint, TavernaServerMethods method, int expectedResponseCode, String acceptData, String filePath, String putData) throws TavernaException {
        HttpURLConnection connection = null;
        
        try {            
            String uri = this.getBaseUri() + endpoint;
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            String authorization = this.username + ":" + this.password;
            String encodedAuthorization = "Basic "+ new String(Base64.encodeBase64(authorization.getBytes()));
            connection.setRequestProperty ("Authorization", encodedAuthorization);
            connection.setRequestMethod(method.getMethod());
            
            if (acceptData != null) {
                connection.setRequestProperty("Accept", acceptData);
            }

            if (TavernaServerMethods.GET.equals(method)) {
                
            }
            else if (TavernaServerMethods.POST.equals(method)) {
                connection.setRequestProperty("Content-Type", "application/vnd.taverna.t2flow+xml");
                FileReader fr = new FileReader(filePath);
                char[] buffer = new char[1024*10];
                int read;
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                while ((read = fr.read(buffer)) != -1) {
                    writer.write(buffer, 0, read);
                }
                writer.flush();
                writer.close();
                fr.close();
            }
            else if (TavernaServerMethods.PUT.equals(method)) {
                connection.setDoInput(true);
                connection.setRequestProperty("Accept", "text/plain");
                connection.setRequestProperty("Content-Type", "text/plain");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(putData);
                writer.flush();
                writer.close();
            }
            else if (TavernaServerMethods.DELETE.equals(method)) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            
            int responseCode = connection.getResponseCode();
            if (responseCode != expectedResponseCode) {
                throw new TavernaException(String.format("Invalid HTTP Response Code. Expected %d, actual %d, URL %s", expectedResponseCode, responseCode, url));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return connection;
    }
    
    private String parseResponse(HttpURLConnection response) {
        try {
            InputStream responseStream = new BufferedInputStream(response.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = responseStreamReader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }

            responseStreamReader.close();
            String output = stringBuilder.toString();
            responseStream.close();

            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @return the UUID of the workflow run
     */
    public String create(String filePath) throws TavernaException {
        String url = "/runs";
        HttpURLConnection response = request(url, TavernaServerMethods.POST, HttpURLConnection.HTTP_CREATED, "application/json", filePath);
        String uuid = null;
        String location = response.getHeaderField("Location");
        if (location != null) {
            uuid = location.split("/runs/")[1];
        }
        response.disconnect();
        return uuid;
    }
    
    public String getStatus(String uuid) throws TavernaException {
        String url = String.format("/runs/%s/status", uuid);
        HttpURLConnection response = request(url, TavernaServerMethods.GET, HttpURLConnection.HTTP_OK);       
        String content = parseResponse(response);
        if (response != null) {
            response.disconnect();
        }
        if (content != null) {
            content = content.replace("\n", "");
        }
        return content;
    }
    
    public String getOutput(String uuid) throws TavernaException {
        String url = String.format("/runs/%s/wd/out", uuid);
        HttpURLConnection response = request(url, TavernaServerMethods.GET, HttpURLConnection.HTTP_OK, "application/json");
        String content = parseResponse(response);
        if (response != null) {
            response.disconnect();
        }
        return content;        
    }
    
    public void start(String uuid) throws TavernaException {
        String url = String.format("/runs/%s/status", uuid);
        String data = TavernaServerStatus.OPERATING.getStatus();
        HttpURLConnection response = request(url, TavernaServerMethods.PUT, HttpURLConnection.HTTP_ACCEPTED, "text/plain", null, data);
        response.disconnect();
    }
    
    public void destroy(String uuid) throws TavernaException {
        String url = String.format("/runs/%s", uuid);
        HttpURLConnection response = request(url, TavernaServerMethods.DELETE, HttpURLConnection.HTTP_NO_CONTENT);
        response.disconnect();
    }
    
    public void setAuthorization(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the baseUri
     */
    public String getBaseUri() {
        return baseUri;
    }

    /**
     * @param baseUri the baseUri to set
     */
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
