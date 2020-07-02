package com.ltts.wellspoc.models;

/**
 * class representing well selection.
 *
 */
public class WellSelectionModel {

	private String wellPlanName;
	private Double easting;
	private Double northing;
	private Double azimuth;
	private String field;
	private String reservoir;
	private String type;
	private boolean isChecked;

	/**
	 * constructor for well selection.
	 */
	public WellSelectionModel() {
	}

	/**
	 * @param wellPlanName
	 * @param easting
	 * @param northing
	 * @param azimuth
	 * @param field
	 * @param reservoir
	 * @param type
	 * @param isChecked
	 */
	public WellSelectionModel(String wellPlanName, Double easting, Double northing, Double azimuth, String field,
			String reservoir, String type, boolean isChecked) {
		this.wellPlanName = wellPlanName;
		this.easting = easting;
		this.northing = northing;
		this.azimuth = azimuth;
		this.field = field;
		this.reservoir = reservoir;
		this.type = type;
		this.isChecked = isChecked;
	}

	public String getWellPlanName() {
		return wellPlanName;
	}

	public void setWellPlanName(String wellPlanName) {
		this.wellPlanName = wellPlanName;
	}

	public Double getEasting() {
		return easting;
	}

	public void setEasting(Double easting) {
		this.easting = easting;
	}

	public Double getNorthing() {
		return northing;
	}

	public void setNorthing(Double northing) {
		this.northing = northing;
	}

	public Double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getReservoir() {
		return reservoir;
	}

	public void setReservoir(String reservoir) {
		this.reservoir = reservoir;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
