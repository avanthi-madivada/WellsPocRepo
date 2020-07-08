package com.ltts.wellspoc.ui.addnewwell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.util.PropertiesCache;
import com.ltts.wellspoc.ui.wizard.WellsWizard;

public class AddNewWellUI {

	public Button checkBoxButton;
	public Group addwellGroup;
	public Label wellNameLabel;
	public Text wellNameText;
	public Label eastingLabel;
	public Text eastingText;
	public Label northingLabel;
	public Text northingText;
	public Label azimuthLabel;
	public Text azimuthText;
	public Label fieldLabel;
	public Combo fieldCombo;
	public Label reservoirLabel;
	public Combo reservoirCombo;
	public Label wellTypelabel;
	public Button wellTypeHorizontalRadio;
	public Button wellTypeVerticalRadio;
	public Button wellTypeDeviatedRadio;
	public Button wellTypeSWellRadio;

	public String selectedRadio = "Horizontal";
	public String selectedReservoir;
	public String selectedField;

	// Min and Max value for azimuth.
	Double azimuthMinValue = 1.0;
	Double azimuthMaxValue = 360.0;

	String[] fieldsData = { "Salala", "Ghawar" };
	String[] reservoirData = { "Not Fm 2D Top" };

	PropertiesCache prop = PropertiesCache.getInstance();
	// read the title from property file
	String PAGE_TITLE = prop.getProperty("AddNewWellPage_title");

	WellsWizard wellswizard = new WellsWizard();
	Well well = new Well();

	boolean isValid;
	boolean isChecked = false;
	public Composite addWellPageContainer;
	AddNewWellUISupport addNewWellUISupport;

	public AddNewWellUI(Composite parent) {

		addWellPageContainer = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 15;
		layout.marginTop = 10;
		addWellPageContainer.setLayout(layout);
		addWellPageContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// check box - to be enabled to add new well details.
		checkBoxButton = new Button(addWellPageContainer, SWT.CHECK);
		checkBoxButton.setText("Click to add new well details.");

		addwellGroup = new Group(addWellPageContainer, SWT.NONE);
		GridLayout groupLayout = new GridLayout(2, true);
		// groupLayout.verticalSpacing = 10;
		addwellGroup.setLayout(groupLayout);
		GridData gridDataAddWellGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		addwellGroup.setLayoutData(gridDataAddWellGroup);
		addwellGroup.setText("Enter well details");

		// Well Name
		wellNameLabel = new Label(addwellGroup, SWT.NONE);
		wellNameLabel.setText("Well Name");
		wellNameLabel.setLayoutData(getLabelGridData());

		wellNameText = new Text(addwellGroup, SWT.BORDER);
		wellNameText.setLayoutData(getGridData());

		// Easting
		eastingLabel = new Label(addwellGroup, SWT.NONE);
		eastingLabel.setText("Easting");
		eastingLabel.setLayoutData(getLabelGridData());

		eastingText = new Text(addwellGroup, SWT.BORDER);
		eastingText.setLayoutData(getGridData());
		eastingText.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");

		// Northing
		northingLabel = new Label(addwellGroup, SWT.NONE);
		northingLabel.setText("Northing");
		northingLabel.setLayoutData(getLabelGridData());

		northingText = new Text(addwellGroup, SWT.BORDER);
		northingText.setLayoutData(getGridData());
		northingText
				.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");

		// Azimuth
		azimuthLabel = new Label(addwellGroup, SWT.NONE);
		azimuthLabel.setText("Azimuth");
		azimuthLabel.setLayoutData(getLabelGridData());

		azimuthText = new Text(addwellGroup, SWT.BORDER);
		azimuthText.setLayoutData(getGridData());
		azimuthText.setToolTipText(
				"The entered double value should be in between 1 and 360 and should have only one digit after decimal.");

		// Field
		fieldLabel = new Label(addwellGroup, SWT.NONE);
		fieldLabel.setText("Field");
		fieldLabel.setLayoutData(getLabelGridData());

		fieldCombo = new Combo(addwellGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		fieldCombo.setItems(fieldsData);
		fieldCombo.setLayoutData(getGridData());

		// Reservoir
		reservoirLabel = new Label(addwellGroup, SWT.NONE);
		reservoirLabel.setText("Reservoir");
		reservoirLabel.setLayoutData(getLabelGridData());

		reservoirCombo = new Combo(addwellGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		reservoirCombo.setItems(reservoirData);
		GridData gridDatareservoirCombo = new GridData();
		gridDatareservoirCombo.widthHint = 86;
		gridDatareservoirCombo.horizontalIndent = 7;
		reservoirCombo.setLayoutData(getGridData());

		// Well Type
		wellTypelabel = new Label(addwellGroup, SWT.NONE);
		wellTypelabel.setText("Well Type ");
		wellTypelabel.setLayoutData(getLabelGridData());

		Composite radioComposite = new Composite(addwellGroup, SWT.NONE);
		radioComposite.setLayout(new RowLayout());

		wellTypeHorizontalRadio = new Button(radioComposite, SWT.RADIO);
		wellTypeHorizontalRadio.setText("Horizontal");
		wellTypeHorizontalRadio.setSelection(true);

		wellTypeVerticalRadio = new Button(radioComposite, SWT.RADIO);
		wellTypeVerticalRadio.setText("Vertical");

		wellTypeDeviatedRadio = new Button(radioComposite, SWT.RADIO);
		wellTypeDeviatedRadio.setText("Deviated");

		wellTypeSWellRadio = new Button(radioComposite, SWT.RADIO);
		wellTypeSWellRadio.setText("S-Well");

		isCheckBoxSelected(false);
	}

	private GridData getGridData() {
		GridData gridData = new GridData();
		gridData.widthHint = 100;
		gridData.horizontalIndent = 5;
		return gridData;
	}

	private GridData getLabelGridData() {
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		gridData.widthHint = 100;
		return gridData;
	}

	/**
	 * Enable/Disable the UI according to the check box state.
	 * 
	 * @param isChecked
	 */
	public void isCheckBoxSelected(boolean isChecked) {
		wellNameText.setEnabled(isChecked);
		wellNameLabel.setEnabled(isChecked);
		eastingLabel.setEnabled(isChecked);
		eastingText.setEnabled(isChecked);
		northingLabel.setEnabled(isChecked);
		northingText.setEnabled(isChecked);
		azimuthText.setEnabled(isChecked);
		azimuthLabel.setEnabled(isChecked);
		fieldLabel.setEnabled(isChecked);
		fieldCombo.setEnabled(isChecked);
		reservoirLabel.setEnabled(isChecked);
		reservoirCombo.setEnabled(isChecked);
		wellTypelabel.setEnabled(isChecked);
		wellTypeSWellRadio.setEnabled(isChecked);
		wellTypeVerticalRadio.setEnabled(isChecked);
		wellTypeHorizontalRadio.setEnabled(isChecked);
		wellTypeDeviatedRadio.setEnabled(isChecked);
	}

	public Text getWellNameText() {
		return wellNameText;
	}

	public Text getEastingText() {
		return eastingText;
	}

	public Text getNorthingText() {
		return northingText;
	}

	public Text getAzimuthText() {
		return azimuthText;
	}

	public String getSelectedRadio() {
		return selectedRadio;
	}

	public String getSelectedReservoir() {
		return selectedReservoir;
	}

	public String getSelectedField() {
		return selectedField;
	}

	public Composite getAddWellPageContainer() {
		return addWellPageContainer;
	}

}
