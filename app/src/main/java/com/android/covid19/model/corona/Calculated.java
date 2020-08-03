package com.android.covid19.model.corona;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Calculated implements Serializable
{

    @SerializedName("death_rate")
    @Expose
    private Double deathRate;
    @SerializedName("recovery_rate")
    @Expose
    private Double recoveryRate;
    @SerializedName("recovered_vs_death_ratio")
    @Expose
    private Object recoveredVsDeathRatio;
    @SerializedName("cases_per_million_population")
    @Expose
    private Integer casesPerMillionPopulation;
    private final static long serialVersionUID = -993096249912551282L;

    public Double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(Double deathRate) {
        this.deathRate = deathRate;
    }

    public Double getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(Double recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public Object getRecoveredVsDeathRatio() {
        return recoveredVsDeathRatio;
    }

    public void setRecoveredVsDeathRatio(Object recoveredVsDeathRatio) {
        this.recoveredVsDeathRatio = recoveredVsDeathRatio;
    }

    public Integer getCasesPerMillionPopulation() {
        return casesPerMillionPopulation;
    }

    public void setCasesPerMillionPopulation(Integer casesPerMillionPopulation) {
        this.casesPerMillionPopulation = casesPerMillionPopulation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("deathRate", deathRate).append("recoveryRate", recoveryRate).append("recoveredVsDeathRatio", recoveredVsDeathRatio).append("casesPerMillionPopulation", casesPerMillionPopulation).toString();
    }

}