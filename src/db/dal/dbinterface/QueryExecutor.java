package db.dal.dbinterface;

public class QueryExecutor implements Runnable {
    private DalAppInterface context = null;

    public QueryExecutor(DalAppInterface context) {
        this.context = context;
    }

    @Override
    public void run() {
        while(true) {
            if(context.workQueue.peek() != null) {
                context.performWrite();
            }
        }
    }
}
