package com.example.supportping;

import com.google.gson.JsonObject;

import java.util.List;

public class ServerRequest {
    private List<JsonObject> boardinfos;

    public List<JsonObject> getBoardinfos() {
        return boardinfos;
    }
}
