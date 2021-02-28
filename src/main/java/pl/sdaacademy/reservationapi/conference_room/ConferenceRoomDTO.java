package pl.sdaacademy.reservationapi.conference_room;

public class ConferenceRoomDTO {
    private String name;
    private String roomNumber;
    private boolean available;
    private int level;
    private int capacity;
    private String organizationName;

    public ConferenceRoomDTO() {

    }

    public ConferenceRoomDTO(String name, String roomNumber, boolean available, int level, int capacity, String organizationName) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.available = available;
        this.level = level;
        this.capacity = capacity;
        this.organizationName = organizationName;
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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
