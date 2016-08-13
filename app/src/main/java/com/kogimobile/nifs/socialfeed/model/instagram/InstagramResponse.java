
package com.kogimobile.nifs.socialfeed.model.instagram;

import java.util.ArrayList;
import java.util.List;

public class InstagramResponse {

    private Pagination pagination;
    private Meta meta;
    private List<Data> data = new ArrayList<Data>();

    /**
     * 
     * @return
     *     The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * 
     * @param pagination
     *     The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<Data> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<Data> data) {
        this.data = data;
    }

}
