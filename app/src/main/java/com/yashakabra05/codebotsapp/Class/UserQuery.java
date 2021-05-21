package com.yashakabra05.codebotsapp.Class;

public class UserQuery {

    String query, useremail;

    public UserQuery() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public UserQuery(String query, String useremail) {
        this.query = query;
        this.useremail = useremail;
    }
}
