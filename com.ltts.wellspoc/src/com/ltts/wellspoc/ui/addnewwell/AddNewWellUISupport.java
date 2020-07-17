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
		addNewWellUI.checkBoxButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {

				if (addNewWellUI.checkBoxButton.getSelection() == true) {
					isChecked = true;
					addNewWellUI.isCheckBoxSelected(isChecked);
				} else {

					isChecked = false;
					addNewWellUI.isCheckBoxSelected(isChecked);
				}
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}

		});

		addNewWellUI.wellNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.eastingText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.eastingText, Double.MIN_VALUE, Double.MAX_VALUE);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.azimuthText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.azimuthText, addNewWellUI.azimuthMinValue,
						addNewWellUI.azimuthMaxValue);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.northingText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				MessagesUtil.restrictEnteredChars(addNewWellUI.northingText, Double.MIN_VALUE, Double.MAX_VALUE);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.reservoirCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();

			}
		});

		addNewWellUI.fieldCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeHorizontalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeVerticalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeDeviatedRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeSWellRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.restoreButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				addNewWellUI.wellNameText.setText("");
				addNewWellUI.eastingText.setText("");
				addNewWellUI.northingText.setText("");
				addNewWellUI.azimuthText.setText("");
				addNewWellUI.wellTypeHorizontalRadio.setSelection(true);
				addNewWellUI.wellTypeDeviatedRadio.setSelection(false);
				addNewWellUI.wellTypeVerticalRadio.setSelection(false);
				addNewWellUI.wellTypeSWellRadio.setSelection(false);
				addNewWellUI.reservoirCombo.deselectAll();
				addNewWellUI.fieldCombo.deselectAll();

			}
		});
	}

}
