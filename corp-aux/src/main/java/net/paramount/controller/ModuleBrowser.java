package net.paramount.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.paramount.auth.entity.Module;
import net.paramount.auth.service.ModuleService;
import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.domain.model.Filter;
import net.paramount.framework.controller.RootController;

/**
 * @author ducbq
 */
@Named(value = "moduleBrowser")
@ViewScoped
public class ModuleBrowser extends RootController {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3642381066130158269L;

	@Inject
	private ModuleService businessService;
	private List<Module> selectedObjects;
	private List<Module> businessObjects;
	private Filter<Module> bizFilter = new Filter<>(new Module());
	private List<Module> filteredObjects;// datatable filteredValue attribute (column filters)

	private String instantSearch;
	Long id;

	Filter<Module> filter = new Filter<>(new Module());

	List<Module> filteredValue;// datatable filteredValue attribute (column filters)

	@PostConstruct
	public void initDataModel() {
		try {
			System.out.println("==>Come to module browser!");
			this.businessObjects = businessService.getObjects();
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void clear() {
		filter = new Filter<Module>(new Module());
	}

	public List<String> completeModel(String query) {
		List<String> result = ListUtility.createDataList();// carService.getModels(query);
		return result;
	}

	public void search(String parameter) {
		log.info("Searching parameter: " + parameter);
		/*
		 * if (id == null) { throw new BusinessException("Provide Car ID to load"); }
		 * selectedCars.add(carService.findById(id));
		 */
	}

	public void delete() {
		if (CommonUtility.isNotEmpty(this.selectedObjects)) {
			for (Module removalItem : this.selectedObjects) {
				System.out.println("#" + removalItem.getName());
				this.businessObjects.remove(removalItem);
			}
			this.selectedObjects.clear();
		}
	}

	public List<Module> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Module> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public Filter<Module> getFilter() {
		return filter;
	}

	public void setFilter(Filter<Module> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Module> getBusinessObjects() {
		return businessObjects;
	}

	public void setBusinessObjects(List<Module> businessObjects) {
		this.businessObjects = businessObjects;
	}

	public List<Module> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<Module> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public Filter<Module> getBizFilter() {
		return bizFilter;
	}

	public void setBizFilter(Filter<Module> bizFilter) {
		this.bizFilter = bizFilter;
	}

	public List<Module> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<Module> filteredObjects) {
		this.filteredObjects = filteredObjects;
	}

	public String getInstantSearch() {
		return instantSearch;
	}

	public void setInstantSearch(String instantSearch) {
		this.instantSearch = instantSearch;
	}

	public void recordsRowSelected(AjaxBehaviorEvent e) {
		System.out.println("recordsRowSelected");
	}
}
