// ApiResponse.java
package zlosnik.jp.lab04;

import java.util.List;

public class ApiResponse {
    private Meta meta;
    private Links links;
    private List<String> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}