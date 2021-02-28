package pl.sdaacademy.reservationapi.organization;

import java.util.Objects;

public class OrganizationConferenceRoomDTO {

    private String name;
    private String roomNumber;
    private boolean available;
    private int level;
    private int capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationConferenceRoomDTO that = (OrganizationConferenceRoomDTO) o;
        return available == that.available && level == that.level && capacity == that.capacity && Objects.equals(name, that.name) && Objects.equals(roomNumber, that.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roomNumber, available, level, capacity);
    }
}
