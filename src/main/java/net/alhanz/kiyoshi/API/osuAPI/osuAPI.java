package net.alhanz.kiyoshi.API.osuAPI;

import net.alhanz.kiyoshi.API.osuAPI.osuAuth.Token;

import com.google.gson.Gson;
import net.alhanz.kiyoshi.SecKey;
import okhttp3.*;

public class osuAPI {

    private static final OkHttpClient httpClient = new OkHttpClient();
    private static Token osuToken = new Token();

    public static void main(String[] args) throws Exception {
        // Authentication
        osuToken.setClient_id(SecKey.osuClientID);
        osuToken.setClient_secret(SecKey.osuAPIKey);

        // Post into API using Form Body Request
        String authToken = getAuthToken();

        // Converting Json from osuAPI to get OAuth Token
        Gson gson = new Gson();
        osuToken = gson.fromJson(authToken, Token.class);
    }

    public static String getAuthToken() throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", osuToken.getClient_id())
                .add("client_secret", osuToken.getClient_secret())
                .add("grant_type", "client_credentials")
                .add("scope", "public")
                .build();

        Request request = new Request.Builder()
                .url("https://osu.ppy.sh/oauth/token")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public static String accessOsuAPIv2(String authToken, String args) throws Exception {
        Request request = new Request.Builder()
                .url("https://osu.ppy.sh/api/v2/" + args)
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }
}
