package net.paramount.mvp.util;

import java.io.Serializable;
import java.util.List;

import net.paramount.domain.dummy.Car;

/**
 * Created by rmpestano on 07/02/17.
 */
public interface UtilityService extends Serializable {
	List<Car> getCars();
}
