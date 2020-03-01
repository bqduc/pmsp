package net.paramount.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.paramount.auth.entity.Authority;
import net.paramount.auth.service.AuthorityService;
import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.domain.model.Filter;

/**
 * @author ducbq
 */
@Named(value = "authorityBrowser")
@ViewScoped
public class AuthorityBrowser implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6412581743995405359L;

	@Inject
	private AuthorityService businessService;
	private List<Authority> selectedObjects;
	private List<Authority> businessObjects;
	private Filter<Authority> bizFilter = new Filter<>(new Authority());
	private List<Authority> filteredObjects;// datatable filteredValue attribute (column filters)

	private String instantSearch;
	Long id;

	Filter<Authority> filter = new Filter<>(new Authority());

	List<Authority> filteredValue;// datatable filteredValue attribute (column filters)

	@PostConstruct
	public void initDataModel() {
		try {
			System.out.println("==>Come to authority browser!");
			this.businessObjects = businessService.getObjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		filter = new Filter<Authority>(new Authority());
	}

	public List<String> completeModel(String query) {
		List<String> result = ListUtility.createDataList();// carService.getModels(query);
		return result;
	}

	public void search(String parameter) {
		System.out.println("Searching parameter: " + parameter);
		/*
		 * if (id == null) { throw new BusinessException("Provide Car ID to load"); }
		 * selectedCars.add(carService.findById(id));
		 */
	}

	public void delete() {
		if (CommonUtility.isNotEmpty(this.selectedObjects)) {
			for (Authority removalItem : this.selectedObjects) {
				System.out.println("#" + removalItem.getName());
				this.businessObjects.remove(removalItem);
			}
			this.selectedObjects.clear();
		}
	}

	public List<Authority> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Authority> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public Filter<Authority> getFilter() {
		return filter;
	}

	public void setFilter(Filter<Authority> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Authority> getBusinessObjects() {
		return businessObjects;
	}

	public void setBusinessObjects(List<Authority> businessObjects) {
		this.businessObjects = businessObjects;
	}

	public List<Authority> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<Authority> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public Filter<Authority> getBizFilter() {
		return bizFilter;
	}

	public void setBizFilter(Filter<Authority> bizFilter) {
		this.bizFilter = bizFilter;
	}

	public List<Authority> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<Authority> filteredObjects) {
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
