/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paramount.dbx.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import net.paramount.common.ListUtility;
import net.paramount.dbx.entity.Dashboard;
import net.paramount.dbx.service.DashboardService;

@Named(value="autoCompleteDashboard")
@ViewScoped
public class AutoCompleteDashboard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3953585001633751748L;

	private Dashboard item;
	private List<Dashboard> selectedItems;

	@Inject
	private DashboardService businessService;

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public List<Dashboard> completeItem(String query) {
		List<Dashboard> allItems = businessService.getObjects();
		List<Dashboard> filteredItems = ListUtility.createDataList();
		Dashboard skin = null;
		for (int i = 0; i < allItems.size(); i++) {
			skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public List<Dashboard> completeItemContains(String query) {
		List<Dashboard> allItems = businessService.getObjects();
		List<Dashboard> filteredItems = ListUtility.createDataList();

		for (int i = 0; i < allItems.size(); i++) {
			Dashboard skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Dashboard Selected", event.getObject().toString()));
	}

	public List<Dashboard> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Dashboard> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public char getItemGroup(Dashboard item) {
		return item.getName().charAt(0);
	}

	public Dashboard getItem() {
		return item;
	}

	public void setItem(Dashboard item) {
		this.item = item;
	}

	public DashboardService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(DashboardService businessService) {
		this.businessService = businessService;
	}

}
