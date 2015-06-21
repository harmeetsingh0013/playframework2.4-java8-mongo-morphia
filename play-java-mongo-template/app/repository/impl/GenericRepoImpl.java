/**
 * 
 */
package repository.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import repository.GenericRepo;

import com.google.inject.Inject;

import configuration.MongoMorphia;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class GenericRepoImpl<T> implements GenericRepo<T>{

	private static final Logger logger = LoggerFactory.getLogger(GenericRepoImpl.class);
	
	private Type type;
	private Datastore datastore;
	@Inject
	private MongoMorphia mongoMorphia; 
	
	public GenericRepoImpl(){
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	private Class getClassType(String typeName){
		try {
			return Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			logger.error("Stacktrace", e);
			return null;
		}
	}
	
	public Datastore getDataStore() {
		logger.info("getDataStore method in Repository");
		
		if(this.datastore != null){
			return this.datastore;
		}else{
			this.datastore = mongoMorphia.dataStore();
			return this.datastore;
		}
	}
	
	@Override
	public  Optional<ObjectId> save(T entity) {
		logger.info("save method in Repository");
		
		Optional<ObjectId> optional = Optional.empty();
		Object obj = getDataStore().save(entity).getId();
		if(obj != null){
			optional = Optional.of((ObjectId) obj);
		}
		return  optional;
	}
	
	@Override
	public Optional<List<T>> findAll() {
		logger.info("findAll method in Repository");
		
		Optional<List<T>> optional = Optional.empty();
		List<T> list = (List<T>) getDataStore().createQuery(getClassType(type.getTypeName())).asList();
		if(list != null){
			optional = Optional.of(list);
		}
		return  optional;
	}
	
	@Override
	public Optional<List<T>> findAll(int page, int pageSize) {
		logger.info("findAll pagination method in Repository");
		
		int from = (page - 1) * pageSize;
		Optional<List<T>> optional = Optional.empty();
		List<T> list = (List<T>) getDataStore().createQuery(getClassType(type.getTypeName())).offset(from).limit(pageSize).asList();
		if(list != null){
			optional = Optional.of(list);
		}
		return  optional;
	}
	
	@Override
	public Optional<T> findById(ObjectId id){
		logger.info("findAll method in Repository");
		
		Optional<T> optional = Optional.empty();
		List<T> list = getDataStore().createQuery(getClassType(type.getTypeName())).filter("_id ==", id).asList();
		if(list != null && !list.isEmpty()){
			optional = Optional.of(list.get(0));
		}
		return optional;
	}
	
	@Override
	public Optional<Boolean> removeById(ObjectId id){
		logger.info("removeById method in Repository");
		
		Optional<Boolean> optional = Optional.empty();
		Query<T> query = getDataStore().createQuery(getClassType(type.getTypeName())).filter("_id ==", id);
		int value = getDataStore().delete(query).getN();
		if(value == 1){
			optional = Optional.of(true);
		}else{
			optional = Optional.of(false);
		}
		return optional;
	}
	
	@Override
	public Optional<Long> count() {
		logger.info("removeById method in Repository");
		
		Optional<Long> optional = Optional.empty();
		Long count = (Long) getDataStore().getCount(getClassType(type.getTypeName()));
		optional = Optional.of(count);
		return optional;
	}
}
