package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Category;
import com.projet.evaluation_satge.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

//    public List<Category> saveAllCategories(List<Category> categories) {
//        return categoryRepository.saveAll(categories);
//    }

    public List<Category> saveAllCategories(List<Category> categories) {
        // Add logging for each category being saved
        for (Category category : categories) {
            System.out.println("Saving category: " + category.getIntitule() +
                    " for competence ID: " + category.getCompetenceId());
        }

        // Try saving each category individually to pinpoint any failures
        List<Category> savedCategories = new ArrayList<>();
        for (Category category : categories) {
            try {
                Category saved = categoryRepository.save(category);
                savedCategories.add(saved);
            } catch (Exception e) {
                System.err.println("Error saving category: " + category.getIntitule());
                e.printStackTrace();
            }
        }

        return savedCategories;
    }
}