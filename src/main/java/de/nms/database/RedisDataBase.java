package de.nms.database;

import de.nms.database.exep.NotImplementedException;

public class RedisDataBase extends Database{
    @Override
    public void connect() {
        try {
            throw new NotImplementedException("Error: Redis is sadly not implemented");
        } catch (NotImplementedException e) {
        }
    }

    @Override
    public void start() {
        try {
            throw new NotImplementedException("Error: Redis is sadly not implemented");
        } catch (NotImplementedException e) {
        }
    }

    @Override
    public void data(String key, Object value) {
        try {
            throw new NotImplementedException("Error: Redis is sadly not implemented");
        } catch (NotImplementedException e) {
        }
    }

    @Override
    public DataBaseType type() {
        return DataBaseType.REDIS;
    }
}
