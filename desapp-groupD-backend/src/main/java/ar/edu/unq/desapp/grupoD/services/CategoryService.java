package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.Category;
import ar.edu.unq.desapp.grupoD.persistence.CategoryDao;

/**
 * 
 * @author Lucas
 */
public class CategoryService {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Transactional(readOnly=true)
	public List<Category> findAll(){
		return categoryDao.findAll();
	}

	@Transactional
	public void removeCategory(String name) {
		this.categoryDao.removeCategoryByName(name);
	}

	@Transactional
	public void save(Category category) {
		this.categoryDao.save(category);
	}
	
	@Transactional
	public void update(String name, Integer idCat) {
		this.categoryDao.update(name, idCat);
	}
	@Transactional
	public Category findByName(String categoryName) {
		return this.categoryDao.getByName(categoryName);
	}
}