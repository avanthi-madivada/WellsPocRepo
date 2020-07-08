package com.ltts.wellspoc.ui.addnewwell;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.ltts.wellspoc.models.Well;
import com.ltts.wellspoc.ui.util.MessagesUtil;

public class AddNewWellUISupport {

	AddNewWellUI addNewWellUI;
	Well wellModel;

	boolean isChecked = false;
	public AddNewWellUISupport(AddNewWellUI addNewWellUI, Well wellModel) {
		this.addNewWellUI = addNewWellUI;
		addModifyListener();
	}

	private void addModifyListener() {
		addNewWellUI.checkBoxButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent event) {

				if (addNewWellUI.checkBoxButton.getSelection() == true) {
					//need to be changed.
//					AddNewWellModelMgr.INSTANCE.isChecked = true;
//					addNewWellUI.isCheckBoxSelected(AddNewWellModelMgr.INSTANCE.isChecked);
					isChecked = true;
					addNewWellUI.isCheckBoxSelected(isChecked);
				} else {
//					AddNewWellModelMgr.INSTANCE.isChecked = false;
//					addNewWellUI.isCheckBoxSelected(AddNewWellModelMgr.INSTANCE.isChecked);
					isChecked = false;
					addNewWellUI.isCheckBoxSelected(isChecked);
				}
//				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
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
				int index = addNewWellUI.reservoirCombo.getSelectionIndex();
				addNewWellUI.selectedReservoir = addNewWellUI.reservoirCombo.getItem(index);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();

			}
		});

		addNewWellUI.fieldCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = addNewWellUI.fieldCombo.getSelectionIndex();
				addNewWellUI.selectedField = addNewWellUI.fieldCombo.getItem(index);
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeHorizontalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewWellUI.wellTypeHorizontalRadio = (Button) e.getSource();
				addNewWellUI.selectedRadio = addNewWellUI.wellTypeHorizontalRadio.getText();
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeVerticalRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewWellUI.wellTypeVerticalRadio = (Button) e.getSource();
				addNewWellUI.selectedRadio = addNewWellUI.wellTypeVerticalRadio.getText();
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeDeviatedRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewWellUI.wellTypeDeviatedRadio = (Button) e.getSource();
				addNewWellUI.selectedRadio = addNewWellUI.wellTypeDeviatedRadio.getText();
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

		addNewWellUI.wellTypeSWellRadio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addNewWellUI.wellTypeSWellRadio = (Button) e.getSource();
				addNewWellUI.selectedRadio = addNewWellUI.wellTypeSWellRadio.getText();
				AddNewWellModelMgr.INSTANCE.changeModelFromUI();
			}
		});

	}

}
