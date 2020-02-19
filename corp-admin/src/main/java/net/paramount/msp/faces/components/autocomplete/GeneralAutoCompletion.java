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
package net.paramount.msp.faces.components.autocomplete;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;

import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.css.service.org.BusinessUnitService;
import net.paramount.entity.general.BusinessUnit;
import net.paramount.entity.general.Country;

@Named
@ViewScoped
public class GeneralAutoCompletion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2483732154322853618L;

	private static List<Country> countryList = ListUtility.createDataList();
	
	/*private Item item;
	private List<Item> selectedItems;

	@Inject
	private ItemService businessService;*/

	@Inject
	private BusinessUnitService businessUnitService;

	public List<BusinessUnit> onCompleteBusinessUnit(String keyword) {
		Page<BusinessUnit> searchResult = businessUnitService.search(keyword);
		//System.out.println("onCompleteBusinessUnit: " + keyword);
		return searchResult.getContent();
	}

	public List<Country> suggestCountries(String keyword) {
		List<Country> availableCountries = generateCountryList();
		List<Country> fetchedCountries = ListUtility.createDataList();
		for (Country country :availableCountries) {
			if (StringUtils.containsIgnoreCase(country.getCode(), keyword) || StringUtils.containsIgnoreCase(country.getIsoCode(), keyword) || StringUtils.containsIgnoreCase(country.getName(), keyword) ) {
				fetchedCountries.add(country);
			}
		}
		return fetchedCountries;
	}

	private List<Country> generateCountryList() {
		if (!countryList.isEmpty())
			return countryList;

    String[] isoCountries = Locale.getISOCountries();
    for (String country : isoCountries) {
      Locale locale = new Locale(CommonUtility.LOCALE_US.getLanguage(), country);
      countryList.add(Country.builder()
    			.code(locale.getCountry())
    			.isoCode(locale.getISO3Country())
    			.name(locale.getDisplayCountry())
    			.build());
    }
		return countryList;
	}
	/*public List<Item> completeItem(String query) {
		List<Item> allItems = businessService.getObjects();
		List<Item> filteredItems = ListUtility.createDataList();

		for (int i = 0; i < allItems.size(); i++) {
			Item skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public List<Item> completeItemContains(String query) {
		List<Item> allItems = businessService.getObjects();
		List<Item> filteredItems = ListUtility.createDataList();

		for (int i = 0; i < allItems.size(); i++) {
			Item skin = allItems.get(i);
			if (skin.getName().toLowerCase().contains(query.toLowerCase())) {
				filteredItems.add(skin);
			}
		}

		return filteredItems;
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
	}

	public List<Item> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<Item> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public char getItemGroup(Item item) {
		return item.getName().charAt(0);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(ItemService businessService) {
		this.businessService = businessService;
	}*/

}
