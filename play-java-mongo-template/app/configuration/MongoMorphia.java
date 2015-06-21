/**
 * 
 */
package configuration;

import java.net.InetSocketAddress;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import play.Configuration;

import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

@Singleton
public class MongoMorphia {

	private static final Logger logger = LoggerFactory.getLogger(MongoMorphia.class);
	
	private final Morphia morphia = new Morphia();
	private Configuration configuration = Configuration.root();
	
	private MongoClientOptions mongoClientOptions(){
		Builder builder = new Builder();
		builder.connectionsPerHost(configuration.getInt("connections-per-host"));
		builder.connectTimeout(configuration.getInt("connections-timeout"));
		builder.maxConnectionIdleTime(configuration.getInt("max-connections-idle-time"));
		builder.maxConnectionLifeTime(configuration.getInt("max-connections-life-time"));
		builder.minConnectionsPerHost(configuration.getInt("max-connections-per-host"));
		builder.serverSelectionTimeout(configuration.getInt("server-selection-timeout"));
		builder.socketKeepAlive(configuration.getBoolean("socket-keep-live"));
		builder.socketTimeout(configuration.getInt("socket-timeout"));
		return builder.build();
	}
	
	private ServerAddress serverAddress(){
		ServerAddress serverAddress = new ServerAddress(new InetSocketAddress(configuration.getString("mongodb.uri"), configuration.getInt("mongodb.port")));
		return serverAddress;
	}
	
	private MongoClient mongoClient(){
		return new MongoClient(serverAddress(), mongoClientOptions());
	}
	
	public Datastore dataStore() {
		logger.info("dataStore method in Configuration");
		
		morphia.mapPackage(configuration.getString("package.scan"));
		return morphia.createDatastore(mongoClient(), configuration.getString("mongodb.name"));
	}
}
