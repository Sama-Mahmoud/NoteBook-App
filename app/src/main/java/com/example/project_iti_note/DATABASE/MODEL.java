package com.example.project_iti_note.DATABASE;

public class MODEL {
    private String note_name , note_details ;

    public MODEL() {

    }

    public MODEL(String note_name, String note_details) {
        this.note_name = note_name;
        this.note_details = note_details;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getNote_details() {
        return note_details;
    }

    public void setNote_details(String note_details) {
        this.note_details = note_details;
    }
}
