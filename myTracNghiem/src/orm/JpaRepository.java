package orm;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<T,ID extends Serializable> {
    void save(T t);
    void update(ID id,T t);
    Optional<T> findById(ID id);
    T getOne(ID id);
    List<T> findAll();
    long count();
    List<T> findAllByAColumn(String column);
    boolean delete(ID id);
   
}
