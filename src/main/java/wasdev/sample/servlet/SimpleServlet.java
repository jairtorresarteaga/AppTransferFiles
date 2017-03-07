package wasdev.sample.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
import org.apache.commons.io.IOUtils;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.openstack.OSFactory;
*/

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")


public class SimpleServlet extends HttpServlet {
    
	public static final long serialVersionUID = 1L;

	//Get these credentials from Bluemix by going to your Object Storage service, and clicking on Service Credentials:
	//private static final String USERNAME = "PUT_YOUR_OBJECT_STORAGE_USERNAME_HERE";
	//private static final String PASSWORD = "PUT_YOUR_OBJECT_STORAGE_PASSWORD_HERE";
	//private static final String DOMAIN_ID = "PUT_YOUR_OBJECT_STORAGE_DOMAIN_ID_HERE";
	//private static final String PROJECT_ID = "PUT_YOUR_OBJECT_STORAGE_PROJECT_ID_HERE";

	public static final String USERNAME = "admin_294cf97bd70edab00606c4d8d7a193dd344edec2";
	public static final String PASSWORD = "K3-fxy4AWm*P*a{g";
	public static final String DOMAIN_ID = "b3fa3bbc790244ceba58b583fbf8c5f5";
	public static final String PROJECT_ID = "10c9e4313e824aaea9dde317b7b41451";
	
    /**
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Empieza la prueba de comunicación-> Jair Torres..!!!");
        
		String OBJECT_STORAGE_AUTH_URL = "https://identity.open.softlayer.com/v3";
		System.out.println("Inicio de Proceso");

	System.out.println("Successfully retrieved file from ObjectStorage!");
		
		System.out.println("Authenticating...");
 
          
    }
    


}
