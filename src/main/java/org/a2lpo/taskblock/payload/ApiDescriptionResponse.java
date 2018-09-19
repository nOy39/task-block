package org.a2lpo.taskblock.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiDescriptionResponse implements Serializable {
    private String url;
    private String method;
    private String description;
    private String requestExample;
    private String responseExample;
    private Boolean authRequire;
    public ApiDescriptionResponse(String url,
                                  String method,
                                  String description,
                                  String requestExample,
                                  String responseExample,
                                  Boolean authRequire) {
        this.url = url;
        this.method = method;
        this.description = description;
        this.requestExample = requestExample;
        this.responseExample = responseExample;
        this.authRequire = authRequire;
    }
}
