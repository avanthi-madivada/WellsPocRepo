package com.ltts.wellspoc.models;

/**
 * class representing new well details to be added.
 *
 */
public class WellDetailsModel {

	private String wellPlanName;
	private double easting;
	private double northing;
	private double azimuth;
	private String field;
	private String reservoir;
	private String type;

	/**
	 * @param wellPlanName
	 * @param easting
	 * @param northing
	 * @param azimuth
	 * @param field
	 * @param reservoir
	 * @param type
	 */
	public WellDetailsModel(String wellPlanName, Double easting, Double northing, Double azimuth, String field,
			String reservoir, String type) {
		this.wellPlanName = wellPlanName;
		this.easting = easting;
		this.northing = northing;
		this.azimuth = azimuth;
		this.field = field;
		this.reservoir = reservoir;
		this.type = type;
	}

	/**
	 * constructor for new well details.
	 */
	public WellDetailsModel() {
	}

	public String getWellPlanName() {
		return wellPlanName;
	}

	public void setWellPlanName(String wellPlanName) {
		this.wellPlanName = wellPlanName;
	}

	public double getEasting() {
		return easting;
	}

	public void setEasting(double easting) {
		this.easting = easting;
	}

	public double getNorthing() {
		return northing;
	}

	public void setNorthing(double northing) {
		this.northing = northing;
	}

	public double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(double azimuth) {
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

}
