package com.calibroz.spa.framework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.chrome.ChromeDriver;


public class PrintDialogUtil {
	/**
	 * <strong> Initiate Print as SaveAsPDF</strong>
	 */
	public static void initiatePrintSaveAsPDF(String fileName) {
		Runnable printTask = () -> { 
			System.out.println("Printing Task #2 is running");
			Robot r = null;
			try {
				r = new Robot();
			} catch (AWTException e) {
				
				e.printStackTrace();
			}
			r.delay(5000);
			
			if( Browser.driver  instanceof ChromeDriver) {
				tabBackToDestinationByChrome(r);
				r.delay(2000);
				moveCursorToVeryTopInDestinationDropDownByChrome(r);
				r.delay(2000);
				pressAndReleaseDownKey(r);// Selecting 'Save As PDF'
				r.delay(2000);
				tabForwardToSaveBtnByChrome(r); 
				r.delay(2000);
				//leftMouseClickAndRelease(r);
				pressAndReleaseEnterKey(r);
				r.delay(3000);
				typeContent(r, fileName);
				r.delay(2000);				
				pressAndReleaseEnterKey(r);
				replaceExistingFileIfAny(r);
				System.out.println("Chrome: Print to PDF Completed");
			}
			
			
		
		};
		new Thread(printTask).start();
	}
	public static void replaceExistingFileIfAny(Robot r) {
		r.delay(3000);	
		shiftTabEvent(r);
		r.delay(2000);	
		pressAndReleaseEnterKey(r);
	}
	/**
	 * <strong> Tab back to Destination Drop down from 'print' button </strong>
	 * <p> Chrome browser Print Dialog , By default the tab focus will be at print button</p>
	 * 
	 * @param r
	 */
	public static void tabBackToDestinationByChrome(Robot r) {
		r.keyPress(KeyEvent.VK_SHIFT);
		
		pressAndReleasTabKey(r);
		pressAndReleasTabKey(r);
		pressAndReleasTabKey(r);
		pressAndReleasTabKey(r);
		pressAndReleasTabKey(r);
		
		r.keyRelease(KeyEvent.VK_SHIFT);

	}
	/**
	 * <strong> Tab forward to  'print' button from 'Destination' </strong>
	 * <p> Chrome browser Print Dialog , By default the tab focus will be at print button</p>
	 * 
	 * @param r
	 */
	public static void tabForwardToSaveBtnByChrome(Robot r) {
				
		pressAndReleasTabKey(r);
		
		pressAndReleasTabKey(r);	
		
		pressAndReleasTabKey(r);
		
		pressAndReleasTabKey(r);
			
		
	}
	public static void moveCursorToVeryTopInDestinationDropDownByChrome(Robot r) {
          for(int i = 0; i<8; i++) {
        	  pressAndReleaseUpKey(r);
          }
	}
	public static void pressAndReleaseDownKey(Robot r) {
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
	}
	public static void pressAndReleaseUpKey(Robot r) {
		r.keyPress(KeyEvent.VK_UP);
		r.keyRelease(KeyEvent.VK_UP);
	}
	public static void pressAndReleasTabKey(Robot r) {
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);	
	}
	public static void leftMouseClickAndRelease(Robot r) {
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);;
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);	
	}
	public static void pressAndReleaseEnterKey(Robot r) {
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void shiftTabEvent(Robot r) {
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_SHIFT);

	}
	
	public static void typeContent(Robot robot, String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

	}
}
