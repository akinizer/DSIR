package sample.SuperiorManagement.DatabaseManagement;

public interface IDatabase {

    public void connect();

    public void disconnect();

    //CRUD
    public void getRecordByName(String name);

    public void updateRecordByName(String name);

    public void setRecordByName(String name);

    public void removeRecordByName(String name);
}
