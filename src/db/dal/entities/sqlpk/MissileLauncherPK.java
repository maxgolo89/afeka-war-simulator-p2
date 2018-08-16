package db.dal.entities.sqlpk;

import java.io.Serializable;

public class MissileLauncherPK implements Serializable {
    public String   id;
    public long     warModelId;

    public MissileLauncherPK() {
    }

    public MissileLauncherPK(String id, long warModelId) {
        this.id = id;
        this.warModelId = warModelId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
