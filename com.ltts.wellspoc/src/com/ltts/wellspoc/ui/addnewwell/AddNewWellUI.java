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

/**
 * class to create the UI widgets for AdNewWellPage.
 * 
 * @author Ranjith D
 *
 */
public class AddNewWellUI {

	private Button checkBoxButton;
	private Group addwellGroup;
	private Label wellNameLabel;
	private Text wellNameText;
	private Label eastingLabel;
	private Text eastingText;
	private Label northingLabel;
	private Text northingText;
	private Label azimuthLabel;
	private Text azimuthText;
	private Label fieldLabel;
	private Combo fieldCombo;
	private Label reservoirLabel;
	private Combo reservoirCombo;
	public Group getAddwellGroup() {
		return addwellGroup;
	}

	public void setAddwellGroup(Group addwellGroup) {
		this.addwellGroup = addwellGroup;
	}

	public Label getWellNameLabel() {
		return wellNameLabel;
	}

	public void setWellNameLabel(Label wellNameLabel) {
		this.wellNameLabel = wellNameLabel;
	}

	public Label getEastingLabel() {
		return eastingLabel;
	}

	public void setEastingLabel(Label eastingLabel) {
		this.eastingLabel = eastingLabel;
	}

	public Label getNorthingLabel() {
		return northingLabel;
	}

	public void setNorthingLabel(Label northingLabel) {
		this.northingLabel = northingLabel;
	}

	public Label getAzimuthLabel() {
		return azimuthLabel;
	}

	public void setAzimuthLabel(Label azimuthLabel) {
		this.azimuthLabel = azimuthLabel;
	}

	public Label getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(Label fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public Combo getFieldCombo() {
		return fieldCombo;
	}

	public void setFieldCombo(Combo fieldCombo) {
		this.fieldCombo = fieldCombo;
	}

	public Label getReservoirLabel() {
		return reservoirLabel;
	}

	public void setReservoirLabel(Label reservoirLabel) {
		this.reservoirLabel = reservoirLabel;
	}

	public Combo getReservoirCombo() {
		return reservoirCombo;
	}

	public void setReservoirCombo(Combo reservoirCombo) {
		this.reservoirCombo = reservoirCombo;
	}

	public Label getWellTypelabel() {
		return wellTypelabel;
	}

	public void setWellTypelabel(Label wellTypelabel) {
		this.wellTypelabel = wellTypelabel;
	}

	public Button getWellTypeHorizontalRadio() {
		return wellTypeHorizontalRadio;
	}

	public void setWellTypeHorizontalRadio(Button wellTypeHorizontalRadio) {
		this.wellTypeHorizontalRadio = wellTypeHorizontalRadio;
	}

	public Button getWellTypeVerticalRadio() {
		return wellTypeVerticalRadio;
	}

	public void setWellTypeVerticalRadio(Button wellTypeVerticalRadio) {
		this.wellTypeVerticalRadio = wellTypeVerticalRadio;
	}

	public Button getWellTypeDeviatedRadio() {
		return wellTypeDeviatedRadio;
	}

	public void setWellTypeDeviatedRadio(Button wellTypeDeviatedRadio) {
		this.wellTypeDeviatedRadio = wellTypeDeviatedRadio;
	}

	public Button getWellTypeSWellRadio() {
		return wellTypeSWellRadio;
	}

	public void setWellTypeSWellRadio(Button wellTypeSWellRadio) {
		this.wellTypeSWellRadio = wellTypeSWellRadio;
	}

	public Button getRestoreButton() {
		return restoreButton;
	}

	public void setRestoreButton(Button restoreButton) {
		this.restoreButton = restoreButton;
	}

	public void setCheckBoxButton(Button checkBoxButton) {
		this.checkBoxButton = checkBoxButton;
	}

	public void setWellNameText(Text wellNameText) {
		this.wellNameText = wellNameText;
	}

	public void setEastingText(Text eastingText) {
		this.eastingText = eastingText;
	}

	public void setNorthingText(Text northingText) {
		this.northingText = northingText;
	}

	public void setAzimuthText(Text azimuthText) {
		this.azimuthText = azimuthText;
	}

	public void setAddWellPageContainer(Composite addWellPageContainer) {
		this.addWellPageContainer = addWellPageContainer;
	}

	private Label wellTypelabel;
	private Button wellTypeHorizontalRadio;
	private Button wellTypeVerticalRadio;
	private Button wellTypeDeviatedRadio;
	private Button wellTypeSWellRadio;
	private Button restoreButton;

	// Min and Max value for azimuth.
	Double azimuthMinValue = 0.0;
	Double azimuthMaxValue = 360.0;

	String[] fieldsData = { "Salala", "Ghawar" };
	String[] reservoirData = { "Not Fm 2D Top" };

	boolean isChecked = false;
	private Composite addWellPageContainer;

	/**
	 * method to create the UI.
	 * 
	 * @param parent
	 */
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

		// Restore defaults
		restoreButton = new Button(addWellPageContainer, GridData.VERTICAL_ALIGN_END);
		restoreButton.setText("Restore defaults");
		GridData restoreButtonGridData = new GridData(SWT.RIGHT, SWT.BOTTOM, true, true);
		restoreButton.setLayoutData(restoreButtonGridData);

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
		restoreButton.setEnabled(isChecked);
	}

	/**
	 * @return wellNameText
	 */
	public Text getWellNameText() {
		return wellNameText;
	}

	/**
	 * @return eastingText
	 */
	public Text getEastingText() {
		return eastingText;
	}

	/**
	 * @return northingText
	 */
	public Text getNorthingText() {
		return northingText;
	}

	/**
	 * @return azimuthText
	 */
	public Text getAzimuthText() {
		return azimuthText;
	}

	/**
	 * @return addWellPageContainer
	 */
	public Composite getAddWellPageContainer() {
		return addWellPageContainer;
	}

	/**
	 * @return checkBoxButton
	 */
	public Button getCheckBoxButton() {
		return checkBoxButton;
	}
}
