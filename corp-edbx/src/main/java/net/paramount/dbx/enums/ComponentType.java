/**
 * 
 */
package net.paramount.dbx.enums;

/**
 * @author ducbq
 *
 *
 * An Enumeration for indicating which type of component a Bulletin is associated with
 */
public enum ComponentType {
  /**
   * Bulletin is associated with a Processor
   */
  PROCESSOR,

  /**
   * Bulletin is associated with a Remote Process Group
   */
  REMOTE_PROCESS_GROUP,

  /**
   * Bulletin is associated with an Input Port
   */
  INPUT_PORT,

  /**
   * Bulletin is associated with an Output Port
   */
  OUTPUT_PORT,

  /**
   * Bulletin is associated with a Reporting Task
   */
  REPORTING_TASK,

  /**
   * Bulletin is associated with a Controller Service
   */
  CONTROLLER_SERVICE,

  /**
   * Bulletin is a system-level bulletin, associated with the Flow Controller
   */
  FLOW_CONTROLLER;
}
