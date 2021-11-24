package example;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Main {

   public static void main( String args[] ) {

     // Mongodb initialization parameters.
     int port_no = 27017;
     String auth_user="admin";
     String auth_pwd = "admin";
     String host_name = "db";
     String db_name = "test";
     String encoded_pwd = "";

     try {
         encoded_pwd = URLEncoder.encode(auth_pwd, "UTF-8");
     } catch (UnsupportedEncodingException ex) {
         //log.error(ex);
     }

     // Mongodb connection string.
     String client_url = "mongodb://" + auth_user + ":" + encoded_pwd + "@" + host_name + ":" + port_no + "/" + db_name;
     MongoClientURI uri = new MongoClientURI(client_url);

     // Connecting to the mongodb server using the given client uri.
     MongoClient mongo_client = new MongoClient(uri);

     // Fetching the database from the mongodb.
     MongoDatabase db = mongo_client.getDatabase(db_name);

     for (String name : db.listCollectionNames()) {
        System.out.println("[OUTPUT] " + name);
     }
   }
}
