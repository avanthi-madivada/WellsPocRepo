package com.ltts.wellspoc.models;

import java.util.ArrayList;
import java.util.List;

public class WellDataProvider {

	public final static WellDataProvider wellDataProvider = new WellDataProvider();

	private List<Well> wellData = new ArrayList<Well>();

	private WellDataProvider() {
		wellData.add(
				new Well("Well 1", "420107.6", "7244305.1", "240", "Ghawar", "Not Fm. 2 HD Top", "Horizontal", false));
		wellData.add(
				new Well("Well 2", "450107.6", "6144305.1", "180", "Salala", "Not Fm. 2 HD Top", "Vertical", false));
		wellData.add(
				new Well("Well 3", "520107.6", "2244305.1", "270", "Ghawar", "Not Fm. 2 HD Top", "Deviated", false));
		wellData.add(
				new Well("Well 4", "470107.6", "5244305.1", "190", "Ghawar", "Not Fm. 2 HD Top", "Horizontal", false));
		wellData.add(
				new Well("Well 5", "400107.6", "3144305.1", "260", "Ghawar", "Not Fm. 2 HD Top", "Vertical", false));
		wellData.add(
				new Well("Well 6", "480107.6", "8844305.1", "140", "Ghawar", "Not Fm. 2 HD Top", "Deviated", false));
		wellData.add(
				new Well("Well 7", "560107.6", "4444305.1", "320", "Ghawar", "Not Fm. 2 HD Top", "Horizontal", false));
		wellData.add(
				new Well("Well 8", "530107.6", "32244305.1", "290", "Ghawar", "Not Fm. 2 HD Top", "Vertical", false));
		wellData.add(
				new Well("Well 9", "550107.6", "6244305.1", "170", "Ghawar", "Not Fm. 2 HD Top", "Deviated", false));
		wellData.add(
				new Well("Well 10", "580107.6", "6744305.1", "160", "Ghawar", "Not Fm. 2 HD Top", "Horizontal", false));

	}

	public List<Well> getWell() {
		return wellData;
	}

}