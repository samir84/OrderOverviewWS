package com.hs.eai.orderoverview.common;

import java.util.List;
import java.util.Map;
/**
 * * Method that returns the number of entries from a table that meet some
 * criteria (where clause params)
 * 
 * @author samir.elazzouzi
 *
 * @param <T>
 * sql parameters
 * @return the number of records meeting the criteria
 */
public interface GenericDao<T> {
 
	T find(Object id);
	
    long countAll();

    T create(T t);

    void delete(Object id);

    T update(T t);   
    
    List<T> findAll();

	void deleteById(Object id);
	
	//List<T> findByProperties(Map<String, Object> params);
}