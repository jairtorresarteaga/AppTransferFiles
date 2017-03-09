package wasdev.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.IOUtils;

 
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;


import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.User;

import org.openstack4j.model.common.Payload;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.model.common.DLPayload;
//
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
	public static final String PROJECT_NAME = "object_storage_474c1d83_4d7d_41c4_be9e_ed8141cbfd39";
	public static final String domainName = "1258017";
	   
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("Empieza la prueba de comunicación Nro0626-> Jair Torres..!!!");
        
		System.out.println("Inicio de Proceso");
		
		//ObjectStorageService objectStorage;
		
		
		ObjectStorageService objectStorage = authenticateAndGetObjectStorageService();

		System.out.println("Retrieving file from ObjectStorage...");
		//System.out.println(objectStorage.containers().toString());
        
		String containerName = "TdP-SIA";

		String fileName = "C:\\Pagina_4.xps";
		
		SwiftObject pictureObj = objectStorage.objects().get(containerName,fileName);

		if(pictureObj == null){ //The specified file was not found
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			System.out.println("File not found.");
			return;
		}
		InputStream inputStream = null;
		BufferedReader br = null;
		try {
		
			// read this file into InputStream
			inputStream = new FileInputStream(fileName);

			br = new BufferedReader(new InputStreamReader(inputStream));

			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			System.out.println(sb.toString());
			System.out.println("\nDone!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		final InputStream fileStream = inputStream;
		
		System.out.println("Inicio PayloadClass...");
		
		
		Payload<InputStream> payload = new PayloadClass(fileStream);
		System.out.println("Fin PayloadClass...");
		objectStorage.objects().put(containerName, fileName, payload);
		
		System.out.println("Successfully stored file in ObjectStorage!");
		
		
    }
	private class PayloadClass implements Payload<InputStream> {
		private InputStream stream = null;

		public PayloadClass(InputStream stream) {
			this.stream = stream;
		}

		@Override
		public void close() throws IOException {
			stream.close();
		}

		@Override
		public InputStream open() {
			return stream;
		}

		@Override
		public void closeQuietly() {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}

		@Override
		public InputStream getRaw() {
			return stream;
		}

	}
    private ObjectStorageService authenticateAndGetObjectStorageService() {
		
    	
    	String OBJECT_STORAGE_AUTH_URL = "https://identity.open.softlayer.com/v3";
		
		System.out.println("Inicio de Proceso - 1-----------------------");
		try {
			Identifier domainIdentifier = Identifier.byId(DOMAIN_ID);
			
			System.out.println("2--------------------------");
			
			System.out.println(OBJECT_STORAGE_AUTH_URL);
		
		
			OSClientV3 os = OSFactory.builderV3()
					.endpoint(OBJECT_STORAGE_AUTH_URL)
					.credentials(USERNAME,PASSWORD, domainIdentifier)
					//.scopeToProject(Identifier.byId(PROJECT_ID) )
					.scopeToProject(Identifier.byId(PROJECT_ID),Identifier.byId(DOMAIN_ID) )
					.authenticate();


			
			System.out.println("Authenticated successfully!");
			ObjectStorageService objectStorage = os.objectStorage();
	
			return objectStorage;
		
		}catch(Exception e){
			System.out.println("Error durante proceso de autenticación...");
	        System.out.println(e);
	        e.printStackTrace();
	                
	        return null;
		}
	
	}
    
    
    
}
