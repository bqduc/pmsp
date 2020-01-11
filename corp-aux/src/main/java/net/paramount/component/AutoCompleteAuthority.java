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
package net.paramount.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import net.paramount.auth.entity.Authority;
import net.paramount.auth.service.AuthorityService;
import net.paramount.common.ListUtility;

@Named(value="autoCompleteAuthority")
@ViewScoped
public class AutoCompleteAuthority implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3953585001633751748L;

	private Authority item;
	private List<Authority> selectedItems;

	@Inject
	private AuthorityService businessService;

	public List<String> completeText(String query) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public List<Authority> completeItem(String query) {
		List<Authority> allItems = businessService.getObjects();
		List<Authority> filteredItems = ListUtility.createDataList();
		Authority skin = null;
		for (int i = 0; i < allItems.size(); i++) {
			skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public List<Authority> completeItemContains(String query) {
		List<Authority> allItems = businessService.getObjects();
		List<Authority> filteredItems = ListUtility.createDataList();

		for (int i = 0; i < allItems.size(); i++) {
			Authority skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Authority Selected", event.getObject().toString()));
	}

	public List<Authority> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Authority> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public char getItemGroup(Authority item) {
		return item.getName().charAt(0);
	}

	public Authority getItem() {
		return item;
	}

	public void setItem(Authority item) {
		this.item = item;
	}

	public AuthorityService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(AuthorityService businessService) {
		this.businessService = businessService;
	}

}
