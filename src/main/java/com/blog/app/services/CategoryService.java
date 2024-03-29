package com.blog.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.app.DAO.CategoryDAO;
import com.blog.app.entities.Category;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	private CategoryDAO categoryDAO;
	
	@Autowired
	public CategoryService(CategoryDAO theCategoryDAO) {
		categoryDAO = theCategoryDAO;
	}
	
	@Transactional
	public List<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}

	@Transactional
	public Category getCategory(Long categoryId) {
		Category category = categoryDAO.getCategory(categoryId);
		if (category == null) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }
        return category;
	}
	
	@Transactional
    public Category addCategory(Category category) {
		return categoryDAO.addCategory(category);
    }
	
	@Transactional
    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Category existingCategory = categoryDAO.getCategory(categoryId);
        if (existingCategory == null) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }
        return categoryDAO.updateCategory(categoryId, updatedCategory);
    }
	
	@Transactional
	public void deleteCategory(Long categoryId) {
		Category existingCategory = categoryDAO.getCategory(categoryId);
		if (existingCategory == null) {
            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
        }
		categoryDAO.deleteCategory(categoryId);
	}


}