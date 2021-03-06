package ar.edu.unq.desapp.grupoD.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoD.model.category.Concept;
import ar.edu.unq.desapp.grupoD.model.category.SubCategory;
import ar.edu.unq.desapp.grupoD.persistence.ConceptDao;
import ar.edu.unq.desapp.grupoD.persistence.SubcategoryDao;

public class ConceptService {
	
	private SubcategoryDao subcategoryDao;
	private ConceptDao conceptDao;
	
	public void setSubcategoryDao(SubcategoryDao subcategoryDao) {
		this.subcategoryDao = subcategoryDao;
	}

	public void setConceptDao(ConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}

	@Transactional(readOnly=true)
	public List<Concept> findAll() {
		return conceptDao.findAll();
	}

	@Transactional
	public void removeConceptByName(String name, Integer idSub){
		SubCategory subcategory = subcategoryDao.findById(idSub);
		Concept concept = conceptDao.getByName(name);
		subcategory.getConcepts().remove(concept);
		subcategoryDao.save(subcategory);
		conceptDao.delete(concept);
	}

	@Transactional
	public void save(Concept concept, Integer idSubcategory) {
		SubCategory toUpdate = subcategoryDao.findById(idSubcategory);
		List<Concept> concepts = toUpdate.getConcepts();
		concepts.add(concept);
		subcategoryDao.save(toUpdate);
	}
	
	@Transactional
	public void update(String name, int id) {
		this.conceptDao.update(name, id);
	}
	
	@Transactional
	public Concept findByName(String conceptName) {
		return this.conceptDao.getByName(conceptName);
	}

}
