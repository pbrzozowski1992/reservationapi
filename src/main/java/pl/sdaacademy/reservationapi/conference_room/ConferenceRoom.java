package pl.sdaacademy.reservationapi.conference_room;

import pl.sdaacademy.reservationapi.organization.Organization;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String roomNumber;
    private boolean available;
    private int level;
    private int capacity;

    @ManyToOne
    private Organization organization;

    public ConferenceRoom() {

    }

    public ConferenceRoom(long id, String name, String roomNumber, boolean available, int level, int capacity) {
        this.id = id;
        this.name = name;
        this.roomNumber = roomNumber;
        this.available = available;
        this.level = level;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        ConferenceRoom that = (ConferenceRoom) o;
        return id == that.id && available == that.available && level == that.level && capacity == that.capacity && Objects.equals(name, that.name) && Objects.equals(roomNumber, that.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roomNumber, available, level, capacity);
    }
}
