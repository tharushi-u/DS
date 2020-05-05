package com.ds.firealarmrest.sensor;

import javax.xml.bind.annotation.XmlRootElement;

/*********************  Sensor Model class ******************/

@XmlRootElement
public class Sensor {
    private int sensorId;
    private int co2Level;
    private int smokeLevel;
	private String floorNo;
	private String roomNo;
	private int status;

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(int co2Level) {
        this.co2Level = co2Level;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sensor [sensorId=" + sensorId + ", co2Level=" + co2Level + ", smokeLevel=" + smokeLevel + ", status=" + status + "]";
    }

}
