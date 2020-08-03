package com.android.covid19.model.corona;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LatestData implements Serializable
{

    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("critical")
    @Expose
    private Integer critical;
    @SerializedName("calculated")
    @Expose
    private Calculated calculated;
    private final static long serialVersionUID = -8019379297362423996L;

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public Calculated getCalculated() {
        return calculated;
    }

    public void setCalculated(Calculated calculated) {
        this.calculated = calculated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("deaths", deaths).append("confirmed", confirmed).append("recovered", recovered).append("critical", critical).append("calculated", calculated).toString();
    }

}