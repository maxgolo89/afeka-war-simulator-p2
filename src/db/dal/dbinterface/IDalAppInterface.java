package db.dal.dbinterface;

import bl.WarModel;
import db.dal.entities.IWarModelDao;
import program.IConstants;

public interface IDalAppInterface {
    boolean writeWarToDb(WarModel warModel);
    IWarModelDao readWarFromDb(long warTime);
}
