package com.ltts.wellspoc.ui.login;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.ltts.wellspoc.models.UserModel;

public enum LoginModelMgr {

	INSTANCE;

	UserModel userModel;
	private List<PropertyChangeListener> userModelChangeisteners = new ArrayList<PropertyChangeListener>();

	public UserModel getUserModel() {
		if (userModel == null) {
			this.createUserModel();
		}
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

//	
	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : userModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
