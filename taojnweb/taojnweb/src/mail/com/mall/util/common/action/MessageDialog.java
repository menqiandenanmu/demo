package com.mall.util.common.action;

import java.util.ArrayList;
import java.util.List;

public class MessageDialog {
	
	public static String SUCCESS = "success";
	public static String ERROR = "error";
	public static String WARNING = "warning";
	public static String QUESTION = "question";
	public static String FORBID = "forbid";
	private String flag = SUCCESS;;
	private List<String> messages;
	private List<MessageButton> buttons;

	public MessageDialog() {
		messages = new ArrayList<String>();
		buttons = new ArrayList<MessageButton>();
	}

	public MessageDialog(String flag) {
		messages = new ArrayList<String>();
		buttons = new ArrayList<MessageButton>();
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<MessageButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<MessageButton> buttons) {
		this.buttons = buttons;
	}

	public void addButton(MessageButton button) {
		buttons.add(button);
	}

	public void addMessage(String message) {
		messages.add(message);
	}

}
