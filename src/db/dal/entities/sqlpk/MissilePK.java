package db.dal.entities.sqlpk;

import java.io.Serializable;

public class MissilePK implements Serializable {
    public String   id;
    public long     warModelId;

    public MissilePK() {
    }

    public MissilePK(String mId, long wId) {
        this.id = mId;
        this.warModelId = wId;
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
