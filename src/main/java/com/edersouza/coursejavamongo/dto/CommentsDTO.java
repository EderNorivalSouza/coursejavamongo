package com.edersouza.coursejavamongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentsDTO implements Serializable {

    private String text;
    private Date date;
    private AuthorDTO authorDTO;

    public CommentsDTO() {
    }

    public CommentsDTO(String text, Date date, AuthorDTO authorDTO) {
        this.text = text;
        this.date = date;
        this.authorDTO = authorDTO;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }
}
