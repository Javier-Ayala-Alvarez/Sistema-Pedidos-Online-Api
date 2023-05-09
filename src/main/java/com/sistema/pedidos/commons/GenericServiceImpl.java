package com.sistema.pedidos.commons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T,ID extends Serializable> implements  GenericServiceApi<T,ID>{

    @Override
    public T save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDao().findById(id);
        return obj.orElse(null);
    }

    @Override
    public List<T> getAll() {
        ArrayList<T> returnList = new ArrayList<>();
        getDao().findAll().forEach(returnList::add);
        return  returnList;
    }
    @Override
    public Page<T> getAllWithPagination(Pageable pageable) {
        return getDao().findAll(pageable);
    }



    public abstract JpaRepository<T,ID> getDao();
}
