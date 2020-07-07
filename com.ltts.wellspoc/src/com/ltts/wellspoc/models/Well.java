package com.ltts.wellspoc.models;

/**
 * @author Deepika KS Model class which represents Well details
 */
public class Well {

	private String wellPlanName;
	private Double easting;
	private Double northing;
	private Double azimuth;
	private String field;
	private String reservoir;
	private String type;
	private boolean isChecked;
	
	public enum WellType {
		HORIZONTAL, VERTICAL, DEVIATED, SWELL
	}

	/**
	 * @return WellName
	 */
	public String getWellPlanName() {
		return wellPlanName;
	}

	/**
	 * @param wellPlanName
	 */
	public void setWellPlanName(String wellPlanName) {
		this.wellPlanName = wellPlanName;
	}

	/**
	 * @return easting
	 */
	public Double getEasting() {
		return easting;
	}

	/**
	 * @param easting
	 */
	public void setEasting(Double easting) {
		this.easting = easting;
	}

	/**
	 * @return northing
	 */
	public Double getNorthing() {
		return northing;
	}

	/**
	 * @param northing
	 */
	public void setNorthing(Double northing) {
		this.northing = northing;
	}

	/**
	 * @return azimuth
	 */
	public Double getAzimuth() {
		return azimuth;
	}

	/**
	 * @param azimuth
	 */
	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}

	/**
	 * @return field value
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return reservoir
	 */
	public String getReservoir() {
		return reservoir;
	}

	/**
	 * @param reservoir
	 */
	public void setReservoir(String reservoir) {
		this.reservoir = reservoir;
	}

	/**
	 * @return type value
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @param wellPlanName
	 * @param easting
	 * @param northing
	 * @param azimuth
	 * @param field
	 * @param reservoir
	 * @param type
	 */
	public Well(String wellPlanName, Double easting, Double northing, Double azimuth, String field, String reservoir,
			String type) {
		super();
		this.wellPlanName = wellPlanName;
		this.easting = easting;
		this.northing = northing;
		this.azimuth = azimuth;
		this.field = field;
		this.reservoir = reservoir;
		this.type = type;
	}

	public Well() {
	}

	public Well(String wellPlanName, Double easting, Double northing, Double azimuth, String field, String reservoir,
			String type, boolean isChecked) {
		super();
		this.wellPlanName = wellPlanName;
		this.easting = easting;
		this.northing = northing;
		this.azimuth = azimuth;
		this.field = field;
		this.reservoir = reservoir;
		this.type = type;
		this.isChecked = isChecked;
	}

}
