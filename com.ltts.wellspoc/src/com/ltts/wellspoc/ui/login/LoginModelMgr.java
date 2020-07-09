package com.ltts.wellspoc.ui.login;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.ltts.wellspoc.models.UserModel;

/**
 * creates user Model instance.
 * 
 * @author Ranjith D
 *
 */
public enum LoginModelMgr {

	INSTANCE;

	UserModel userModel;
	LoginUI loginUI;
	private List<PropertyChangeListener> userModelChangeisteners = new ArrayList<PropertyChangeListener>();

	/**
	 * provides user Model instance.
	 * 
	 * @return
	 */
	public UserModel getUserModel() {
		if (userModel == null) {
			this.createUserModel();
		}

		return userModel;
	}

	/**
	 * creates user Model instance.
	 */
	public void createUserModel() {
		if (userModel == null) {
			userModel = new UserModel();
		}
	}

	/**
	 * adds the current instance listeners to wellModelChangeisteners.
	 * 
	 * @param newListener
	 */
	public void addChangeListener(PropertyChangeListener newListener) {
		userModelChangeisteners.add(newListener);
	}

	/**
	 * updates the model values from UI.
	 */
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

// 	To do

//	public void changeUIFromModel() {
//	
//	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : userModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
