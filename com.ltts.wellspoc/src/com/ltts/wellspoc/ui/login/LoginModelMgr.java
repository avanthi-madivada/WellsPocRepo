package com.ltts.wellspoc.ui.login;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.ltts.wellspoc.models.UserModel;
import com.ltts.wellspoc.ui.util.MessagesUtil;

public enum LoginModelMgr {

	INSTANCE;

	UserModel userModel;
	LoginUI loginUI;
	private List<PropertyChangeListener> userModelChangeisteners = new ArrayList<PropertyChangeListener>();

	public UserModel getUserModel() {
		if (userModel == null) {
			this.createUserModel();
		}
//		this.changeUIFromModel();
		return userModel;
	}

	public void createUserModel() {
		if (userModel == null) {
			userModel = new UserModel();
		}

	}

	public void addChangeListener(PropertyChangeListener newListener) {
		userModelChangeisteners.add(newListener);
	}

	public void changeModelFromUI() {

		if (LoginViewMgr.INSTANCE.loginUI.userNameText.getText() != null) {
			userModel.setUserName(LoginViewMgr.INSTANCE.loginUI.userNameText.getText());
			notifyListeners(this, "", "", "");
		}

		if (LoginViewMgr.INSTANCE.loginUI.passwordText.getText() != null) {
			userModel.setPassword(LoginViewMgr.INSTANCE.loginUI.passwordText.getText());
			notifyListeners(this, "", "", "");
		}
	}

	public void changeUIFromModel() {
		try {
			if (LoginViewMgr.INSTANCE.userModel.getUserName() != null
					&& LoginViewMgr.INSTANCE.userModel.getUserName() == "admin") {
				loginUI.userNameText.setText("admin");
			}
			if (LoginViewMgr.INSTANCE.userModel.getPassword() != null
					&& LoginViewMgr.INSTANCE.userModel.getPassword() == "admin") {
				loginUI.passwordText.setText("admin");
			}
		} catch (Exception e) {
			MessagesUtil.logError(LoginModelMgr.class.getName(), e.getMessage());
		}
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : userModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
