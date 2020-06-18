package com.ltts.wellspoc.models;

public class Well {

	private String wellPlanName;
	private String easting;
	private String northing;
	private String azimuth;
	private String field;
	private String reservoir;
	private String type;
	private boolean isChecked;

	public String getWellPlanName() {
		return wellPlanName;
	}

	public void setWellPlanName(String wellPlanName) {
		this.wellPlanName = wellPlanName;
	}

	public String getEasting() {
		return easting;
	}

	public void setEasting(String easting) {
		this.easting = easting;
	}

	public String getNorthing() {
		return northing;
	}

	public void setNorthing(String northing) {
		this.northing = northing;
	}

	public String getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(String azimuth) {
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

	public Well(String wellPlanName, String easting, String northing, String azimuth, String field, String reservoir,
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

	public Well(String wellPlanName, String easting, String northing, String azimuth, String field, String reservoir,
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