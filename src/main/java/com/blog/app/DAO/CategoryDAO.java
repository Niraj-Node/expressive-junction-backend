package com.blog.app.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.blog.app.entities.Category;

import jakarta.persistence.EntityManager;

@Repository
public class CategoryDAO {

	private EntityManager entityManager;
	
	@Autowired
	public CategoryDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<Category> getAllCategory() {
		return	entityManager.createQuery("from Category", Category.class).getResultList();
	}
	
	public Category getCategory(Long categoryId) {
		return entityManager.find(Category.class, categoryId);
	}

	public Category addCategory(Category category) {
        entityManager.merge(category);
        return category;
	}

	public Category updateCategory(Long categoryId, Category updatedCategory) {
		updatedCategory.setId(categoryId);
        return entityManager.merge(updatedCategory);
    }
	
	public void deleteCategory(Long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        entityManager.remove(category);
	}
	
}
