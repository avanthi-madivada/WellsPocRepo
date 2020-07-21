package com.ltts.wellspoc.ui.addnewwell;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.util.MessagesUtil;

/**
 * synchronize UI and model instance.
 * 
 * @author Ranjith D
 *
 */
public class AddNewWellUISupport {

	AddNewWellUI addNewWellUI;
	boolean isChecked = false;

	public AddNewWellUISupport(AddNewWellUI addNewWellUI, Well wellModel) {
		this.addNewWellUI = addNewWellUI;
		addModifyListener();
	}

	/**
	 * method to create listeners.
	 */
	private void addModifyListener() {
		addNewWellUI.getCheckBoxButton().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {

				if (addNewWellUI.getCheckBoxButton().getSelection() == true) {
					isChecked = true;
					addNewWellUI.isCheckBoxSelected(isChecked);
				} else {

					isChecked = false;
					addNewWellUI.isCheckBoxSelected(isChecked);
				}
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}

		});

		addNewWellUI.getWellNameText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getEastingText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.getEastingText(), Double.MIN_VALUE, Double.MAX_VALUE);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getAzimuthText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.getAzimuthText(), addNewWellUI.azimuthMinValue,
						addNewWellUI.azimuthMaxValue);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getNorthingText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.getNorthingText(), Double.MIN_VALUE, Double.MAX_VALUE);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getReservoirCombo().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();

			}
		});

		addNewWellUI.getFieldCombo().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getWellTypeHorizontalRadio().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getWellTypeVerticalRadio().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getWellTypeDeviatedRadio().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getWellTypeSWellRadio().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.getRestoreButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				addNewWellUI.getWellNameText().setText("");
				addNewWellUI.getEastingText().setText("");
				addNewWellUI.getNorthingText().setText("");
				addNewWellUI.getAzimuthText().setText("");
				addNewWellUI.getWellTypeHorizontalRadio().setSelection(true);
				addNewWellUI.getWellTypeDeviatedRadio().setSelection(false);
				addNewWellUI.getWellTypeVerticalRadio().setSelection(false);
				addNewWellUI.getWellTypeSWellRadio().setSelection(false);
				addNewWellUI.getReservoirCombo().deselectAll();
				addNewWellUI.getFieldCombo().deselectAll();

			}
		});
	}

}
