/**
 * 
 */
package net.paramount.css.specification;

import org.springframework.data.jpa.domain.Specification;

import lombok.Builder;
import net.paramount.entity.general.CatalogueSubtype;
import net.paramount.framework.model.SearchParameter;
import net.paramount.framework.model.SearchSpec;
import net.paramount.framework.specification.CoreSpecifications;

/**
 * @author bqduc
 *
 */
@Builder
public class CatalogueSubtypeSpecification extends CoreSpecifications<CatalogueSubtype, SearchSpec>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2021774340255996625L;

	public static Specification<CatalogueSubtype> buildSpecification(final SearchParameter searchParameter) {
		return CatalogueSubtypeSpecification
				.builder()
				.build()
				.buildSpecifications(searchParameter);
	}
}
