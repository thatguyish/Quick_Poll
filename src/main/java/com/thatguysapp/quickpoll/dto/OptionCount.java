package com.thatguysapp.quickpoll.dto;

public class OptionCount {
    private Long optionId;
    private Long count;

    public OptionCount() {
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
