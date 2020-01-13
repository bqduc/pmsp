/**
 * 
 */
package net.paramount.dbx.specification;

import org.springframework.data.jpa.domain.Specification;

import lombok.Builder;
import net.paramount.auth.entity.Authority;
import net.paramount.framework.model.SearchParameter;
import net.paramount.framework.model.SearchSpec;
import net.paramount.framework.specification.CoreSpecifications;

/**
 * @author bqduc
 *
 */
@Builder
public class DashletSpecification extends CoreSpecifications<Authority, SearchSpec>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7689251828322811904L;

	public static Specification<Authority> buildSpecification(final SearchParameter searchParameter) {
		return DashletSpecification
				.builder()
				.build()
				.buildSpecifications(searchParameter);
	}
}
