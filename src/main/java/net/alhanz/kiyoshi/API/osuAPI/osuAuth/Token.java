package net.alhanz.kiyoshi.API.osuAPI.osuAuth;

public class Token {

    private String client_id;
    private String client_secret;
    final public static String grant_type = "client_credentials";
    final public static String scope = "public";

    private String access_token;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}

