package com.ltts.wellspoc.ui.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import java.text.DecimalFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * Class containing Text field to manage Texts with decimal values.
 * 
 * @author Avanthi
 *
 */
public class DecimalNumberManager {
	private Text textItem;
	private String textName;
	private Double minValue = -Double.MAX_VALUE;
	private Double maxValue = Double.MAX_VALUE;
	private String errorMessage = "";
	private boolean isValid = true;
	private static final Device device = Display.getCurrent();;
	private static final Color red = new Color(device, 255, 0, 0);
	private final Color black = new Color(device, 0, 0, 0);

	public DecimalNumberManager(String textName, Composite parent) {
		this.textName = textName;
		textItem = new Text(parent, SWT.BORDER);
		GridData gridDataText = new GridData();
		gridDataText.widthHint = 100;
		gridDataText.horizontalIndent =7;
		textItem.setLayoutData(gridDataText);
		textItem.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");
	}

	public DecimalNumberManager(String textName, Composite parent, Double minValue, Double maxValue) {
		textItem = new Text(parent, SWT.BORDER);
		GridData gridDataText = new GridData();
		gridDataText.widthHint = 100;
		gridDataText.horizontalIndent =7;
		textItem.setLayoutData(gridDataText);
		textItem.setToolTipText("The entered value should be decimal and should have only one digit after decimal.");
		this.textName = textName;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public  void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Verifies and sets the double value to Text item.
	 * 
	 * @param doubleValue
	 */
	public void setValue(Double doubleValue) {
	    if (doubleValue == null || doubleValue==-999.0) {
	        textItem.setText("");
	        textItem.setForeground(black);
	        isValid = false;
	        return;
	    }
		String formatter = "#######.#";
		DecimalFormat df = new DecimalFormat(formatter);
		textItem.setText(df.format(doubleValue));
		textItem.setForeground(black);
		isValid = true;
	}

	/**
	 * 
	 * @return value in text box as Double value
	 */
	public Double getValue() {
		checkInLimit();
		if (textItem.getText().trim() == "" || !isValid) {
			return 0.0;
		} else {
			return Double.valueOf(textItem.getText());
		}
	}

	// Restricts the entry of invalid characters.
	public void restrictEnteredChars() {
		String allowedCharacters = "0123456789.,-";
		String text = textItem.getText();
		textItem.setForeground(black);
		for (int i = 0; i < text.length(); i++) {
			char charBefore='\t';
			if(i>=2) {
				charBefore = text.charAt(i-2);
			}			 
			char character = text.charAt(i);
			boolean isAllowed = allowedCharacters.indexOf(character) > -1;
			if (!isAllowed) {
				textItem.setForeground(red);
				isValid = false;
				errorMessage = "The entered  value in  " + textName + "  should be decimal value";
				return;
			}
			else if(charBefore=='.'){
				textItem.setForeground(red);
				isValid = false;
				errorMessage = "The entered  value in  " + textName + "  should have only one digit after decimal";
				return;
			}
		}
		checkInLimit();
	}

	// Checks if the entered chars is number or not.
	private void checkIfNumber() {
		String textEntered = textItem.getText();
		try {
			textItem.setForeground(black);
			Double.valueOf(textEntered);
			return;
		} catch (NumberFormatException exception) {
			isValid = false;
			textItem.setForeground(red);
			errorMessage = "The entered  value " + textName + " should be decimal value";
			return;
		}
	}

	// Checks if the given number is within the provided min and max range.
	private void checkInLimit() {
		checkIfNumber();
		String textEntered = textItem.getText();
		try {
			Double enteredDouble = Double.valueOf(textEntered);
			if (enteredDouble < minValue || enteredDouble > maxValue) {
				isValid = false;
				textItem.setForeground(red);
				errorMessage = "The entered double value in  " + textName + "  should be in between " + minValue
						+ " and " + maxValue;
				return;
			} else {
				textItem.setForeground(black);
				isValid = true;
				return;
			}
		} catch (NumberFormatException exception) {
			isValid = false;
		}
	}

	public Text getTextItem() {
		return textItem;
	}

    public void setEnabled(boolean enabled) {
        textItem.setEnabled(enabled);
        
    }

    public void addModifyListener(ModifyListener listener) {
       textItem.addModifyListener(listener);
        
    }

    public void removeModifyListener(ModifyListener listener) {
       textItem.removeModifyListener(listener);
        
    }
}
