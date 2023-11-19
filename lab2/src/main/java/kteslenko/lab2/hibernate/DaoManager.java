package kteslenko.lab2.hibernate;

public class DaoManager {
    private static ClientDao clientDao;

    public static ClientDao getClientDao() {
        if (clientDao == null) {
            clientDao = new ClientDao(SessionFactorySingleton.getInstance());
        }
        return clientDao;
    }
}
