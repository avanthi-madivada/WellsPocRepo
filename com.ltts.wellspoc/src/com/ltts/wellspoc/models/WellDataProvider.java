package com.ltts.wellspoc.models;

import java.util.ArrayList;
import java.util.List;

public class WellDataProvider {

	public final static WellDataProvider wellDataProvider = new WellDataProvider();

	private List<Well> wellData = new ArrayList<Well>();

	private WellDataProvider() {
		wellData.add(new Well("Well Name 1", "Id 1", false));
		wellData.add(new Well("Well Name 2", "Id 2", false));
		wellData.add(new Well("Well Name 3", "Id 3", false));
		wellData.add(new Well("Well Name 4", "Id 4", false));
		wellData.add(new Well("Well Name 5", "Id 5", false));
		wellData.add(new Well("Well Name 6", "Id 6", false));
		wellData.add(new Well("Well Name 7", "Id 7", false));
		wellData.add(new Well("Well Name 8", "Id 8", false));
		wellData.add(new Well("Well Name 9", "Id 9", false));
		wellData.add(new Well("Well Name 10", "Id 10", false));

	}

	public List<Well> getWell() {
		return wellData;
	}

}
