package com.example.imagemachine;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Machine implements Parcelable {

    private int id;
    private String mMachineName;
    private String mMachineType;
    private int qrCode;
    private Date lastMaintenance;

    public Machine(int id, String mMachineName, String mMachineType, int qrCode, Date lastMaintenance) {
        this.id = id;
        this.mMachineName = mMachineName;
        this.mMachineType = mMachineType;
        this.qrCode = qrCode;
        this.lastMaintenance = lastMaintenance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmMachineName() {
        return mMachineName;
    }

    public void setmMachineName(String mMachineName) {
        this.mMachineName = mMachineName;
    }

    public String getmMachineType() {
        return mMachineType;
    }

    public void setmMachineType(String mMachineType) {
        this.mMachineType = mMachineType;
    }

    public int getQrCode() {
        return qrCode;
    }

    public void setQrCode(int qrCode) {
        this.qrCode = qrCode;
    }

    public Date getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(Date lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", mMachineName='" + mMachineName + '\'' +
                ", mMachineType='" + mMachineType + '\'' +
                ", qrCode=" + qrCode +
                ", lastMaintenance=" + lastMaintenance +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.mMachineName);
        dest.writeString(this.mMachineType);
        dest.writeInt(this.qrCode);
        dest.writeLong(this.lastMaintenance != null ? this.lastMaintenance.getTime() : -1);
    }

    protected Machine(Parcel in) {
        this.id = in.readInt();
        this.mMachineName = in.readString();
        this.mMachineType = in.readString();
        this.qrCode = in.readInt();
        long tmpLastMaintenance = in.readLong();
        this.lastMaintenance = tmpLastMaintenance == -1 ? null : new Date(tmpLastMaintenance);
    }

    public static final Parcelable.Creator<Machine> CREATOR = new Parcelable.Creator<Machine>() {
        @Override
        public Machine createFromParcel(Parcel source) {
            return new Machine(source);
        }

        @Override
        public Machine[] newArray(int size) {
            return new Machine[size];
        }
    };
}
