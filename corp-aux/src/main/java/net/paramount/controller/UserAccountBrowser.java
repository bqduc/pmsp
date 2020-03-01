package net.paramount.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.paramount.auth.entity.UserAccount;
import net.paramount.auth.service.UserAccountService;
import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.domain.model.Filter;

/**
 * @author ducbq
 */
@Named(value = "userAccountBrowser")
@ViewScoped
public class UserAccountBrowser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3867358136696124359L;
	@Inject
	private UserAccountService businessService;
	private List<UserAccount> selectedObjects;
	private List<UserAccount> businessObjects;
	private Filter<UserAccount> bizFilter = new Filter<>(new UserAccount());
	private List<UserAccount> filteredObjects;// datatable filteredValue attribute (column filters)

	private String instantSearch;
	Long id;

	Filter<UserAccount> filter = new Filter<>(new UserAccount());

	List<UserAccount> filteredValue;// datatable filteredValue attribute (column filters)

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
		filter = new Filter<UserAccount>(new UserAccount());
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
			for (UserAccount removalItem : this.selectedObjects) {
				System.out.println("#" + removalItem.getDisplayName());
				this.businessObjects.remove(removalItem);
			}
			this.selectedObjects.clear();
		}
	}

	public List<UserAccount> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<UserAccount> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public Filter<UserAccount> getFilter() {
		return filter;
	}

	public void setFilter(Filter<UserAccount> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserAccount> getBusinessObjects() {
		return businessObjects;
	}

	public void setBusinessObjects(List<UserAccount> businessObjects) {
		this.businessObjects = businessObjects;
	}

	public List<UserAccount> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<UserAccount> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public Filter<UserAccount> getBizFilter() {
		return bizFilter;
	}

	public void setBizFilter(Filter<UserAccount> bizFilter) {
		this.bizFilter = bizFilter;
	}

	public List<UserAccount> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<UserAccount> filteredObjects) {
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
