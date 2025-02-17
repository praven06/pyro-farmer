package com.example.pyro.DTO;


public class BuyShareRequest {
    private String user;
    private String share;
    private int numberOfShares;
    
    public BuyShareRequest() {
        
    }
    public BuyShareRequest(String user, String share, int numberOfShares) {
        this.user = user;
        this.share = share;
        this.numberOfShares = numberOfShares;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getShare() {
        return share;
    }
    public void setShare(String share) {
        this.share = share;
    }
    public int getNumberOfShares() {
        return numberOfShares;
    }
    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

  
   
}
