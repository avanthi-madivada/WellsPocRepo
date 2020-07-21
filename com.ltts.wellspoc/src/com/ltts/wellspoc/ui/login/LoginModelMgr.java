package com.ltts.wellspoc.ui.login;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.ltts.wellspoc.models.UserModel;
import com.ltts.wellspoc.ui.util.MessagesUtil;
import com.ltts.wellspoc.ui.util.PropertiesCache;

/**
 * creates user Model instance.
 * 
 * @author Ranjith D
 *
 */
public enum LoginModelMgr {

	INSTANCE;

	UserModel userModel;
	private List<PropertyChangeListener> userModelChangeisteners = new ArrayList<PropertyChangeListener>();

	// accessing user name and password
	PropertiesCache prop = PropertiesCache.getInstance();
	private final String USERNAME = prop.getProperty("LoginPage_username");
	private final String PASSWORD = prop.getProperty("LoginPage_password");
	boolean isValid;

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

	/**
	 * validates the user name and password entered.
	 * 
	 * @return
	 */
	public boolean isValid(String userName, String password) {
		isValid = false;
		if (userName.contentEquals(USERNAME) && password.contentEquals(PASSWORD)) {

			isValid = true;

		} else if (!userName.contentEquals(USERNAME) && !password.contentEquals(PASSWORD)) {

			MessagesUtil.displayErrorDialog("Your username and password is incorrect. Please try again.");

		} else if (!userName.contentEquals(USERNAME)) {

			MessagesUtil.displayErrorDialog("Your username is incorrect. Please try again.");

		} else if (!password.contentEquals(PASSWORD)) {

			MessagesUtil.displayErrorDialog("Your password is incorrect. Please try again.");
		}
		return isValid;

	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener listner : userModelChangeisteners) {
			listner.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}
}
