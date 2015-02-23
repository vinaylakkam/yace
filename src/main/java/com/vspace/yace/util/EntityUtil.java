package com.vspace.yace.util;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.vspace.yace.domain.IdentityDO;
import com.vspace.yace.domain.Timesheet;
import com.vspace.yace.domain.UserRelatedDO;

public class EntityUtil {

	/**
	 * Look up the entity of the given class with the given id in the given
	 * collection.
	 *
	 * @param entities the collection to search
	 * @param entityClass the entity class to look up
	 * @param entityId the entity id to look up
	 * @return the found entity (null if the entity was not found)
	 */
	public static <T extends IdentityDO> T getById(Collection<T> entities, Class<T> entityClass, int entityId) {
		
		for( T entity: entities) {
			if(entity.getId().intValue() == entityId && entityClass.isInstance(entity)) {
				return entity;
			}
		}
		// throw new ObjectRetrievalFailureException(entityClass, new Integer(entityId));
		return null;
	}
	
	/**
	 * Look up the entity of the given class with the given id in the given
	 * collection.
	 *
	 * @param entities the collection to search
	 * @param entityClass the entity class to look up
	 * @param entityId the entity user id to look up
	 * @return the found entity (null if the entity was not found)
	 */
	public static <T extends UserRelatedDO> T getByUserId(Collection<T> entities, Class<T> entityClass, int entityUserId) {
		
		for( T entity: entities) {
			if(entity.getUserId().intValue() == entityUserId && entityClass.isInstance(entity)) {
				return entity;
			}
		}
		// throw new ObjectRetrievalFailureException(entityClass, new Integer(entityId));
		return null;
	}

}
