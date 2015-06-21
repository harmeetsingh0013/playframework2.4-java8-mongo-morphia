/**
 * 
 */
package repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

public interface GenericRepo<T> {

	public Optional<ObjectId> save(T entity);
	public Optional<List<T>> findAll();
	public Optional<List<T>> findAll(int page, int pageSize);
	public Optional<T> findById(ObjectId id);
	public Optional<Boolean> removeById(ObjectId id);
	public Optional<Long> count();
}
