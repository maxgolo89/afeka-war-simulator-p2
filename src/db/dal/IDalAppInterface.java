package db.dal;

import bl.WarModel;
import program.IConstants;

public interface IDalAppInterface {
    boolean writeWarToDb(WarModel warModel);
    WarModel readWarFromDb(long warTime);
}
