package net.paramount.dbx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.dbx.entity.Dashboard;
import net.paramount.dbx.service.DashboardService;
import net.paramount.domain.model.Filter;

/**
 * @author ducbq
 */
@Named(value = "dashboardBrowser")
@ViewScoped
public class DashboardBrowser implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -6412581743995405359L;

	@Inject
	private DashboardService businessService;
	private List<Dashboard> selectedObjects;
	private List<Dashboard> businessObjects;
	private Filter<Dashboard> bizFilter = new Filter<>(new Dashboard());
	private List<Dashboard> filteredObjects;// datatable filteredValue attribute (column filters)

	private String instantSearch;
	Long id;

	Filter<Dashboard> filter = new Filter<>(new Dashboard());

	List<Dashboard> filteredValue;// datatable filteredValue attribute (column filters)

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
		filter = new Filter<Dashboard>(new Dashboard());
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
			for (Dashboard removalItem : this.selectedObjects) {
				System.out.println("#" + removalItem.getName());
				this.businessObjects.remove(removalItem);
			}
			this.selectedObjects.clear();
		}
	}

	public List<Dashboard> getFilteredValue() {
		return filteredValue;
	}

	public void setFilteredValue(List<Dashboard> filteredValue) {
		this.filteredValue = filteredValue;
	}

	public Filter<Dashboard> getFilter() {
		return filter;
	}

	public void setFilter(Filter<Dashboard> filter) {
		this.filter = filter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Dashboard> getBusinessObjects() {
		return businessObjects;
	}

	public void setBusinessObjects(List<Dashboard> businessObjects) {
		this.businessObjects = businessObjects;
	}

	public List<Dashboard> getSelectedObjects() {
		return selectedObjects;
	}

	public void setSelectedObjects(List<Dashboard> selectedObjects) {
		this.selectedObjects = selectedObjects;
	}

	public Filter<Dashboard> getBizFilter() {
		return bizFilter;
	}

	public void setBizFilter(Filter<Dashboard> bizFilter) {
		this.bizFilter = bizFilter;
	}

	public List<Dashboard> getFilteredObjects() {
		return filteredObjects;
	}

	public void setFilteredObjects(List<Dashboard> filteredObjects) {
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
