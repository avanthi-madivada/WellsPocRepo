package com.ltts.wellspoc.ui.wizard;

import org.eclipse.jface.wizard.WizardPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ltts.wellspoc.ui.util.PropertiesCache;
import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * The class is used to add new well.
 * 
 * @author Ranjith D
 *
 */
public class AddNewWellPage extends WizardPage {

	protected static Button checkBoxButton;
	protected static Group addwellGroup;
	protected static Label wellNameLabel;
	protected static Text wellNameText;
	protected static Label eastingLabel;
	protected static Text eastingText;
	protected static Label northingLabel;
	protected static Text northingText;
	protected static Label azimuthLabel;
	protected static Text azimuthText;
	protected static Label fieldLabel;
	protected static Combo fieldCombo;
	protected static Label reservoirLabel;
	protected static Combo reservoirCombo;
	protected static Label wellTypelabel;
	protected static Button wellTypeHorizontalRadio;
	protected static Button wellTypeVerticalRadio;
	protected static Button wellTypeDeviatedRadio;
	protected static Button wellTypeSWellRadio;

	protected static String selectedRadio;
	protected static String selectedReservoir;
	protected static String selectedField;
	
	// Min and Max value for azimuth.
	Double azimuthMinValue = 1.0;
	Double azimuthMaxValue = 360.0;
	
	String[] fieldsData = { "Salala", "Ghawar" };
	String[] reservoirData = { "Not Fm 2D Top" };
	
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
    String PAGE_TITLE = prop.getProperty("AddNewWellPage_title");
	
	WellsWizard wellswizard = new WellsWizard();
	Well well = new Well();
	boolean isChecked = false;

	/**
	 * Constructor for AddNewWellPage
	 * 
	 * @param pageName
	 */
	protected AddNewWellPage(String pageName) {
		super(pageName);

	}

	/**
	 * This method is used to create UI for Add New Well Page.
	 */
	@Override
	public void createControl(Composite parent) {
		
		setTitle(PAGE_TITLE);

		Composite addWellPageContainer = new Composite(parent, SWT.BORDER);
		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 15;
		layout.marginTop = 10;
		addWellPageContainer.setLayout(layout);
		addWellPageContainer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		//check box - to be enabled to add new well details.
		checkBoxButton = new Button(addWellPageContainer, SWT.CHECK);
		checkBoxButton.setText("Click to add new well details.");

		checkBoxButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {

				if (checkBoxButton.getSelection() == true) {
					
					isChecked = true;
					isCheckBoxSelected(isChecked);
				} else {
					
					isChecked = false;
					isCheckBoxSelected(isChecked);
				}	
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
			
		});

		addwellGroup = new Group(addWellPageContainer, SWT.NONE);
		GridLayout groupLayout = new GridLayout(2, true);
		//groupLayout.verticalSpacing = 10;
		addwellGroup.setLayout(groupLayout);
		GridData gridDataAddWellGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		addwellGroup.setLayoutData(gridDataAddWellGroup);
		addwellGroup.setText("Enter well details");

		// Well Name
		wellNameLabel = new Label(addwellGroup, SWT.NONE);
		wellNameLabel.setText("Well Name");
		wellNameLabel.setLayoutData(getLabelGridData());

		wellNameText = new Text(addwellGroup, SWT.BORDER);
		//GridData gridDatawellNameText = new GridData();
		//gridDatawellNameText.widthHint = 100;
		//gridDatawellNameText.horizontalIndent = 7;
		wellNameText.setLayoutData(getGridData());

		// Easting
		eastingLabel = new Label(addwellGroup, SWT.NONE);
		eastingLabel.setText("Easting");
		eastingLabel.setLayoutData(getLabelGridData());
		
		eastingText = new Text(addwellGroup, SWT.BORDER);
		//GridData gridDataeastingText = new GridData();
		//gridDataeastingText.widthHint = 100;
		//gridDataeastingText.horizontalIndent = 7;
		eastingText.setLayoutData(getGridData());
		eastingText.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");
		
		// Northing
		northingLabel = new Label(addwellGroup, SWT.NONE);
		northingLabel.setText("Northing");
		northingLabel.setLayoutData(getLabelGridData());
		
		northingText = new Text(addwellGroup, SWT.BORDER);
		//GridData gridDatanorthingText = new GridData();
		//gridDatanorthingText.widthHint = 100;
		//gridDatanorthingText.horizontalIndent = 7;
		northingText.setLayoutData(getGridData());
		northingText.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");
			
		// Azimuth
		azimuthLabel = new Label(addwellGroup, SWT.NONE);
		azimuthLabel.setText("Azimuth");
		azimuthLabel.setLayoutData(getLabelGridData());
	
		azimuthText = new Text(addwellGroup, SWT.BORDER);
		//GridData gridDataazimuthText = new GridData();
		//gridDataazimuthText.widthHint = 100;
		//gridDataazimuthText.horizontalIndent = 7;
		azimuthText.setLayoutData(getGridData());
		azimuthText.setToolTipText("The entered double value should be in between 1 and 360 and should have only one digit after decimal.");
		
		// Field
		fieldLabel = new Label(addwellGroup, SWT.NONE);
		fieldLabel.setText("Field");
		fieldLabel.setLayoutData(getLabelGridData());
	
		fieldCombo = new Combo(addwellGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		fieldCombo.setItems(fieldsData);
//		GridData gridDatafieldCombo = new GridData();
//		gridDatafieldCombo.widthHint = 86;
//		gridDatafieldCombo.horizontalIndent = 7;
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
		
		wellNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});
		
		eastingText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(eastingText,Double.MIN_VALUE,Double.MAX_VALUE);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});
		
		azimuthText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(azimuthText,azimuthMinValue,azimuthMaxValue);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});
		
		northingText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(northingText,Double.MIN_VALUE,Double.MAX_VALUE);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});
		
		reservoirCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = reservoirCombo.getSelectionIndex();
				selectedReservoir = reservoirCombo.getItem(index);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();

			}
		});
		
		fieldCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = fieldCombo.getSelectionIndex();
				selectedField = fieldCombo.getItem(index);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});
		
		wellTypeHorizontalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wellTypeHorizontalRadio = (Button) e.getSource();
				selectedRadio = wellTypeHorizontalRadio.getText();
			}
		});

		wellTypeVerticalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wellTypeVerticalRadio = (Button) e.getSource();
				selectedRadio = wellTypeVerticalRadio.getText();
			}
		});

		wellTypeDeviatedRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wellTypeDeviatedRadio = (Button) e.getSource();
				selectedRadio = wellTypeDeviatedRadio.getText();
			}
		});

		wellTypeSWellRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wellTypeSWellRadio = (Button) e.getSource();
				selectedRadio = wellTypeSWellRadio.getText();
			}
		});
		isCheckBoxSelected(isChecked);
		setControl(addwellGroup);
	}

	/**
	 * Enable/Disable the UI according to the check box state.
	 * @param isChecked
	 */
	private void isCheckBoxSelected(boolean isChecked) {	
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
}
