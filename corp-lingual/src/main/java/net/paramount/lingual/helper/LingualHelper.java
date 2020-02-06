/**
 * 
 */
package net.paramount.lingual.helper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import net.paramount.common.CommonUtility;
import net.paramount.common.ListUtility;
import net.paramount.entity.general.Country;
import net.paramount.framework.component.ComponentBase;
import net.paramount.lingual.service.CountryService;

/**
 * @author ducbq
 *
 */
@Component
public class LingualHelper extends ComponentBase {
	private static final long serialVersionUID = -5659348796380602585L;

	@Inject 
	private CountryService countryService;

	private List<Country> getAvailableCountries(){
		List<Country> availableCountries = ListUtility.createDataList();
    String[] isoCountries = Locale.getISOCountries();
    Locale locale = null;
    for (String isoCountryCode : isoCountries) {
    	locale = new Locale(CommonUtility.LOCALE_US.getLanguage(), isoCountryCode);
      availableCountries.add(Country.builder()
    			.code(locale.getCountry())
    			.isoCode(locale.getISO3Country())
    			.name(locale.getDisplayCountry())
    			.build());
    }
		return availableCountries;
	}

	public void initAvailableCountries() {
		List<Country> availableCountries = getAvailableCountries();
		for (Country currentCountry :availableCountries) {
			//this.countryService.saveOrUpdate(currentCountry);
		}
	}

	private static void listCountries() {
		// Get all available locales
    List<Locale> availableLocales = Arrays.asList(Locale.getAvailableLocales());

    // Get all available ISO countries
    String[] countryCodes = Locale.getISOCountries();

    // Create a collection of all available countries
    List<Country> countries = new ArrayList<Country>();

    // Map ISO countries to custom country object
    for (String countryCode : countryCodes){

        Optional<Locale> candidate = availableLocales.stream()
                .filter(l -> l.getCountry().equals(countryCode))
                .collect(Collectors.reducing((a, b) -> null));

        Locale locale;
        if (candidate.isPresent()){
            locale = candidate.get();
        } else {
            System.out.println("could not find resource for: " + countryCode + " mapping default lang");
            locale = new Locale("", countryCode);
        }

        String iso = locale.getISO3Country();
        String code = locale.getCountry();
        String country = locale.getDisplayCountry(locale);
        countries.add(Country.builder().code(code).isoCode(iso).isoLanguageCode(country).build());
    }

    // Sort countries
    Collections.sort(countries);

    // Loop over collection of countries and print to console
    countries.forEach((System.out::println));

    // Print out available locales
    System.out.println("available locales: " + availableLocales.size());

    // print total number of countries
    System.out.println("found: " + countries.size() + " countries");
		
	}
  private static void listLanguages() {
    Locale list[] = DateFormat.getAvailableLocales();
    for (Locale aLocale : list) {
        System.out.println(aLocale.toString());
    }
    
    SortedSet<String> allLanguages = new TreeSet<String>();
    String[] languages = Locale.getISOLanguages();
    for (int i = 0; i < languages.length; i++){
        Locale loc = new Locale(languages[i]);
        //allLanguages.add(loc.getDisplayLanguage());
        System.out.println(loc.getDisplayLanguage());
    }    
    
    String[] locales = Locale.getISOCountries();
    List<String> listD = new ArrayList<>(500); // <-- List interface, and diamond
                                              //     operator.
    Locale outLocale = new Locale("EN", "us"); // <-- English US
    for (String countryCode : locales) {
        Locale obj = new Locale("en-us", countryCode);
        listD.add(obj.getDisplayCountry(outLocale));
    }
    Collections.sort(listD);
    String[] country = listD.toArray(new String[listD.size()]);
    System.out.println(Arrays.toString(country));
  }	
	
	public static void main(String... args){
		listLanguages();

	}

}
