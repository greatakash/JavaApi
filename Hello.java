package test;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import java.net.UnknownHostException;
import java.util.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Application;

@Path("/hello")
//@XmlRootElement(name = "customers")
public class Hello {
		
	@POST
	@Path("/showme")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello1(@FormParam("description") String description) {
		String ram = description;
		//System.out.println("cool"+ram);
		
		DBObject doc =new BasicDBObject();
		MongoClient mongo;
		try {
			mongo = new MongoClient("localhost", 27017);
		
		DB db = mongo.getDB("testing");
		
		DBCollection col = db.getCollection("users");
		doc.put("name", ram);
		
		col.insert(doc);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		String status = "Data Inserted" ;
		//return Response.status(201).entity(ram).build();
		return ram;
		
	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloHtml(@QueryParam("name") String name){
		//String resource="<h1>Hi afshenn html</h1>";
		//System.out.println(resource);
		ArrayList<DBObject> lDataList = new ArrayList<DBObject>();
		MongoClient mongo;
		try {
			mongo = new MongoClient("localhost", 27017);
			
			DB db = mongo.getDB("testing");
			
		DBCollection col = db.getCollection("users");
		DBObject query = BasicDBObjectBuilder.start().add("name", name).get();
		DBCursor cursor = col.find(query);
		List<DBObject> array = new ArrayList<DBObject>();
		//while(cursor.hasNext()){
			
			if (cursor != null)
				array = cursor.toArray();
			if (array != null) {
				for (int i = 0; i < array.size(); i++) {
					lDataList.add(array.get(i));
				}
			}
			//store all result in one vaiable and return it
		//	response[] = cursor.next();
		//}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data=lDataList.get(0).toString();
		//String data="akash raj";
		return data;
	}
	
	
}
