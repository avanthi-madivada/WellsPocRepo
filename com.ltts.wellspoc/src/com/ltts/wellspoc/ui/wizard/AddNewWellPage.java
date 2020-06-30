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

import com.ltts.wellspoc.ui.util.DecimalNumberManager;
import com.ltts.wellspoc.ui.util.PropertiesCache;

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
	protected static Label northingLabel;
	protected static Label azimuthLabel;
	protected static Label fieldLabel;
	protected static Combo fieldCombo;
	protected static Label reservoirLabel;
	protected static Combo reservoirCombo;
	protected static Label wellTypelabel;
	protected static Button wellTypeHorizontalRadio;
	protected static Button wellTypeVerticalRadio;
	protected static Button wellTypeDeviatedRadio;
	protected static Button wellTypeSWellRadio;

	protected static String selectedRadio = "Horizontal";
	protected static String selectedReservoir;
	protected static String selectedField;
	
	String[] fieldsData = { "Salala", "Ghawar" };
	String[] reservoirData = { "Not Fm 2D Top" };

	static DecimalNumberManager eastingText;
	static DecimalNumberManager northingText;
	static DecimalNumberManager azimuthText;

	DecimalNumberManager decimalNumberManager;
	
	PropertiesCache prop = PropertiesCache.getInstance();	 
	//read the title from property file
    String PAGE_TITLE = prop.getProperty("AddNewWellPage_title");
	
	WellsWizard wellswizard = new WellsWizard();
	
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

		checkBoxButton = new Button(addWellPageContainer, SWT.CHECK);
		checkBoxButton.setText("Click to add new well.");

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

			}
		});

		addwellGroup = new Group(addWellPageContainer, SWT.NONE);
		GridLayout groupLayout = new GridLayout(2, false);
//		addwellGroup.setFont(new Font(null, "Times New Roman", 11, SWT.BOLD));
		groupLayout.verticalSpacing = 10;
		addwellGroup.setLayout(groupLayout);
		GridData gridDataAddWellGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		addwellGroup.setLayoutData(gridDataAddWellGroup);
		addwellGroup.setText("Enter well details");

		// Well Name
		wellNameLabel = new Label(addwellGroup, SWT.NONE);
		wellNameLabel.setText("Well Name");

		
		wellNameText = new Text(addwellGroup, SWT.BORDER);
		GridData gridDatawellNameText = new GridData();
		gridDatawellNameText.widthHint = 100;
		gridDatawellNameText.horizontalIndent = 7;
		wellNameText.setLayoutData(gridDatawellNameText);

		
		wellNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();
			}
		});

		// Easting
		eastingLabel = new Label(addwellGroup, SWT.NONE);
		eastingLabel.setText("Easting");

		eastingText = new DecimalNumberManager("Easting", addwellGroup);

		
		 eastingText.addModifyListener(new WellDetailsModifyListener(eastingText));

		// Northing
		northingLabel = new Label(addwellGroup, SWT.NONE);
		northingLabel.setText("Northing");

		
		northingText = new DecimalNumberManager("Northing", addwellGroup);

		
		northingText.addModifyListener(new WellDetailsModifyListener(northingText));

		// Azimuth
		azimuthLabel = new Label(addwellGroup, SWT.NONE);
		azimuthLabel.setText("Azimuth");
		

		
		azimuthText = new DecimalNumberManager("Azimuth", addwellGroup, 0.0,360.0);
		azimuthText.addModifyListener(new WellDetailsModifyListener(azimuthText));

		// Field
		fieldLabel = new Label(addwellGroup, SWT.NONE);
		fieldLabel.setText("Field");

		
		fieldCombo = new Combo(addwellGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
		fieldCombo.setItems(fieldsData);
		GridData gridDatafieldCombo = new GridData();
		gridDatafieldCombo.widthHint = 86;
		gridDatafieldCombo.horizontalIndent = 7;
		fieldCombo.setLayoutData(gridDatafieldCombo);

		fieldCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int index = fieldCombo.getSelectionIndex();
				selectedField = fieldCombo.getItem(index);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();

			}
		});

		// Reservoir
		reservoirLabel = new Label(addwellGroup, SWT.NONE);
		reservoirLabel.setText("Reservoir");

		
		reservoirCombo = new Combo(addwellGroup, SWT.DROP_DOWN | SWT.READ_ONLY);

		reservoirCombo.setItems(reservoirData);
		GridData gridDatareservoirCombo = new GridData();
		gridDatareservoirCombo.widthHint = 86;
		gridDatareservoirCombo.horizontalIndent = 7;
		reservoirCombo.setLayoutData(gridDatareservoirCombo);

		
		reservoirCombo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				int index = reservoirCombo.getSelectionIndex();
				selectedReservoir = reservoirCombo.getItem(index);
				wellswizard.canFinish();
				getWizard().getContainer().updateButtons();

			}
		});

		// Well Type
		wellTypelabel = new Label(addwellGroup, SWT.NONE);
		wellTypelabel.setText("Well Type ");

		
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

	/**
	 * Inner class for AddNewWellPage Text Listeners
	 *
	 */
	private class WellDetailsModifyListener implements ModifyListener {

		DecimalNumberManager decimalNumberManager;

		public WellDetailsModifyListener(DecimalNumberManager decimalNumberManager) {
			this.decimalNumberManager = decimalNumberManager;
		}

		@Override
		public void modifyText(ModifyEvent e) {

			decimalNumberManager.restrictEnteredChars();
			wellswizard.canFinish();
			getWizard().getContainer().updateButtons();
		}
	}

	
}
