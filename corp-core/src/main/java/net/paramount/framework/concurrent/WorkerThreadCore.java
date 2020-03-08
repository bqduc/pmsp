/**
 * 
 */
package net.paramount.framework.concurrent;

import net.paramount.framework.component.CompCore;
import net.paramount.framework.model.ExecutionContext;

/**
 * @author ducbq
 *
 */
public abstract class WorkerThreadCore extends CompCore implements Runnable {
	private static final long serialVersionUID = -2857158059074111900L;

	protected ExecutionContext executionContext;

	public WorkerThreadCore(ExecutionContext executionContext) {
		this.executionContext = ExecutionContext.builder().build();
		this.executionContext.putAll(executionContext);
	}

	@Override
	public void run() {
		perform();
	}

	protected abstract void perform();
}
