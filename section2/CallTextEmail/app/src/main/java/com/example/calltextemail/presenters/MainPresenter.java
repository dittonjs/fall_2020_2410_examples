package com.example.calltextemail.presenters;

public class MainPresenter {
    public interface MVPView {
        void makePhoneCall(String number);
        void sendText(String number);
        void sendEmail(String address);
    }

    private MVPView view;

    public MainPresenter(MVPView view) {
        this.view = view;
    }

    public void handleCallPressed(String phoneNumber) {
        view.makePhoneCall(phoneNumber);
    }

    public void handleTextPressed(String phoneNumber) {
        view.sendText(phoneNumber);
    }

    public void handleEmailPressed(String email) {
        view.sendEmail(email);
    }
}
